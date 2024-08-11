package com.it.support.config;

import com.it.support.enums.Role;
import com.it.support.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * Security configuration class for setting up Spring Security.
 * Configures authentication, authorization, and JWT filter.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    JwtAuthFilter jwtAuthFilter; // Custom JWT filter for handling JWT authentication.

    /**
     * Bean for UserDetailsService to load user-specific data.
     *
     * @return UserDetailsService implementation.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserAuthService(); // Service to load user details and authenticate users.
    }

    /**
     * Bean for configuring HTTP security settings.
     *
     * @param http HttpSecurity configuration object.
     * @return Configured SecurityFilterChain.
     * @throws Exception If an error occurs during configuration.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable) // Disable CSRF protection as we're using JWT.
                .cors(withDefaults()) // Enable CORS with default settings.
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Stateless session management for REST APIs.
                )
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                //.requestMatchers("/public/**").permitAll() // Allow public access to "/public/**".
                                //.requestMatchers("/admin/**").hasRole(Role.ADMIN.name()) // Restrict access to "/admin/**" to ADMIN role.
                                //.requestMatchers("/user/**").hasRole(Role.USER.name()) // Restrict access to "/user/**" to USER role.
                                //.requestMatchers("/tech/**").hasRole(Role.TECH.name()) // Restrict access to "/tech/**" to TECH role.
                                //.requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll() // Allow access to Swagger UI and API docs.
                                //.anyRequest().authenticated() // Require authentication for all other requests.
                                .anyRequest().permitAll()
                );

        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class); // Add custom JWT filter before the default UsernamePasswordAuthenticationFilter.
        return http.build(); // Build and return the SecurityFilterChain.
    }

    /**
     * Bean for PasswordEncoder using BCrypt for password hashing.
     *
     * @return PasswordEncoder implementation.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // BCrypt implementation for password encoding.
    }

    /**
     * Bean for AuthenticationProvider for user authentication.
     *
     * @return AuthenticationProvider implementation.
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService()); // Set the UserDetailsService.
        authenticationProvider.setPasswordEncoder(passwordEncoder()); // Set the PasswordEncoder.
        return authenticationProvider; // Return the configured AuthenticationProvider.
    }

    /**
     * Bean for AuthenticationManager to handle authentication requests.
     *
     * @param config AuthenticationConfiguration object.
     * @return AuthenticationManager.
     * @throws Exception If an error occurs during the creation of AuthenticationManager.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager(); // Obtain and return the AuthenticationManager from the configuration.
    }
}
