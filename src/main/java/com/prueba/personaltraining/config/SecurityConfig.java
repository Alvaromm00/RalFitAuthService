package com.prueba.personaltraining.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

import lombok.RequiredArgsConstructor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFiler jwtAuthenticationFiler;
    private final AuthenticationProvider authenticationProvider;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationFiler jwtAuthenticationFiler) throws Exception{
        
        return http
            .csrf(csrf ->
                csrf.disable())

            .authorizeHttpRequests(authRequest ->
              authRequest
                .requestMatchers("/login").permitAll()
                      .requestMatchers("/register").permitAll()
                      .requestMatchers("/logs").hasRole("ADMIN")
                      .requestMatchers("/version").hasRole("ADMIN")
                      .anyRequest().authenticated()

            )
                .sessionManagement(sessionManager ->
                        sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFiler, UsernamePasswordAuthenticationFilter.class)

            .build();
    }

}

