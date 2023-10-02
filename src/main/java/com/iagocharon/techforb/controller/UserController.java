package com.iagocharon.techforb.controller;

import com.iagocharon.techforb.dto.CardDto;
import com.iagocharon.techforb.dto.Message;
import com.iagocharon.techforb.dto.UserDto;
import com.iagocharon.techforb.entity.Card;
import com.iagocharon.techforb.entity.Transaction;
import com.iagocharon.techforb.entity.User;
import com.iagocharon.techforb.jwt.JwtProvider;
import com.iagocharon.techforb.service.CardService;
import com.iagocharon.techforb.service.TransactionService;
import com.iagocharon.techforb.service.UserService;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  PasswordEncoder passwordEncoder;

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserService userService;

  @Autowired
  TransactionService transactionService;

  @Autowired
  CardService cardService;

  @Autowired
  JwtProvider jwtProvider;

  @GetMapping("/list")
  public ResponseEntity<?> getAllUsers() {
    List<User> users = userService.list();
    return new ResponseEntity<>(users, HttpStatus.OK);
  }

  @GetMapping("/{id}/detail")
  public ResponseEntity<?> getUserById(@PathVariable("id") int id) {
    if (!userService.existsById(id)) {
      return new ResponseEntity<Message>(
        new Message("User not found"),
        HttpStatus.NOT_FOUND
      );
    }
    User user = userService.getById(id).get();
    return new ResponseEntity<>(user, HttpStatus.OK);
  }

  @GetMapping("/{id}/balance")
  public ResponseEntity<?> getBalance(@PathVariable("id") int id) {
    if (!userService.existsById(id)) {
      return new ResponseEntity<Message>(
        new Message("User not found"),
        HttpStatus.NOT_FOUND
      );
    }
    User user = userService.getById(id).get();
    return new ResponseEntity<>(user.getBalance(), HttpStatus.OK);
  }

  @GetMapping("/{id}/transactions")
  public ResponseEntity<?> getTransactions(@PathVariable("id") int id) {
    if (!userService.existsById(id)) {
      return new ResponseEntity<Message>(
        new Message("User not found"),
        HttpStatus.NOT_FOUND
      );
    }

    User user = userService.getById(id).get();

    List<Transaction> incoming = user.getIncomingTransactions();
    List<Transaction> outgoing = user.getOutgoingTransactions();
    List<Transaction> transactions = new ArrayList<>();
    transactions.addAll(incoming);
    transactions.addAll(outgoing);

    return new ResponseEntity<>(transactions, HttpStatus.OK);
  }

  @GetMapping("/{id}/monthly-incoming-transactions")
  public ResponseEntity<?> getMonthlyIncomingTransactions(
    @PathVariable("id") int id
  ) {
    if (!userService.existsById(id)) {
      return new ResponseEntity<Message>(
        new Message("User not found"),
        HttpStatus.NOT_FOUND
      );
    }

    User user = userService.getById(id).get();

    List<Transaction> incoming = user.getIncomingTransactions();
    List<Transaction> monthlyIncoming = new ArrayList<>();
    LocalDateTime currentDate = LocalDateTime.now();

    for (Transaction transaction : incoming) {
      LocalDateTime date = transaction.getDate();

      long daysDifference = ChronoUnit.DAYS.between(date, currentDate);

      if (daysDifference <= 30) {
        monthlyIncoming.add(transaction);
      }
    }
    return new ResponseEntity<>(monthlyIncoming, HttpStatus.OK);
  }

  @GetMapping("/{id}/monthly-outgoing-transactions")
  public ResponseEntity<?> getMonthlyOutgoingTransactions(
    @PathVariable("id") int id
  ) {
    if (!userService.existsById(id)) {
      return new ResponseEntity<Message>(
        new Message("User not found"),
        HttpStatus.NOT_FOUND
      );
    }

    User user = userService.getById(id).get();

    List<Transaction> outgoing = user.getOutgoingTransactions();
    List<Transaction> monthlyOutgoing = new ArrayList<>();
    LocalDateTime currentDate = LocalDateTime.now();

    for (Transaction transaction : outgoing) {
      LocalDateTime date = transaction.getDate();

      long daysDifference = ChronoUnit.DAYS.between(date, currentDate);

      if (daysDifference <= 30) {
        monthlyOutgoing.add(transaction);
      }
    }

    return new ResponseEntity<>(monthlyOutgoing, HttpStatus.OK);
  }

  @GetMapping("/{id}/weekly-incoming-transactions")
  public ResponseEntity<?> getWeeklyIncomingTransactions(
    @PathVariable("id") int id
  ) {
    if (!userService.existsById(id)) {
      return new ResponseEntity<Message>(
        new Message("User not found"),
        HttpStatus.NOT_FOUND
      );
    }

    User user = userService.getById(id).get();

    List<Transaction> incoming = user.getIncomingTransactions();
    List<Transaction> weeklyIncoming = new ArrayList<>();
    LocalDateTime currentDate = LocalDateTime.now();

    for (Transaction transaction : incoming) {
      LocalDateTime date = transaction.getDate();

      long daysDifference = ChronoUnit.DAYS.between(date, currentDate);

      if (daysDifference <= 7) {
        weeklyIncoming.add(transaction);
      }
    }

    return new ResponseEntity<>(weeklyIncoming, HttpStatus.OK);
  }

  @GetMapping("/{id}/weekly-outgoing-transactions")
  public ResponseEntity<?> getWeeklyOutgoingTransactions(
    @PathVariable("id") int id
  ) {
    if (!userService.existsById(id)) {
      return new ResponseEntity<Message>(
        new Message("User not found"),
        HttpStatus.NOT_FOUND
      );
    }

    User user = userService.getById(id).get();

    List<Transaction> outgoing = user.getOutgoingTransactions();
    List<Transaction> weeklyOutgoing = new ArrayList<>();
    LocalDateTime currentDate = LocalDateTime.now();

    for (Transaction transaction : outgoing) {
      LocalDateTime date = transaction.getDate();

      long daysDifference = ChronoUnit.DAYS.between(date, currentDate);

      if (daysDifference <= 7) {
        weeklyOutgoing.add(transaction);
      }
    }

    return new ResponseEntity<>(weeklyOutgoing, HttpStatus.OK);
  }

  @GetMapping("/{id}/last-week-incoming-transactions")
  public ResponseEntity<?> getLastWeekIncomingTransactions(
    @PathVariable("id") int id
  ) {
    if (!userService.existsById(id)) {
      return new ResponseEntity<Message>(
        new Message("User not found"),
        HttpStatus.NOT_FOUND
      );
    }
    User user = userService.getById(id).get();

    List<Transaction> incoming = user.getIncomingTransactions();
    List<Transaction> lastWeekIncoming = new ArrayList<>();
    LocalDateTime currentDate = LocalDateTime.now();

    for (Transaction transaction : incoming) {
      LocalDateTime date = transaction.getDate();

      long daysDifference = ChronoUnit.DAYS.between(date, currentDate);

      if (daysDifference <= 14 && daysDifference > 7) {
        lastWeekIncoming.add(transaction);
      }
    }

    return new ResponseEntity<>(lastWeekIncoming, HttpStatus.OK);
  }

  @GetMapping("/{id}/last-week-outgoing-transactions")
  public ResponseEntity<?> getLastWeekOutgoingTransactions(
    @PathVariable("id") int id
  ) {
    if (!userService.existsById(id)) {
      return new ResponseEntity<Message>(
        new Message("User not found"),
        HttpStatus.NOT_FOUND
      );
    }
    User user = userService.getById(id).get();

    List<Transaction> outgoing = user.getOutgoingTransactions();
    List<Transaction> lastWeekOutgoing = new ArrayList<>();
    LocalDateTime currentDate = LocalDateTime.now();

    for (Transaction transaction : outgoing) {
      LocalDateTime date = transaction.getDate();

      long daysDifference = ChronoUnit.DAYS.between(date, currentDate);

      if (daysDifference <= 14 && daysDifference > 7) {
        lastWeekOutgoing.add(transaction);
      }
    }

    return new ResponseEntity<>(lastWeekOutgoing, HttpStatus.OK);
  }

  @GetMapping("/{id}/monthly-income")
  public ResponseEntity<?> getMonthlyIncome(@PathVariable("id") int id) {
    if (!userService.existsById(id)) {
      return new ResponseEntity<Message>(
        new Message("User not found"),
        HttpStatus.NOT_FOUND
      );
    }

    User user = userService.getById(id).get();
    double total = 0;
    List<Transaction> incoming = user.getIncomingTransactions();
    LocalDateTime currentDate = LocalDateTime.now();

    for (Transaction transaction : incoming) {
      LocalDateTime date = transaction.getDate();

      long daysDifference = ChronoUnit.DAYS.between(date, currentDate);

      if (daysDifference <= 30) {
        total += transaction.getAmount();
      }
    }

    return new ResponseEntity<>(total, HttpStatus.OK);
  }

  @GetMapping("/{id}/last-month-income")
  public ResponseEntity<?> getLastMonthIncome(@PathVariable("id") int id) {
    if (!userService.existsById(id)) {
      return new ResponseEntity<Message>(
        new Message("User not found"),
        HttpStatus.NOT_FOUND
      );
    }

    User user = userService.getById(id).get();
    double total = 0;
    List<Transaction> incoming = user.getIncomingTransactions();
    LocalDateTime currentDate = LocalDateTime.now();

    for (Transaction transaction : incoming) {
      LocalDateTime date = transaction.getDate();

      long daysDifference = ChronoUnit.DAYS.between(date, currentDate);

      if (daysDifference <= 60 && daysDifference > 30) {
        total += transaction.getAmount();
      }
    }

    return new ResponseEntity<>(total, HttpStatus.OK);
  }

  @GetMapping("/{id}/monthly-expenses")
  public ResponseEntity<?> getMonthlyExpenses(@PathVariable("id") int id) {
    if (!userService.existsById(id)) {
      return new ResponseEntity<Message>(
        new Message("User not found"),
        HttpStatus.NOT_FOUND
      );
    }

    User user = userService.getById(id).get();
    double total = 0;
    List<Transaction> outgoing = user.getOutgoingTransactions();
    LocalDateTime currentDate = LocalDateTime.now();

    for (Transaction transaction : outgoing) {
      LocalDateTime date = transaction.getDate();

      long daysDifference = ChronoUnit.DAYS.between(date, currentDate);

      if (daysDifference <= 30) {
        total += transaction.getAmount();
      }
    }

    return new ResponseEntity<>(total, HttpStatus.OK);
  }

  @GetMapping("/{id}/last-month-expenses")
  public ResponseEntity<?> getLastMonthExpenses(@PathVariable("id") int id) {
    if (!userService.existsById(id)) {
      return new ResponseEntity<Message>(
        new Message("User not found"),
        HttpStatus.NOT_FOUND
      );
    }

    User user = userService.getById(id).get();
    double total = 0;
    List<Transaction> outgoing = user.getOutgoingTransactions();
    LocalDateTime currentDate = LocalDateTime.now();

    for (Transaction transaction : outgoing) {
      LocalDateTime date = transaction.getDate();

      long daysDifference = ChronoUnit.DAYS.between(date, currentDate);

      if (daysDifference <= 60 && daysDifference > 30) {
        total += transaction.getAmount();
      }
    }

    return new ResponseEntity<>(total, HttpStatus.OK);
  }

  @PutMapping("/{id}/add-card")
  public ResponseEntity<?> addCard(
    @PathVariable("id") int id,
    @RequestBody CardDto cardDto
  ) {
    if (!userService.existsById(id)) {
      return new ResponseEntity<>(
        new Message("User not found"),
        HttpStatus.NOT_FOUND
      );
    }
    User existingUser = userService.getById(id).get();
    Card card = new Card(
      cardDto.getName(),
      cardDto.getNumber(),
      cardDto.getCvv(),
      cardDto.getExpirationDate(),
      cardDto.getIssuer()
    );
    card.setUser(existingUser);
    existingUser.addCard(card);

    cardService.save(card);
    userService.save(existingUser);

    return new ResponseEntity<>(new Message("Card added"), HttpStatus.OK);
  }

  @DeleteMapping("/{id}/delete-card/{cardId}")
  public ResponseEntity<?> deleteCard(
    @PathVariable("id") int id,
    @PathVariable("cardId") int cardId
  ) {
    if (!userService.existsById(id)) {
      return new ResponseEntity<>(
        new Message("User not found"),
        HttpStatus.NOT_FOUND
      );
    }
    User existingUser = userService.getById(id).get();
    if (!cardService.existsById(cardId)) {
      return new ResponseEntity<>(
        new Message("Card not found"),
        HttpStatus.NOT_FOUND
      );
    }
    Card existingCard = cardService.getById(cardId).get();
    existingUser.getCards().remove(existingCard);
    existingCard.setUser(null);
    cardService.delete(cardId);
    userService.save(existingUser);

    return new ResponseEntity<>(new Message("Card deleted"), HttpStatus.OK);
  }

  @PutMapping("/{id}/update")
  public ResponseEntity<?> updateUser(
    @PathVariable("id") int id,
    @Valid @RequestBody UserDto userDto
  ) {
    if (!userService.existsById(id)) {
      return new ResponseEntity<>(
        new Message("User not found"),
        HttpStatus.NOT_FOUND
      );
    }

    User existingUser = userService.getById(id).get();
    existingUser.setName(userDto.getName());
    existingUser.setEmail(userDto.getEmail());
    existingUser.setPhone(userDto.getPhone());
    existingUser.setDni(userDto.getDni());

    userService.save(existingUser);
    return new ResponseEntity<>(new Message("User updated"), HttpStatus.OK);
  }

  @DeleteMapping("/{id}/delete")
  public ResponseEntity<?> deleteUser(@PathVariable int id) {
    if (!userService.existsById(id)) {
      return new ResponseEntity<Message>(
        new Message("User not found"),
        HttpStatus.NOT_FOUND
      );
    }

    userService.delete(id);

    return new ResponseEntity<Message>(
      new Message("User deleted"),
      HttpStatus.OK
    );
  }
}
