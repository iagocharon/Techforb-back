package com.iagocharon.techforb.service;

import com.iagocharon.techforb.entity.Card;
import com.iagocharon.techforb.repository.CardRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CardService {

  @Autowired
  CardRepository cardRepository;

  public List<Card> list() {
    return cardRepository.findAll();
  }

  public Optional<Card> getById(int id) {
    return cardRepository.findById(id);
  }

  public boolean existsById(int id) {
    return cardRepository.existsById(id);
  }

  public void save(Card card) {
    cardRepository.save(card);
  }

  public void delete(int id) {
    cardRepository.deleteById(id);
  }
}
