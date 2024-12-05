package com.example.flashcardsserver.Service;

import com.example.flashcardsserver.DTO.RegisterRequest;
import com.example.flashcardsserver.Model.User;
import com.example.flashcardsserver.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public void register(RegisterRequest request) {
    if (userRepository.existsByUsername(request.username())) {
      throw new IllegalArgumentException("Username already exists");
    }
    User user = new User();
    user.setUsername(request.username());
    user.setPassword(passwordEncoder.encode(request.password()));
    userRepository.save(user);
  }

  public User authenticate(String username, String password) {
    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    if (!passwordEncoder.matches(password, user.getPassword())) {
      throw new BadCredentialsException("Invalid credentials");
    }
    return user;
  }
}

