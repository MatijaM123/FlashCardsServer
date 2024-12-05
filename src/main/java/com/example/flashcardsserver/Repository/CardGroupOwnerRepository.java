package com.example.flashcardsserver.Repository;


import com.example.flashcardsserver.Model.CardGroupOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardGroupOwnerRepository extends JpaRepository<CardGroupOwner, Long> {}
