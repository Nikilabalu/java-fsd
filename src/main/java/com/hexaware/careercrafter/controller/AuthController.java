package com.hexaware.careercrafter.controller;

import com.hexaware.careercrafter.utility.JwtUtility;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final JwtUtility jwtUtility;

    @GetMapping("/login")
    public ResponseEntity<?> login(Principal principal){
        if (principal == null) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Principal is null - authentication failed");
        }

        String loggedInUser = principal.getName();
        Map map = new HashMap<>();
        map.put("token", jwtUtility.generateToken(loggedInUser));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(map);
    }

}