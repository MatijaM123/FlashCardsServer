package com.example.flashcardsserver.Model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long Id;
    @Column(nullable = false)
    String pitanje;
    @Column(nullable = false)
    String odgovor;
}
