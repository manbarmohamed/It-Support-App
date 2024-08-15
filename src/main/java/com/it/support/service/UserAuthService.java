package com.it.support.service;

import com.it.support.dto.AuthRequestDTO;
import com.it.support.dto.JwtResponseDTO;
import com.it.support.model.Person;
import com.it.support.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service class responsible for handling user authentication and JWT token generation.
 */
@Service
public class UserAuthService implements UserDetailsService {

    @Autowired
    private PersonRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Lazy
    @Autowired
    private AuthenticationManager authenticationManager;

    @Lazy
    @Autowired
    private JwtService jwtService;

    /**
     * Loads user details by username.
     *
     * @param username The username of the user to be loaded.
     * @return UserDetails object containing user information.
     * @throws UsernameNotFoundException If the user with the specified username is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build();
    }

    /**
     * Authenticates a user and generates a JWT token upon successful login.
     *
     * @param authRequestDTO The AuthRequestDTO object containing the username and password.
     * @return JwtResponseDTO object containing the JWT token and user information.
     * @throws UsernameNotFoundException If the authentication fails due to invalid credentials.
     */
    public JwtResponseDTO login(AuthRequestDTO authRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword())
        );

        if (authentication.isAuthenticated()) {
            Person user = userRepository.findByUsername(authRequestDTO.getUsername());
            String token = jwtService.generateToken(user.getUsername(), user.getRole());

            return JwtResponseDTO.builder()
                    .accessToken(token)
                    .user(user)
                    .build();
        } else {
            throw new UsernameNotFoundException("Invalid user request.");
        }
    }}