package com.m1raynee.pieceaccountingapp.pieces;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.m1raynee.pieceaccountingapp.pieces.dto.PieceCreateDto;
import com.m1raynee.pieceaccountingapp.pieces.dto.PieceResponseDto;
import com.m1raynee.pieceaccountingapp.web.PageResponse;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/pieces")
public class PieceController {
    private final PieceService pieceService;

    public PieceController(PieceService pieceService) {
        this.pieceService = pieceService;
    }

    @GetMapping("/")
    public ResponseEntity<PageResponse<PieceResponseDto>> getAllPieces(
            @RequestParam(required = false) String name,
            @RequestParam(name = "alt_name", required = false) String altName,
            @RequestParam(name = "box_id", required = false) Long boxId,
            @PageableDefault(page = 0, size = 20, sort = "name") Pageable pageable) {
        return ResponseEntity.ok(
                PageResponse.from(pieceService.findAll(
                        name,
                        altName,
                        boxId,
                        pageable)));
    }

    @PostMapping("/")
    public ResponseEntity<PieceResponseDto> postPiece(
            @Valid @RequestBody PieceCreateDto dto) {
        return ResponseEntity.ok(pieceService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PieceResponseDto> getPieceById(@PathVariable() Long id) {
        return ResponseEntity.ok(pieceService.findById(id));
    }

}
