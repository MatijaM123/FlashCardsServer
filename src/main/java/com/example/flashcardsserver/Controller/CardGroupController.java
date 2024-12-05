package com.example.flashcardsserver.Controller;

import com.example.flashcardsserver.Model.CardGroup;
import com.example.flashcardsserver.Service.CardGroupService;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController


public class CardGroupController {
    private final CardGroupService cardGroupService;

    @Autowired
    public CardGroupController(CardGroupService cardGroupService) {
        this.cardGroupService = cardGroupService;
    }

    @PostMapping("/cardgroup")
    public ResponseEntity<CardGroup> createCardGroup(@RequestBody CardGroup cardGroup) {
        CardGroup savedCardGroup = cardGroupService.addCardGroup(cardGroup);
        return new ResponseEntity<>(savedCardGroup, HttpStatus.CREATED);
    }

    @GetMapping("/cardgroup")
    public ResponseEntity<List<CardGroup>> getall() {
        List<CardGroup> cardGroups = cardGroupService.getAllCardGroups();
        return new ResponseEntity<>(cardGroups, HttpStatus.OK);
    }
}