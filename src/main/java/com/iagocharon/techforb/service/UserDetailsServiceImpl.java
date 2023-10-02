package com.iagocharon.techforb.service;

import com.iagocharon.techforb.entity.MainUser;
import com.iagocharon.techforb.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  UserService userService;

  @Override
  public UserDetails loadUserByUsername(String username)
    throws UsernameNotFoundException {
    User user = userService
      .getByUsername(username)
      .orElseThrow(() ->
        new UsernameNotFoundException(
          "User Not Found with username: " + username
        )
      );
    return MainUser.build(user);
  }
}
