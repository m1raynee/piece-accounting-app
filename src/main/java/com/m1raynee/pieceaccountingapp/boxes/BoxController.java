package com.m1raynee.pieceaccountingapp.boxes;

import com.m1raynee.pieceaccountingapp.boxes.dto.BoxResponseDto;
import com.m1raynee.pieceaccountingapp.pieces.PieceService;
import com.m1raynee.pieceaccountingapp.pieces.dto.PieceCreateDto;
import com.m1raynee.pieceaccountingapp.pieces.dto.PieceResponseDto;
import com.m1raynee.pieceaccountingapp.web.PageResponse;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/boxes")
public class BoxController {

    private final PieceService pieceService;
    private final BoxService boxService;

    public BoxController(BoxService boxService, PieceService pieceService) {
        this.boxService = boxService;
        this.pieceService = pieceService;
    }

    @GetMapping
    public PageResponse<BoxResponseDto> getAllBoxes(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String placeCode,
            @PageableDefault(page = 0, size = 20, sort = "place_code") Pageable pageable) {
        return PageResponse.from(boxService.findAll(name, placeCode, pageable));
    }

    @GetMapping("/{id}")
    public BoxResponseDto getBoxById(@PathVariable Long id) {
        return boxService.findById(id);
    }

    @GetMapping("/{id}/pieces")
    public PageResponse<PieceResponseDto> getAllBoxPieces(
            @PathVariable Long id,
            @PageableDefault(page = 0, size = 25) Pageable pageable) {
        return PageResponse.from(boxService.getBoxPieces(id, pageable));
    }

    @PostMapping("/{id}/pieces")
    public PieceResponseDto postPieceToBox(@PathVariable Long id, @RequestBody PieceCreateDto dto) {
        return pieceService.create(id, dto);
    }

}
