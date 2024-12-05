package com.example.flashcardsserver.Repository;

import com.example.flashcardsserver.Model.Card;
import com.example.flashcardsserver.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    Optional<Card> findB(String username);
    boolean existsByUsername(String username);
}
