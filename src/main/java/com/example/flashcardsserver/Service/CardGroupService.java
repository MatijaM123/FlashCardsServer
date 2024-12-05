package com.example.flashcardsserver.Service;

import com.example.flashcardsserver.Model.CardGroup;
import com.example.flashcardsserver.Repository.CardGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardGroupService {

    private final CardGroupRepository cardGroupRepository;


    public CardGroupService(CardGroupRepository cardGroupRepository) {
        this.cardGroupRepository = cardGroupRepository;
    }

    public CardGroup addCardGroup(CardGroup cardGroup) {
        return cardGroupRepository.save(cardGroup);
    }
    public List<CardGroup> getAllCardGroups() {
        return cardGroupRepository.findAll();
    }
}