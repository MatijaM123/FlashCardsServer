package com.example.flashcardsserver.Repository;

import com.example.flashcardsserver.Model.Card;
import com.example.flashcardsserver.Model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    Optional<Card> findById(long id);
    boolean existsById(Long id);
    void deleteById(Long id);
    List<Card> findByCardGroupId(Long cardGroupId);
}
