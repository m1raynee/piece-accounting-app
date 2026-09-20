package com.m1raynee.pieceaccountingapp.boxes;

/**
 * BoxResponseDto
 */
public record BoxResponseDto(
        Long id,
        Integer index,
        String name,
        String placeCode) {
}
