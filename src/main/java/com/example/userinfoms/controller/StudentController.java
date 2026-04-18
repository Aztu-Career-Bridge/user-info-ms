package com.example.userinfoms.controller;

import com.example.userinfoms.dto.StudentInfoDto;
import com.example.userinfoms.service.UserInfoService;
import com.example.userinfoms.shares.ApiResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {

    private final UserInfoService userInfoService;

    @PostMapping
    public ResponseEntity<ApiResponseDto<Void>> postStudent(
            @RequestBody @Valid StudentInfoDto studentInfoDto) {
        userInfoService.saveInfo(studentInfoDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponseDto.<Void>builder()
                        .data(null)
                        .status("Saved successfully")
                        .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<StudentInfoDto>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponseDto.<StudentInfoDto>builder()
                .data(userInfoService.getById(id))
                .status("OK")
                .build());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<ApiResponseDto<StudentInfoDto>> getByEmail(@PathVariable String email) {
        return ResponseEntity.ok(ApiResponseDto.<StudentInfoDto>builder()
                .data(userInfoService.getByEmail(email))
                .status("OK")
                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDto<StudentInfoDto>> update(
            @PathVariable Long id,
            @RequestBody @Valid StudentInfoDto studentInfoDto) {
        return ResponseEntity.ok(ApiResponseDto.<StudentInfoDto>builder()
                .data(userInfoService.updateInfo(id, studentInfoDto))
                .status("Updated successfully")
                .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto<Void>> delete(@PathVariable Long id) {
        userInfoService.deleteById(id);
        return ResponseEntity.ok(ApiResponseDto.<Void>builder()
                .data(null)
                .status("Deleted successfully")
                .build());
    }

}
