package com.it.support.service;

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

@Service
@RequiredArgsConstructor
public class PersonService implements UserDetailsService {

    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TechnicianRepository technicianRepository;
    private final AdminRepository adminRepository;

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
     public User addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
     }
     public Technicien addTech(Technicien technicien) {
        technicien.setPassword(passwordEncoder.encode(technicien.getPassword()));
        return technicianRepository.save(technicien);
     }
     public Admin addAdmin(Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return adminRepository.save(admin);
     }
}
