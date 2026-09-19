package com.m1raynee.pieceaccountingapp.service;

import java.util.List;
import java.util.Optional;

import com.m1raynee.pieceaccountingapp.entity.Student;

public interface StudentService {
    List<Student> findAll();
    List<Student> findAllWithActiveLoans();
    Optional<Student> findById(Long id);
    Student save(Student piece);
    void deleteById(Long id);
    List<Student> findByNameContaining(String name);
}
