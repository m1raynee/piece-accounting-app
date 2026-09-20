package com.m1raynee.pieceaccountingapp.boxes;

import com.m1raynee.pieceaccountingapp.pieces.PieceRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.m1raynee.pieceaccountingapp.boxes.dto.BoxMapper;
import com.m1raynee.pieceaccountingapp.boxes.dto.BoxResponseDto;
import com.m1raynee.pieceaccountingapp.pieces.dto.PieceResponseDto;

@Service
public class BoxService {

    private final PieceRepository pieceRepository;
    private final BoxRepository repository;
    private final BoxMapper mapper;

    public BoxService(BoxRepository repository, BoxMapper mapper, PieceRepository pieceRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.pieceRepository = pieceRepository;
    }

    public Page<BoxResponseDto> findAll(String name, String placeCode, Pageable pageable) {
        var probe = new BoxEntity();
        probe.setName(name);
        probe.setPlaceCode(placeCode);

        var matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withIgnoreNullValues()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        return repository.findAll(Example.of(probe, matcher), pageable)
                .map(mapper::toDomain);
    }

    public BoxResponseDto findById(Long id) {
        return mapper.toDomain(repository.findOrThrow(id));
    }

    public Page<PieceResponseDto> getBoxPieces(Long id, Pageable pageable) {
        return pieceRepository.findAllByBox(
                repository.findOrThrow(id), pageable);

    }

}
