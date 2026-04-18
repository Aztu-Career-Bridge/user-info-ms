package com.example.userinfoms.controller;


import com.example.userinfoms.service.AuthService;
import com.example.userinfoms.shares.ApiResponseDto;
import com.example.userinfoms.util.LoginRequest;
import com.example.userinfoms.util.RegisterRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponseDto<Void>> register(@RequestBody @Valid RegisterRequest request) {
        authService.register(request);
        return ResponseEntity.ok(ApiResponseDto.<Void>builder()
                .status("Registered successfully").build());
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponseDto<String>> login(
            @RequestBody @Valid LoginRequest request) {
        String token = authService.login(request);
        return ResponseEntity.ok(ApiResponseDto.<String>builder()
                .data(token)
                .status("OK").build());
    }
}