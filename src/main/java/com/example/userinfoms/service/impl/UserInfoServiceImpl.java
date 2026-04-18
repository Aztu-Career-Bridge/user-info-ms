package com.example.userinfoms.service.impl;

import com.example.userinfoms.StudentInfo;
import com.example.userinfoms.dto.StudentInfoDto;
import com.example.userinfoms.exception.StudentAlreadyExistsException;
import com.example.userinfoms.exception.StudentNotFoundException;
import com.example.userinfoms.mapper.StudentInfoMapper;
import com.example.userinfoms.repository.StudentInfoRepository;
import com.example.userinfoms.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserInfoServiceImpl implements UserInfoService {

    private final StudentInfoRepository studentInfoRepository;
    private final StudentInfoMapper studentInfoMapper;

    @Override
    @Transactional
    public void saveInfo(StudentInfoDto dto) {
        log.info("Saving student info for email: {}", dto.email());

        if (studentInfoRepository.existsByEmail(dto.email())) {
            throw new StudentAlreadyExistsException(dto.email());
        }

        StudentInfo saved = studentInfoRepository.save(studentInfoMapper.toEntity(dto));
        log.info("Student info saved with id: {}", saved.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public StudentInfoDto getById(Long id) {
        log.info("Fetching student by id: {}", id);
        return studentInfoRepository.findById(id)
                .map(studentInfoMapper::toDto)
                .orElseThrow(() -> new StudentNotFoundException("id", id.toString()));
    }

    @Override
    @Transactional(readOnly = true)
    public StudentInfoDto getByEmail(String email) {
        log.info("Fetching student by email: {}", email);
        return studentInfoRepository.findByEmail(email)
                .map(studentInfoMapper::toDto)
                .orElseThrow(() -> new StudentNotFoundException("email", email));
    }

    @Override
    @Transactional
    public StudentInfoDto updateInfo(Long id, StudentInfoDto dto) {
        log.info("Updating student info for id: {}", id);

        StudentInfo existing = studentInfoRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("id", id.toString()));

        existing.setName(dto.name());
        existing.setSurname(dto.surname());
        existing.setUniversity(dto.university());
        existing.setSpecialization(dto.specialization());
        existing.setGraduationYear(dto.graduationYear());
        existing.setSkills(dto.skills());
        existing.setStudentCriterias(studentInfoMapper.toEntity(dto).getStudentCriterias());

        StudentInfo updated = studentInfoRepository.save(existing);
        log.info("Student info updated for id: {}", updated.getId());
        return studentInfoMapper.toDto(updated);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        log.info("Deleting student info for id: {}", id);

        if (!studentInfoRepository.existsById(id)) {
            throw new StudentNotFoundException("id", id.toString());
        }

        studentInfoRepository.deleteById(id);
        log.info("Student info deleted for id: {}", id);
    }

}
