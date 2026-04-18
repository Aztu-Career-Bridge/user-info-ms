package com.example.userinfoms.service;

import com.example.userinfoms.dto.StudentInfoDto;


public interface UserInfoService {

    void saveInfo(StudentInfoDto studentInfoDto);

    StudentInfoDto getById(Long id);

    StudentInfoDto getByEmail(String email);

    StudentInfoDto updateInfo(Long id, StudentInfoDto studentInfoDto);

    void deleteById(Long id);
}

