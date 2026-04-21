package com.codewithdurgesh.blog.config;


import com.codewithdurgesh.blog.security.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

/*
* SecurityConfig Class
* Purpose: Configures Spring Security settings for the Blog App API
* Defines authentication and authorization rules for all HTTP requests
 */



@Configuration      //Indicates this class contains Spring Configuration beans
@EnableWebSecurity  // Enables Sprinf Security web security features for this application
public class securityConfig {

    /*
    * SecurityFilterChain Bean
    * Purpose:Creates and returns a security filter chain that defines hot HTTP requests should be secured
    *
    * @param http-HttpSecurity object used to configure security setting
    * @return-SecurityFilterChain with configures security rules
    * @throws Exception -If any error occurs during security configuration
    */

          @Autowired
    private CustomUserDetailService customUserDetailService;

    /*
    * SecurityFilterChain Bean(Spring Security 6.0+ approach)
    * Purpose: Creates and returns a security filter chain with HTTP Basic authentication
    * Uses CustomUserDetails for loading user details from database
    *
    * */

  public SecurityFilterChain securityFilterChain (HttpSecurity http)throws Exception{
       http
               //Disable CSRF protection (REST APIs dont need it)
               .csrf(AbstractHttpConfigurer::disable)
               //Authorize all http requests
               .authorizeHttpRequests(auth->auth.anyRequest().authenticated()) //All requests require authentication

               //Enable HTTP Basic authentication
               .httpBasic(Customizer.withDefaults());

       return http.build();

  }
















}
