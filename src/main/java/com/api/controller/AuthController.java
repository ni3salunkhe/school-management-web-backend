package com.api.controller;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.dto.LoginDto;
import com.api.entity.Developer;
import com.api.entity.School;
import com.api.entity.Staff;
import com.api.service.DeveloperService;
import com.api.service.SchoolService;
import com.api.service.StaffService;
import com.api.util.JwtUtil;

@RestController
@RequestMapping("/public")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private DeveloperService developerService;

    @Autowired
    private StaffService staffService;

    @Autowired
    private SchoolService schoolService;

    @Autowired
    private JwtUtil jwtUtil;

	@PostMapping("/authenticate")
public ResponseEntity<?> authenticate(@RequestBody LoginDto loginRequest) {
    try {
        // 1. Validate credentials using Spring Security
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
            )
        );

        // 2. Continue only if credentials are valid
        Object user = null;
        String jwt = "";

        switch (loginRequest.getUserType().toUpperCase()) {
            case "DEVELOPER":
                Developer developer = developerService.getByUsername(loginRequest.getUsername());
                if (developer != null) user = developer;
                break;

            case "STAFF":
                Staff staff = staffService.getByUsername(loginRequest.getUsername());
                if (staff != null) user = staff;
                break;

            case "HEADMASTER":
                School school = schoolService.getByUsername(loginRequest.getUsername());
                if (school != null) user = school;
                break;

            default:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid user type");
        }

        if (user != null) {
            jwt = jwtUtil.generateTokenBasedOnRole(user);
            Map<String, String> response = new HashMap<>();
            response.put("token", jwt);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

    } catch (Exception e) {
        // Catch invalid credentials
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
    }
}

}