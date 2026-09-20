package com.m1raynee.pieceaccountingapp.pieces.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PieceCreateDto(
        String article,
        @NotBlank(message = "piece must contain a name") String name,
        String altName,

        @NotNull(message = "box_id is required") @Positive(message = "box_id must be positive") Long boxId,
        String cellHint) {
}
