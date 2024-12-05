package com.example.flashcardsserver.Service;

import com.example.flashcardsserver.Model.CardGroup;
import com.example.flashcardsserver.Model.CardGroupOwner;
import com.example.flashcardsserver.Model.User;
import com.example.flashcardsserver.Repository.CardGroupOwnerRepository;
import com.example.flashcardsserver.Repository.CardGroupRepository;
import com.example.flashcardsserver.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardGroupOwnerService {

    private final CardGroupOwnerRepository cardGroupOwnerRepository;
    private final CardGroupRepository cardGroupRepository;
    private final UserRepository ownerRepository;


    public CardGroupOwnerService(CardGroupOwnerRepository cardGroupOwnerRepository,
                                 CardGroupRepository cardGroupRepository,
                                 UserRepository ownerRepository) {
        this.cardGroupOwnerRepository = cardGroupOwnerRepository;
        this.cardGroupRepository = cardGroupRepository;
        this.ownerRepository = ownerRepository;
    }

    public CardGroupOwner createCardGroupOwner(Long cardGroupId, String username) {
        Optional<CardGroup> cardGroupOptional = cardGroupRepository.findById(cardGroupId);
        Optional<User> ownerOptional = ownerRepository.findByUsername(username);

        if (cardGroupOptional.isPresent() && ownerOptional.isPresent()) {
            CardGroup cardGroup = cardGroupOptional.get();
            User owner = ownerOptional.get();

            CardGroupOwner cardGroupOwner = new CardGroupOwner();
            cardGroupOwner.setCardGroup(cardGroup);
            cardGroupOwner.setOwner(owner);


            return cardGroupOwnerRepository.save(cardGroupOwner);
        } else {
            throw new IllegalArgumentException("CardGroup or Owner not found");
        }
    }
}