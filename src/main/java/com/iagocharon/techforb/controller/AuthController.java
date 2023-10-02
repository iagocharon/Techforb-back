package com.iagocharon.techforb.controller;

import com.iagocharon.techforb.dto.JwtDto;
import com.iagocharon.techforb.dto.Message;
import com.iagocharon.techforb.dto.UserDto;
import com.iagocharon.techforb.entity.User;
import com.iagocharon.techforb.jwt.JwtProvider;
import com.iagocharon.techforb.service.UserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

  @Autowired
  PasswordEncoder passwordEncoder;

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserService userService;

  @Autowired
  JwtProvider jwtProvider;

  @PostMapping("/signup")
  public ResponseEntity<?> signup(
    @Valid @RequestBody UserDto userDto,
    BindingResult bindingResult
  ) {
    if (bindingResult.hasErrors()) {
      return new ResponseEntity<Message>(
        new Message("Fields with errors"),
        HttpStatus.BAD_REQUEST
      );
    }
    if (userService.existsByUsername(userDto.getUsername())) {
      return new ResponseEntity<Message>(
        new Message("Username already exists"),
        HttpStatus.BAD_REQUEST
      );
    }

    User user = new User(
      userDto.getUsername(),
      passwordEncoder.encode(userDto.getPassword()),
      userDto.getName(),
      userDto.getEmail(),
      userDto.getPhone(),
      userDto.getDni()
    );
    userService.save(user);
    return new ResponseEntity<Message>(
      new Message("User created successfully"),
      HttpStatus.CREATED
    );
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(
    @Valid @RequestBody UserDto userDto,
    BindingResult bindingResult
  ) {
    if (bindingResult.hasErrors()) {
      return new ResponseEntity<Message>(
        new Message("Incorrect fields"),
        HttpStatus.BAD_REQUEST
      );
    }
    Authentication authentication = authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(
        userDto.getUsername(),
        userDto.getPassword()
      )
    );
    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtProvider.generateToken(authentication);
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    User user = userService.getByUsername(userDetails.getUsername()).get();

    JwtDto jwtDto = new JwtDto(jwt, user.getUsername(), user.getId());
    return new ResponseEntity<JwtDto>(jwtDto, HttpStatus.OK);
  }
}
