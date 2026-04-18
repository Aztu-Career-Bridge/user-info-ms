package com.example.userinfoms.mapper;

import com.example.userinfoms.dto.StudentCriterias;
import com.example.userinfoms.dto.StudentCriteriasDto;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentCriteriasMapper {

    default StudentCriterias toEntity(StudentCriteriasDto dto) {
        if (dto == null) return null;
        return StudentCriterias.builder()
                .desiredLevel(dto.desiredLevel())
                .preferredCategories(
                        dto.preferredCategories() != null
                                ? String.join(",", dto.preferredCategories())
                                : null
                )
                .remoteAllowed(dto.remoteAllowed())
                .build();
    }

    default StudentCriteriasDto toDto(StudentCriterias entity) {
        if (entity == null) return null;
        List<String> categories = entity.getPreferredCategories() != null
                ? List.of(entity.getPreferredCategories().split(","))
                : new ArrayList<>();
        return new StudentCriteriasDto(
                entity.getDesiredLevel(),
                categories,
                entity.getRemoteAllowed()
        );
    }
}
