package com.example.flashcardsserver.Controller;


import com.example.flashcardsserver.DTO.CardDto;
import com.example.flashcardsserver.DTO.RegisterRequest;
import com.example.flashcardsserver.Model.Card;
import com.example.flashcardsserver.Service.CardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cards")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }


    @PostMapping("/group/{groupId}")
    public ResponseEntity<Card> addCard(@PathVariable Long groupId,
                                        @RequestBody CardDto cardDto) {
        Card createdCard = cardService.addCardToGroup(groupId, cardDto.pitanje(), cardDto.odgovor());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCard);
    }
    @DeleteMapping("/{cardId}")
    public ResponseEntity<Void> deleteCard(@PathVariable Long cardId) {
        cardService.deleteCard(cardId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/group/{groupId}")
    public ResponseEntity<List<Card>> getCardsByGroup(@PathVariable Long groupId) {
        List<Card> cards = cardService.getCardsByGroupId(groupId);
        return ResponseEntity.ok(cards);
    }

    @PutMapping("/")
    public ResponseEntity<Card> updateCard(@RequestBody Card card){
       Card nc =  cardService.updateCard(card.getId(),card);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(nc);
    }
}
