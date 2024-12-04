package com.example.flashcardsserver.Util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
  private static final String SECRET_KEY = "secureSecretKeyForJwtGenerationabcdefghijklmikasnansfansfiansfonasofosn";
  private static final long ACCESS_TOKEN_VALIDITY = 1 * 60 * 1000; // 1 minuta
  private static final long REFRESH_TOKEN_VALIDITY = 7 * 24 * 60 * 60 * 1000; // 7 dana

  private Key getSigningKey() {
    return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
  }

  public String generateAccessToken(String username) {
    return Jwts.builder()
        .setSubject(username)
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY))
        .signWith(getSigningKey(), SignatureAlgorithm.HS256)
        .compact();
  }

  public String generateRefreshToken(String username) {
    return Jwts.builder()
        .setSubject(username)
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_VALIDITY))
        .signWith(getSigningKey(), SignatureAlgorithm.HS256)
        .compact();
  }

  public String extractUsername(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(getSigningKey())
        .build()
        .parseClaimsJws(token)
        .getBody()
        .getSubject();
  }

  public boolean isTokenValid(String token, String username) {
    String extractedUsername = extractUsername(token);
    return extractedUsername.equals(username) && !isTokenExpired(token);
  }

  private boolean isTokenExpired(String token) {
    Date expiration = Jwts.parserBuilder()
        .setSigningKey(getSigningKey())
        .build()
        .parseClaimsJws(token)
        .getBody()
        .getExpiration();
    return expiration.before(new Date());
  }
}
