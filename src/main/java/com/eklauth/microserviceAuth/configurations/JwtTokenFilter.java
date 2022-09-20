/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eklauth.microserviceAuth.configurations;

import com.eklauth.microserviceAuth.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

import static org.springframework.util.StringUtils.isEmpty;

/**
 *
 * @author isaac
 */
@Component  //it make is to be a bean
public class JwtTokenFilter extends OncePerRequestFilter {
    @Autowired
    private CustomUserDetails customUserDetails;
    
   @Autowired
   private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
       
         // Get authorization header and validate
       final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
       if (isEmpty(header) || !header.startsWith("Bearer ")) {
           chain.doFilter(request, response);
           return;
       }

       // Get jwt token and validate
       final String token = header.split(" ")[1].trim();
       if (!jwtTokenUtil.validate(token)) {
           chain.doFilter(request, response);
           return;
       }

       // Get user identity and set it on the spring security context
       UserDetails userDetails = customUserDetails.loadUserById(Integer.parseInt(jwtTokenUtil.getUserId(token)));

       UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
               userDetails, null, userDetails == null ? Arrays.asList() : userDetails.getAuthorities());

       authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

       SecurityContextHolder.getContext().setAuthentication(authentication);
       chain.doFilter(request, response);
    }

   
}
