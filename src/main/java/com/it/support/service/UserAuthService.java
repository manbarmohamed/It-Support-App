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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getName())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build();
    }

//    public JwtResponseDTO signUp(Person userRequest) {
//        if (userRepository.findByUsername(userRequest.getUsername()) != null) {
//            throw new RuntimeException("Username is already taken.");
//        }
//        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
//
//        User savedUser = userRepository.save(userRequest);
//        String token = jwtService.generateToken(savedUser.getName(),savedUser.getRole());
//
//        return JwtResponseDTO.builder()
//                .accessToken(token)
//                .user(savedUser)
//                .build();
//    }

    public JwtResponseDTO login(AuthRequestDTO authRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword())
        );

        if (authentication.isAuthenticated()) {
            Person user =userRepository.findByUsername(authRequestDTO.getUsername());
            String token = jwtService.generateToken(user.getName(),user.getRole());

            return JwtResponseDTO.builder()
                    .accessToken(token)
                    .user(user)
                    .build();
        } else {
            throw new UsernameNotFoundException("Invalid user request.");
        }
    }
}
