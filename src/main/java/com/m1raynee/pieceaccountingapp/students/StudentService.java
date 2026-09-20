package com.m1raynee.pieceaccountingapp.students;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private final StudentRepository repository;
    // private final StudentMapper mapper;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
        // this.mapper = mapper;
    }

    public Page<StudentResponseDto> findAll(String name, Pageable pageable) {
        return repository.findByName(name, pageable);
    }

    public StudentResponseDto findById(Long id) {
        var entity = repository.findOrThrow(id);
        return new StudentResponseDto(entity.getId(), entity.getName());
    }

    public StudentResponseDto createStudent(String name) {
        var entity = repository.save(new StudentEntity(name));
        return new StudentResponseDto(entity.getId(), entity.getName());
    }
}
