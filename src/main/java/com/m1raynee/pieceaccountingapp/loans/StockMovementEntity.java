package com.m1raynee.pieceaccountingapp.loans;

import java.time.LocalDateTime;

import com.m1raynee.pieceaccountingapp.pieces.PieceEntity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "stock_movements")
@Data
@NoArgsConstructor
public class StockMovementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "loan_id")
    private LoanEntity loan;

    @ManyToOne
    @JoinColumn(name = "piece_id")
    private PieceEntity piece;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StockMovementType type;

    @Column(nullable = false)
    private Integer amount;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public StockMovementEntity(
            LoanEntity loan,
            PieceEntity piece,
            StockMovementType type,
            Integer amount) {
        this.loan = loan;
        this.piece = piece;
        this.type = type;
        this.amount = amount;
    }

    @PrePersist
    private void onCreate() {
        validateAmount();
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    private void onUpdate() {
        validateAmount();
    }

    private void validateAmount() {
        if (type == null) {
            throw new IllegalArgumentException("Movement type is required");
        }
        if (amount == null || amount <= 0) {
            throw new IllegalArgumentException("Movement amount must be positive");
        }
    }

    public long getSignedAmount() {
        return (long) amount * type.getDirection();
    }
}
