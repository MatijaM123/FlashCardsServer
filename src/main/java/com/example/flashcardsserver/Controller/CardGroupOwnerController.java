package com.example.flashcardsserver.Controller;

import com.example.flashcardsserver.DTO.CardGroupOwnerRequest;
import com.example.flashcardsserver.Model.CardGroupOwner;
import com.example.flashcardsserver.Service.CardGroupOwnerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

public class CardGroupOwnerController {

    private final CardGroupOwnerService cardGroupOwnerService;

    public CardGroupOwnerController(CardGroupOwnerService cardGroupOwnerService) {
        this.cardGroupOwnerService = cardGroupOwnerService;
    }

    @PostMapping("/cardgroup/owners")
    public ResponseEntity<CardGroupOwner> createCardGroupOwner(@RequestBody CardGroupOwnerRequest request) {
        try {
            CardGroupOwner savedCardGroupOwner = cardGroupOwnerService.createCardGroupOwner(
                    request.getCardGroupId(),
                    request.getOwnerUsername()
            );
            return new ResponseEntity<>(savedCardGroupOwner, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
