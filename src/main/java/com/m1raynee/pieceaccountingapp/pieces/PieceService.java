package com.m1raynee.pieceaccountingapp.pieces;

import java.util.NoSuchElementException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

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
            var spec = Specification
                        .where(PieceSpecifications.hasName(name))
                        .and(PieceSpecifications.hasAltName(altName))
                        .and(PieceSpecifications.hasBoxId(boxId));

            return repository.findAll(spec, pageable)
                        .map(mapper::toDomain);
      }

      // PieceEntity save(PieceEntity piece);
      // void deleteById(Long id);

      public PieceResponseDto findById(Long id) {
            var entity = repository.findById(id)
                        .orElseThrow(
                                    () -> new NoSuchElementException(
                                                "Couldn't find piece with id: " + id));

            return mapper.toDomain(entity);
      }

      public PieceResponseDto create(PieceCreateDto dto) {
            var box = boxRepository.findById(dto.boxId())
                        .orElseThrow(
                                    () -> new NoSuchElementException(
                                                "Couldn't find box with id: " + dto.boxId()));

            var entity = mapper.toEntity(dto);
            entity.setBox(box);

            return mapper.toDomain(repository.save(entity));
      }

      public PieceResponseDto update(Long id, PieceUpdateDto dto) {
            var entity = repository.findById(id)
                        .orElseThrow(
                                    () -> new NoSuchElementException(
                                                "Couldn't find piece with id: " + id));

            var box = dto.boxId() == null ? entity.getBox()
                        : boxRepository.findById(dto.boxId())
                                    .orElseThrow(
                                                () -> new NoSuchElementException(
                                                            "Couldn't find box with id: " + dto.boxId()));

            mapper.updateEntity(entity, dto, box);
            return mapper.toDomain(repository.save(entity));
      }

}
