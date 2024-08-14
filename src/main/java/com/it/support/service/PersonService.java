package com.it.support.service;

import com.it.support.enums.Role;
import com.it.support.model.Admin;
import com.it.support.model.Person;
import com.it.support.model.Technicien;
import com.it.support.model.User;
import com.it.support.repository.AdminRepository;
import com.it.support.repository.PersonRepository;
import com.it.support.repository.TechnicianRepository;
import com.it.support.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class responsible for handling operations related to Person entities.
 * Implements the UserDetailsService interface for Spring Security integration.
 */
@Service
@RequiredArgsConstructor
public class PersonService implements UserDetailsService {

    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TechnicianRepository technicianRepository;
    private final AdminRepository adminRepository;

    /**
     * Loads a user by their username for authentication purposes.
     *
     * @param username The username of the user to load.
     * @return UserDetails object containing user information.
     * @throws UsernameNotFoundException if the user is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person user = personRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return org.springframework.security.core.userdetails.User
                .withUsername(username)
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build();
    }

    /**
     * Adds a new user to the system.
     *
     * @param user The User object to be added.
     * @return The saved User object.
     */
    public User addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        return userRepository.save(user);
    }

    /**
     * Adds a new technician to the system.
     *
     * @param technicien The Technicien object to be added.
     * @return The saved Technicien object.
     */
    public Technicien addTech(Technicien technicien) {
        technicien.setRole(Role.TECH);
        technicien.setPassword(passwordEncoder.encode(technicien.getPassword()));
        return technicianRepository.save(technicien);
    }

    /**
     * Adds a new admin to the system.
     *
     * @param admin The Admin object to be added.
     * @return The saved Admin object.
     */
    public Admin addAdmin(Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return adminRepository.save(admin);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public List<Technicien> getAllTechniciens() {
        return technicianRepository.findAll();
    }
}
