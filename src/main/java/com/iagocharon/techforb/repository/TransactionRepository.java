package com.iagocharon.techforb.repository;

import com.iagocharon.techforb.entity.Transaction;
import com.iagocharon.techforb.enums.TransactionType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository
  extends JpaRepository<Transaction, Integer> {
  Optional<Transaction> findById(int id);
  Optional<Transaction> findByType(TransactionType type);
  boolean existsById(int id);
  boolean existsByType(TransactionType type);
}
