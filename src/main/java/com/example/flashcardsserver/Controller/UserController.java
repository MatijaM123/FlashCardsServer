package com.example.flashcardsserver.Controller;

import com.example.flashcardsserver.DTO.AuthRequest;
import com.example.flashcardsserver.DTO.AuthResponse;
import com.example.flashcardsserver.DTO.RefreshRequest;
import com.example.flashcardsserver.DTO.RegisterRequest;
import com.example.flashcardsserver.Model.User;
import com.example.flashcardsserver.Service.UserService;
import com.example.flashcardsserver.Util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserController {
  @Autowired
  private UserService userService;

  @Autowired
  private JwtUtil jwtUtil;

  @PostMapping("/register")
  public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
    try {
      userService.register(request);
      return ResponseEntity.ok("User registered successfully.");
    }catch (Exception e){
      return ResponseEntity.badRequest().body(e.getMessage());}
  }

  @PostMapping("/login")
  public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
    try {
      User user = userService.authenticate(request.username(), request.password());
      String accessToken = jwtUtil.generateAccessToken(user.getUsername());
      String refreshToken = jwtUtil.generateRefreshToken(user.getUsername());
      return ResponseEntity.ok(new AuthResponse(accessToken, refreshToken));
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(new AuthResponse(e.getMessage(), ""));
    }
  }

  @PostMapping("/refresh")
  public ResponseEntity<AuthResponse> refresh(@RequestBody RefreshRequest refreshRequest) {
    try {
      String username = jwtUtil.extractUsername(refreshRequest.refreshToken());
      if (jwtUtil.isTokenValid(refreshRequest.refreshToken(), username)) {
        String accessToken = jwtUtil.generateAccessToken(username);
        return ResponseEntity.ok(new AuthResponse(accessToken, refreshRequest.refreshToken()));
      } else {
        return ResponseEntity.badRequest().body(new AuthResponse("Invalid refresh token", ""));
      }
    }catch (Exception e){
      return ResponseEntity.badRequest().body(new AuthResponse("Invalid refresh token", ""));
    }
  }
}
