package com.example.flashcardsserver.Repository;

import com.example.flashcardsserver.Model.CardGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardGroupRepository extends JpaRepository<CardGroup,Long> {

}
