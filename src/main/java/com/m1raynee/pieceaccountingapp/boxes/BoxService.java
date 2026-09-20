package com.m1raynee.pieceaccountingapp.boxes;

import java.util.NoSuchElementException;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.m1raynee.pieceaccountingapp.pieces.dto.PieceResponseDto;

@Service
public class BoxService {
    private final BoxRepository repository;
    private final BoxMapper mapper;

    public BoxService(BoxRepository repository, BoxMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
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

    // List<BoxEntity> findAll();

    // Optional<BoxEntity> findById(Long id);

    // Optional<BoxEntity> findByIndex(Integer index);

    // BoxEntity save(BoxEntity box);

    // void deleteById(Long id);

    // void deleteByIndex(Integer index);

    // List<BoxEntity> findByNameContaining(String name);
}
