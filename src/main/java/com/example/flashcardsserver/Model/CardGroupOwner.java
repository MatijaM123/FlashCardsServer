package com.example.flashcardsserver.Model;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "grupaKarticeVlasnik")
@Entity
public class CardGroupOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cardgroup_id", nullable = false)
    private CardGroup cardGroup;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User owner;


}
