package com.prueba.personaltraining.config;

import com.prueba.personaltraining.repository.UserRepository;
import com.prueba.personaltraining.service.api.JWTService;
import com.prueba.personaltraining.service.impl.JWTServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFiler extends OncePerRequestFilter {

    @Autowired
    JWTService jwtService;

    @Autowired
    UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String token = getTokenFromRequest(request);

        if (token != null) {


            UserDetails userDetails = userRepository.findByDni(jwtService.getUsernameFromToken(token));
            jwtService.isTokenValid(token,userDetails);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }


        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {

        final String authHeader=request.getHeader("Authorization");

        if(authHeader != null && authHeader.startsWith("Bearer "))
        {
            return authHeader.substring(7);
        }
        return null;
    }
}
