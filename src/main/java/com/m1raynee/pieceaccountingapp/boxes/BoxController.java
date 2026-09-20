package com.m1raynee.pieceaccountingapp.boxes;

import com.m1raynee.pieceaccountingapp.web.PageResponse;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/boxes")
public class BoxController {

    private final BoxService boxService;

    public BoxController(BoxService boxService) {
        this.boxService = boxService;
    }

    @GetMapping
    public PageResponse<BoxResponseDto> getAllBoxes(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String placeCode,
            @PageableDefault(page = 0, size = 20, sort = "place_code") Pageable pageable) {
        return PageResponse.from(boxService.findAll(name, placeCode, pageable));
    }

}
