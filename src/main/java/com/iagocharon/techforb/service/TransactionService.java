package com.iagocharon.techforb.service;

import com.iagocharon.techforb.entity.Transaction;
import com.iagocharon.techforb.repository.TransactionRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TransactionService {

  @Autowired
  TransactionRepository transactionRepository;

  public List<Transaction> list() {
    return transactionRepository.findAll();
  }

  public Optional<Transaction> getById(int id) {
    return transactionRepository.findById(id);
  }

  public boolean existsById(int id) {
    return transactionRepository.existsById(id);
  }

  public int save(Transaction transaction) {
    Transaction savedTransaction = transactionRepository.save(transaction);
    return savedTransaction.getId();
  }

  public void delete(int id) {
    transactionRepository.deleteById(id);
  }
}
