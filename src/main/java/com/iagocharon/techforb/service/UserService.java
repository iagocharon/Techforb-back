package com.iagocharon.techforb.service;

import com.iagocharon.techforb.entity.User;
import com.iagocharon.techforb.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

  @Autowired
  UserRepository userRepository;

  public List<User> list() {
    return userRepository.findAll();
  }

  public Optional<User> getByUsername(String username) {
    return userRepository.findByUsername(username);
  }

  public Optional<User> getById(int id) {
    return userRepository.findById(id);
  }

  public boolean existsByUsername(String username) {
    return userRepository.existsByUsername(username);
  }

  public boolean existsById(int id) {
    return userRepository.existsById(id);
  }

  public void save(User user) {
    userRepository.save(user);
  }

  public void delete(int id) {
    userRepository.deleteById(id);
  }
}
