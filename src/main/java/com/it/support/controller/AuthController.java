package com.it.support.controller;

import com.it.support.dto.AuthRequestDTO;
import com.it.support.dto.JwtResponseDTO;
import com.it.support.model.Admin;
import com.it.support.model.Technicien;
import com.it.support.model.User;
import com.it.support.service.PersonService;
import com.it.support.service.UserAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthController {

    private final UserAuthService userAuthService;
    private final PersonService personService;

    @PostMapping("/public/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestDTO authRequestDTO) {
        try {
            JwtResponseDTO jwtResponseDTO = userAuthService.login(authRequestDTO);
            return ResponseEntity.ok(jwtResponseDTO);
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }



    @PostMapping("/admin/addUser")
    //@PreAuthorize("hasRole('ADMIN')")
    public User addUser(@RequestBody User user) {
        return personService.addUser(user);
    }
    @PostMapping("admin/addTech")
    //@PreAuthorize("hasRole('ADMIN')")
    public Technicien addTech(@RequestBody Technicien technicien) {
        return personService.addTech(technicien);
    }
    @PostMapping("/admin/addAdmin")
    //@PreAuthorize("hasRole('ADMIN')")
    public Admin addAdmin(@RequestBody Admin admin) {
        return personService.addAdmin(admin);
    }

}
