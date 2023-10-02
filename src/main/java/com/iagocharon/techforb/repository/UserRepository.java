package com.iagocharon.techforb.repository;

import com.iagocharon.techforb.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
  Optional<User> findByUsername(String username);
  Optional<User> findById(int id);
  boolean existsByUsername(String username);
  boolean existsById(int id);
}
