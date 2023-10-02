package com.iagocharon.techforb.controller;

import com.iagocharon.techforb.dto.Message;
import com.iagocharon.techforb.entity.Transaction;
import com.iagocharon.techforb.entity.User;
import com.iagocharon.techforb.enums.TransactionType;
import com.iagocharon.techforb.service.TransactionService;
import com.iagocharon.techforb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

  @Autowired
  UserService userService;

  @Autowired
  TransactionService transactionService;

  @PutMapping("/{id}/deposit")
  public ResponseEntity<?> deposit(
    @PathVariable("id") int id,
    @RequestBody double amount
  ) {
    if (!userService.existsById(id)) {
      return new ResponseEntity<Message>(
        new Message("User not found"),
        HttpStatus.NOT_FOUND
      );
    }
    User user = userService.getById(id).get();
    user.setBalance(user.getBalance() + amount);

    Transaction transaction = new Transaction(
      null,
      user,
      TransactionType.DEPOSIT,
      amount
    );

    user.addIncomingTransaction(transaction);

    transactionService.save(transaction);
    userService.save(user);

    return new ResponseEntity<Message>(
      new Message("User updated"),
      HttpStatus.OK
    );
  }

  @PutMapping("/{id}/withdraw")
  public ResponseEntity<?> withdraw(
    @PathVariable("id") int id,
    @RequestBody double amount
  ) {
    if (!userService.existsById(id)) {
      return new ResponseEntity<Message>(
        new Message("User not found"),
        HttpStatus.NOT_FOUND
      );
    }
    User user = userService.getById(id).get();
    if (user.getBalance() < amount) {
      return new ResponseEntity<Message>(
        new Message("Insufficient funds"),
        HttpStatus.BAD_REQUEST
      );
    }
    user.setBalance(user.getBalance() - amount);

    Transaction transaction = new Transaction(
      user,
      null,
      TransactionType.WITHDRAW,
      amount
    );

    user.addOutgoingTransaction(transaction);

    transactionService.save(transaction);
    userService.save(user);

    return new ResponseEntity<Message>(
      new Message("User updated"),
      HttpStatus.OK
    );
  }

  @PutMapping("/{id}/transfer/{targetId}")
  public ResponseEntity<?> transfer(
    @PathVariable("id") int id,
    @PathVariable("targetId") int targetId,
    @RequestBody double amount
  ) {
    if (!userService.existsById(id)) {
      return new ResponseEntity<Message>(
        new Message("User not found"),
        HttpStatus.NOT_FOUND
      );
    }
    if (!userService.existsById(targetId)) {
      return new ResponseEntity<Message>(
        new Message("Target user not found"),
        HttpStatus.NOT_FOUND
      );
    }
    User user = userService.getById(id).get();
    User target = userService.getById(targetId).get();
    if (user.getBalance() < amount) {
      return new ResponseEntity<Message>(
        new Message("Insufficient funds"),
        HttpStatus.BAD_REQUEST
      );
    }

    user.setBalance(user.getBalance() - amount);
    target.setBalance(target.getBalance() + amount);

    Transaction transaction = new Transaction(
      user,
      target,
      TransactionType.TRANSFER,
      amount
    );

    user.addOutgoingTransaction(transaction);
    target.addIncomingTransaction(transaction);

    transactionService.save(transaction);
    userService.save(user);

    return new ResponseEntity<Message>(
      new Message("Users updated"),
      HttpStatus.OK
    );
  }
}
