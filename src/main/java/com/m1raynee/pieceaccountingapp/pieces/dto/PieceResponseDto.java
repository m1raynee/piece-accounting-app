package com.m1raynee.pieceaccountingapp.pieces.dto;

public record PieceResponseDto(
        Long id,
        String name,
        String altName,
        String cellHint,
        Long boxId) {
}
