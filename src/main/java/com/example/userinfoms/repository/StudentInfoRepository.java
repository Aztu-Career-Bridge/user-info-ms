package com.example.userinfoms.repository;

import com.example.userinfoms.StudentInfo;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentInfoRepository extends JpaRepository<StudentInfo, Long> {

    Optional<StudentInfo> findByEmail(String email);

    boolean existsByEmail(String email);

}
