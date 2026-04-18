package com.example.userinfoms.dto;

import java.util.List;

public record StudentInfoDto(

        String name,
        String surname,
        String email,

        String university,
        String specialization,
        Integer graduationYear,

        List<String> skills,

        StudentCriterias studentCriterias

) {}
