package com.iagocharon.techforb.util;

import com.iagocharon.techforb.entity.Transaction;
import com.iagocharon.techforb.entity.User;
import com.iagocharon.techforb.enums.TransactionType;
import com.iagocharon.techforb.service.TransactionService;
import com.iagocharon.techforb.service.UserService;
import java.time.LocalDateTime;
import java.util.Random;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Transactional
public class Utils implements CommandLineRunner {

  @Autowired
  UserService userService;

  @Autowired
  TransactionService transactionService;

  @Autowired
  PasswordEncoder passwordEncoder;

  @Override
  public void run(String... args) throws Exception {
    // createUser();
    // makeDeposits();
    // makeWithdrawals();
  }

  void createUser() {
    User user = new User(
      "admin",
      passwordEncoder.encode("admin"),
      "Iago Charon",
      "iago@iagocharon.com",
      "1176351443",
      "43330981"
    );
    userService.save(user);
  }

  void makeDeposits() {
    Random random = new Random();
    LocalDateTime currentDate = LocalDateTime.now(); // Obtiene la fecha y hora actual

    // Retrocede 60 días a partir de la fecha actual
    LocalDateTime startDate = currentDate.minusDays(60);

    while (currentDate.isAfter(startDate)) {
      int numberOfDeposits = 3; // Genera de 0 a 4 depósitos por día

      for (int i = 0; i < numberOfDeposits; i++) {
        double amount = 10000 + random.nextDouble() * 8000; // Genera un monto aleatorio entre 10,000 y 30,000
        LocalDateTime randomDate = startDate
          .withHour(random.nextInt(24))
          .withMinute(random.nextInt(60))
          .withSecond(random.nextInt(60));

        deposit(amount, randomDate);
      }

      startDate = startDate.plusDays(1); // Avanza al siguiente día
    }
  }

  void makeWithdrawals() {
    Random random = new Random();
    LocalDateTime currentDate = LocalDateTime.now(); // Obtiene la fecha y hora actual

    // Retrocede 60 días a partir de la fecha actual
    LocalDateTime startDate = currentDate.minusDays(60);

    while (currentDate.isAfter(startDate)) {
      int numberOfDeposits = 3; // Genera de 0 a 4 depósitos por día

      for (int i = 0; i < numberOfDeposits; i++) {
        double amount = 8000 + random.nextDouble() * 6000; // Genera un monto aleatorio entre 10,000 y 30,000
        LocalDateTime randomDate = startDate
          .withHour(random.nextInt(24))
          .withMinute(random.nextInt(60))
          .withSecond(random.nextInt(60));

        withdraw(amount, randomDate);
      }

      startDate = startDate.plusDays(1); // Avanza al siguiente día
    }
  }

  void deposit(double amount, LocalDateTime date) {
    User user = userService.getById(1).get();
    user.setBalance(user.getBalance() + amount);

    Transaction transaction = new Transaction(
      null,
      user,
      TransactionType.DEPOSIT,
      amount
    );

    transaction.setDate(date);

    user.addIncomingTransaction(transaction);

    transactionService.save(transaction);
    userService.save(user);
  }

  void withdraw(double amount, LocalDateTime date) {
    User user = userService.getById(1).get();
    if (user.getBalance() < amount) {
      return;
    }
    user.setBalance(user.getBalance() - amount);

    Transaction transaction = new Transaction(
      user,
      null,
      TransactionType.WITHDRAW,
      amount
    );

    transaction.setDate(date);

    user.addOutgoingTransaction(transaction);

    transactionService.save(transaction);
    userService.save(user);
  }
}
