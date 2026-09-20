package com.m1raynee.pieceaccountingapp.students;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.m1raynee.pieceaccountingapp.repository.BaseRepository;

public interface StudentRepository extends BaseRepository<StudentEntity, Long> {
    Page<StudentResponseDto> findByName(String name, Pageable pageable);
}
