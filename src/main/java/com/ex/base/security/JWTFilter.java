package com.ex.base.security;

import com.auth0.jwt.exceptions.JWTVerificationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTFilter extends OncePerRequestFilter {

    @Autowired private ApiUserDetailsService userDetailsService;
    @Autowired private JWTUtility jwtUtility;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        System.out.println("------------ Entered doFilterInteral ----------");
        String authHeader = request.getHeader("Authorization");

        System.out.println("00000000000000000000000####################");

        if(authHeader != null && !authHeader.isBlank() && authHeader.startsWith("Bearer ")){
            String jwt = authHeader.substring(7);
            if(jwt == null || jwt.isBlank()){
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JWT token in bearer header");
            } else {
                try{
                    System.out.println("11111111111111111111####################");

                    String email = jwtUtility.validateTokenAndRetrieveSubject(jwt);

                    System.out.println("222222222222222222222##################");
                    UserDetails userDetails = userDetailsService.loadUserByUsername(email);

                    System.out.println("33333333333333333333####################");
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(email, userDetails.getPassword(), userDetails.getAuthorities());
                    System.out.println("44444444444444444444###################");
                    if(SecurityContextHolder.getContext().getAuthentication() == null) {
                        System.out.println("555555555555555555###################");
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                } catch(JWTVerificationException e) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JWT Token");
                } catch(Exception e) {
                    System.out.println("Caught it!");
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JWT token in bearer header");
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
