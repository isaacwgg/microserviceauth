/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eklauth.microserviceAuth.configurations;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.String.format;

/**
 *
 * @author isaac
 */
@Component
public class JwtTokenUtil {
    private final String jwtSecret = "yBKrn1G0b7cY";
    private final String jwtIssuer = "microservice.com";  //https://passwordsgenerator.net/

   private static final Logger LOGGER = Logger.getLogger(JwtTokenUtil.class.getName());

   public String generateAccessToken(Integer userId, String userName) {
        Calendar date = Calendar.getInstance();
       long timeInSecs = date.getTimeInMillis();
       Date afterAddingMins = new Date(timeInSecs + (2 * 3600 * 1000));
       return Jwts.builder()
               .setSubject(format("%s,%s", userId, userName))
               .setIssuer(jwtIssuer)
               .setIssuedAt(new Date())
               .setExpiration(afterAddingMins) // 2 hrs min
               .signWith(SignatureAlgorithm.HS512, jwtSecret)
               .compact();
   }

   public String getUserId(String token) {
       Claims claims = Jwts.parser()
               .setSigningKey(jwtSecret)
               .parseClaimsJws(token)
               .getBody();

       return claims.getSubject().split(",")[0];
   }

   public String getUsername(String token) {
       Claims claims = Jwts.parser()
               .setSigningKey(jwtSecret)
               .parseClaimsJws(token)
               .getBody();

       return claims.getSubject().split(",")[1];
   }

   public Date getExpirationDate(String token) {
       Claims claims = Jwts.parser()
               .setSigningKey(jwtSecret)
               .parseClaimsJws(token)
               .getBody();

       return claims.getExpiration();
   }

   public boolean validate(String token) {
       try {
           Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
           return true;
       } catch (SignatureException ex) {
           LOGGER.log(Level.SEVERE, "Invalid JWT signature - {}" + ex.getMessage());
       } catch (MalformedJwtException ex) {
           LOGGER.log(Level.SEVERE, "Invalid JWT token - {}" + ex.getMessage());
       } catch (ExpiredJwtException ex) {
           LOGGER.log(Level.SEVERE, "Expired JWT token - {}" + ex.getMessage());
       } catch (UnsupportedJwtException ex) {
           LOGGER.log(Level.SEVERE, "Unsupported JWT token - {}" + ex.getMessage());
       } catch (IllegalArgumentException ex) {
           LOGGER.log(Level.SEVERE, "JWT claims string is empty - {}" + ex.getMessage());
       }
       return false;
   }
}
