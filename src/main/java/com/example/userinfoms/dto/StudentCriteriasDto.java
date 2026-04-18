package com.example.userinfoms.dto;

import java.util.List;

public record StudentCriteriasDto(

        String desiredLevel,

        List<String> preferredCategories,

        Boolean remoteAllowed

) {}
