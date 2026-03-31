package com.hexaware.careercrafter.controller;

import com.hexaware.careercrafter.dto.RegisterReqDto;
import com.hexaware.careercrafter.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterReqDto registerReqDto) {
        userService.register(registerReqDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    // login is now handled by GET /api/auth/login in AuthController

}