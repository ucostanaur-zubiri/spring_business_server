package eus.hekuntza.zubiri.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import eus.hekuntza.zubiri.authentication.providers.OtpAuthenticationProvider;
import eus.hekuntza.zubiri.authentication.providers.UsernamePasswordAuthenticationProvider;

@Configuration
@EnableWebSecurity
public class SecurityAuthManagerConfig {

  @Autowired
  private OtpAuthenticationProvider otpAuthenticationProvider;
  
  @Autowired
  private UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider;
  
  @Bean
  public AuthenticationManager authManager(HttpSecurity http) throws Exception {
      AuthenticationManagerBuilder authenticationManagerBuilder = 
          http.getSharedObject(AuthenticationManagerBuilder.class);
      
      authenticationManagerBuilder
        .authenticationProvider(otpAuthenticationProvider)
        .authenticationProvider(usernamePasswordAuthenticationProvider);
      
      return authenticationManagerBuilder.build();
  }
  

}
