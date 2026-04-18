package com.example.userinfoms.service;


import com.example.userinfoms.StudentInfo;
import com.example.userinfoms.enums.Role;
import com.example.userinfoms.repository.StudentInfoRepository;
import com.example.userinfoms.security.JwtUtil;
import com.example.userinfoms.security.LoginRequest;
import com.example.userinfoms.security.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final StudentInfoRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public void register(RegisterRequest request) {
        if (repository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        StudentInfo student = StudentInfo.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.STUDENT)
                .build();
        repository.save(student);
    }

    public String login(LoginRequest request) {
        StudentInfo student = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(request.getPassword(), student.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return jwtUtil.generateToken(student.getId(), student.getRole());
    }
}