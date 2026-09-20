package com.m1raynee.pieceaccountingapp.loans;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.m1raynee.pieceaccountingapp.repository.BaseRepository;

public interface LoanRepository extends BaseRepository<LoanEntity, Long> {
    Page<LoanEntity> findAllByStatus(LoanStatus status, Pageable pageable);
}
