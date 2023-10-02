package com.iagocharon.techforb.repository;

import com.iagocharon.techforb.entity.Card;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {
  Optional<Card> findById(int id);
  boolean existsById(int id);
}
