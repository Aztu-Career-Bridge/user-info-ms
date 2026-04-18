package com.example.userinfoms.mapper;

import com.example.userinfoms.StudentInfo;
import com.example.userinfoms.dto.StudentInfoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {StudentCriteriasMapper.class})
public interface StudentInfoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    StudentInfo toEntity(StudentInfoDto dto);

    StudentInfoDto toDto(StudentInfo entity);
}