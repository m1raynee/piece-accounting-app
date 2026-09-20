package com.m1raynee.pieceaccountingapp.pieces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.m1raynee.pieceaccountingapp.pieces.dto.PieceResponseDto;
import com.m1raynee.pieceaccountingapp.repository.BaseRepository;
import com.m1raynee.pieceaccountingapp.boxes.BoxEntity;

public interface PieceRepository extends BaseRepository<PieceEntity, Long> {

    Page<PieceResponseDto> findAllByBox(BoxEntity box, Pageable pageable);

    Page<PieceResponseDto> findAllByBox_Id(Long box_Id, Pageable pageable);

    @Query("""
            select coalesce(sum(
                case
                    when m.type in (
                        com.m1raynee.pieceaccountingapp.loans.StockMovementType.INCOMING,
                        com.m1raynee.pieceaccountingapp.loans.StockMovementType.RETURN,
                        com.m1raynee.pieceaccountingapp.loans.StockMovementType.ADJUSTMENT_INCREASE
                    ) then m.amount
                    else -m.amount
                end
            ), 0)
            from StockMovementEntity m
            where m.piece.id = :id
            """)
    long getQuantity(Long id);
}
