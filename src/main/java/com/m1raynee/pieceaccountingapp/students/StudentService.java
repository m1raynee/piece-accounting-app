package com.m1raynee.pieceaccountingapp.students;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<StudentEntity> findAll();
    List<StudentEntity> findAllWithActiveLoans();
    Optional<StudentEntity> findById(Long id);
    StudentEntity save(StudentEntity piece);
    void deleteById(Long id);
    List<StudentEntity> findByNameContaining(String name);
}
