package com.eklauth.microserviceAuth.configurations;
import com.eklauth.microserviceAuth.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

@EnableWebSecurity

@Configuration
//@EnableGlobalMethodSecurity(
//        jsr250Enabled = true, //enable jsr250 annotation
//        securedEnabled = true, //
//        prePostEnabled = true  //
//)

public class SecurityConfig  extends WebSecurityConfigurerAdapter {
    private static final Logger LOGGER = Logger.getLogger(SecurityConfig.class.getName());


    @Autowired
    private CustomUserDetails customUserDetails;
    @Autowired
    private JwtTokenFilter jwtTokenFilter;
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(customUserDetails).passwordEncoder(passwordEncoderD());
    }
    
    @Bean //spring bean
    public  PasswordEncoder passwordEncoderD(){
        return new BCryptPasswordEncoder(); //return if coverted to shaa
        
        
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
       // Enable CORS and disable CSRF
        http = http.cors().and().csrf().disable();

       // Set session management to stateless
       http = http
               .sessionManagement()
               .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
               .and();

       // Set unauthorized requests exception handler
       http = http
               .exceptionHandling()
               .authenticationEntryPoint(
                       (request, response, ex) -> {
                           LOGGER.log(Level.SEVERE, "Unauthorized request - {}" + ex.getMessage());
                           response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
                       }
               )
               .and();


       // Set permissions on endpoints
       http.authorizeRequests()
               // Our public endpoints
               .antMatchers("/authenticate/**").permitAll()
               .antMatchers("/swagger-resources/**").permitAll()
               .antMatchers("swagger-ui/**").permitAll()
               .antMatchers("/v2/api-docs").permitAll()
              // .antMatchers("/configuration/ui").permitAll()

               .antMatchers("/configuration/security").permitAll()
              // .antMatchers("/swagger-ui.html").permitAll()
               .antMatchers("/webjars/**").permitAll()


                // Our private endpoints
               .anyRequest().authenticated();

       // Add JWT token filter
       http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

    }
    
    
    // Used by spring security if CORS is enabled.
    @Bean
   public CorsFilter corsFilter() {
       UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
       CorsConfiguration config = new CorsConfiguration();
       config.setAllowCredentials(true);
       config.setAllowedOriginPatterns(Arrays.asList("*", "http://localhost:80"));
//        config.addAllowedOrigin("*");
       config.addAllowedHeader("*");
       config.addAllowedMethod("*");
       source.registerCorsConfiguration("/**", config);
       return new CorsFilter(source);
   }




   // Expose authentication manager bean
   @Override
   @Bean
   public AuthenticationManager authenticationManagerBean() throws Exception {
       return super.authenticationManagerBean();
   }

//Swagger ui allow
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**",
                "//swagger-ui/*");
    }
    
    
}
