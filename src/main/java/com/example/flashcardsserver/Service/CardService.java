package com.example.flashcardsserver.Service;


import com.example.flashcardsserver.Model.Card;
import com.example.flashcardsserver.Model.CardGroup;
import com.example.flashcardsserver.Repository.CardGroupRepository;
import com.example.flashcardsserver.Repository.CardRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class CardService {

  private final CardRepository cardRepository;
  private final CardGroupRepository cardGroupRepository;
  public CardService(CardRepository cr, CardGroupRepository cgr){cardRepository=cr;cardGroupRepository=cgr;}
  public Card updateCard(Long id, Card uCard){
    return cardRepository.findById(id).map(eCard->{

    if(uCard.getPitanje()!=null)
      eCard.setPitanje(uCard.getPitanje());
    if(uCard.getOdgovor()!=null)
      eCard.setOdgovor(uCard.getOdgovor());
    return cardRepository.save(eCard);

    }).orElseThrow(() -> new EntityNotFoundException("Card not found with id " + id));
  }
  public Card addCardToGroup(Long groupId, String pitanje, String odgovor) {
    Optional<CardGroup> group = cardGroupRepository.findById(groupId);
        if(!group.isPresent())
         throw new EntityNotFoundException("CardGroup not found with id " + groupId);

    Card newCard = new Card();
    newCard.setPitanje(pitanje);
    newCard.setOdgovor(odgovor);
    newCard.setCardGroup(group.get());

    return cardRepository.save(newCard);
  }
  public void deleteCard(Long cardId) {
    if (!cardRepository.existsById(cardId)) {
      throw new EntityNotFoundException("Card not found with id " + cardId);
    }
    cardRepository.deleteById(cardId);
  }
  public List<Card> getCardsByGroupId(Long groupId) {
    return cardRepository.findByCardGroupId(groupId);
  }

}
