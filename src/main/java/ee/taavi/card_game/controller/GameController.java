package ee.taavi.card_game.controller;

import ee.taavi.card_game.service.GameService;
import ee.taavi.card_game.entity.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping("/start")
    public Card startGame() {
        List<Card> deck = gameService.prepareDeck();
        Card baseCard = deck.get(0);

        return baseCard;
        // Return base card info as a response
        //return new BaseCardResponse(baseCard.getSuit(),
         //       baseCard.getRank(),
          //      baseCard.getPower());
    }

    // DTO for returning base card
    public static class BaseCardResponse {
        private String suit;
        private String rank;
        private int power;

        public BaseCardResponse(String suit, String rank, int power) {
            this.suit = suit;
            this.rank = rank;
            this.power = power;
        }

        public String getSuit() { return suit; }
        public String getRank() { return rank; }
        public int getPower() { return power; }
    }
}
