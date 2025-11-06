/*package ee.taavi.card_game.controller;

import ee.taavi.card_game.entity.Card;
import ee.taavi.card_game.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CardController {

    @Autowired
    private CardRepository cardRepository;

    @GetMapping("cards")
    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    @DeleteMapping("cards")
    public List<Card> deleteAllCards() {
        cardRepository.deleteAll();
        return cardRepository.findAll();
    }
}*/