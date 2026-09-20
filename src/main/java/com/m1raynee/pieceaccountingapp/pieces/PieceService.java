package com.m1raynee.pieceaccountingapp.pieces;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.m1raynee.pieceaccountingapp.boxes.BoxEntity;
import com.m1raynee.pieceaccountingapp.boxes.BoxRepository;
import com.m1raynee.pieceaccountingapp.pieces.dto.PieceCreateDto;
import com.m1raynee.pieceaccountingapp.pieces.dto.PieceMapper;
import com.m1raynee.pieceaccountingapp.pieces.dto.PieceResponseDto;
import com.m1raynee.pieceaccountingapp.pieces.dto.PieceUpdateDto;

@Service
public class PieceService {
      private final PieceRepository repository;
      private final PieceMapper mapper;
      private final BoxRepository boxRepository;

      public PieceService(PieceRepository pieceRepository, PieceMapper pieceMapper, BoxRepository boxRepository) {
            this.repository = pieceRepository;
            this.mapper = pieceMapper;
            this.boxRepository = boxRepository;
      }

      public Page<PieceResponseDto> findAll(
                  String name,
                  String altName,
                  Long boxId,
                  Pageable pageable) {

            var probe = new PieceEntity();
            probe.setName(name);
            probe.setAltName(altName);

            var box = new BoxEntity();
            box.setId(boxId);
            probe.setBox(box);

            var matcher = ExampleMatcher.matching()
                        .withIgnoreCase()
                        .withIgnoreNullValues()
                        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

            return repository.findAll(
                        Example.of(probe, matcher), pageable)
                        .map(mapper::toDomain);
      }

      public PieceResponseDto findById(Long id) {
            return mapper.toDomain(repository.findOrThrow(id));
      }

      public PieceResponseDto create(Long boxId, PieceCreateDto dto) {
            var entity = mapper.toEntity(dto);
            entity.setBox(boxRepository.findOrThrow(boxId));
            return mapper.toDomain(repository.save(entity));
      }

      public PieceResponseDto update(Long id, PieceUpdateDto dto) {
            var entity = repository.findOrThrow(id);

            var box = dto.boxId() == null ? entity.getBox()
                        : boxRepository.findOrThrow(dto.boxId());

            mapper.updateEntity(entity, dto, box);
            return mapper.toDomain(repository.save(entity));
      }
}
