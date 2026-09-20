package com.m1raynee.pieceaccountingapp.pieces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PieceRepository extends JpaRepository<PieceEntity, Long>, JpaSpecificationExecutor<PieceEntity> {
}
