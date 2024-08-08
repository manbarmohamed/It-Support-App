package com.it.support.config;

import com.it.support.service.JwtService;
import com.it.support.service.PersonService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * JwtAuthFilter is a custom filter for handling JWT authentication in a Spring Boot application.
 * It ensures that each request is checked for a valid JWT token and the associated user is authenticated.
 */
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService; // Service for JWT operations such as validation and token generation.

    @Autowired
    private PersonService userService; // Service for loading user details.

    /**
     * Processes the incoming HTTP request to extract and validate the JWT token.
     * Sets the authentication in the SecurityContext if the token is valid.
     *
     * @param request The HTTP request.
     * @param response The HTTP response.
     * @param filterChain The filter chain to pass the request and response to.
     * @throws ServletException If an error occurs during request processing.
     * @throws IOException If an I/O error occurs during request processing.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization"); // Retrieve the Authorization header.
        String token = null;
        String username = null;

        // Check if the header starts with "Bearer " and extract the token.
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7); // Remove "Bearer " prefix.

            // Parse the JWT token to extract claims.
            Claims claims = Jwts.parser()
                    .setSigningKey(JwtService.SECRET) // Signing key for JWT verification.
                    .parseClaimsJws(token)
                    .getBody();
            username = claims.getSubject(); // Extract the username from the claims.
        }

        // If a username is found and no authentication is set in the context.
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Load user details using the username.
            UserDetails userDetails = userService.loadUserByUsername(username);

            // Validate the token against the user details.
            if (jwtService.validateToken(token, userDetails)) {
                // Create an authentication token with user details and authorities.
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); // Set details for the authentication token.

                // Set the authentication in the SecurityContext.
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        // Continue the filter chain to the next filter or endpoint.
        filterChain.doFilter(request, response);
    }
}
