package ee.taavi.card_game.service;

import ee.taavi.card_game.entity.Card;
//import ee.taavi.card_game.repository.CardRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@Service
public class GameService {

    //@Autowired
    //private CardRepository cardRepository;

    // Game state
    private int lives = 3;
    private int score = 0;
    private int cardNumber = 0;
    private List<Card> deck;

    // Create a new deck of shuffled cards
    public Card prepareDeck() {
        // Clear deck
        //cardRepository.deleteAll();

        // Reset game state
        //lives = 3;
        //score = 0;
        //cardNumber = 0;

        // Generate all cards in deck
        List<Card> newDeck = new ArrayList<>();
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"Two", "Three", "Four", "Five", "Six", "Seven",
                "Eight", "Nine", "Ten", "Jack", "Queen", "King", "Ace"};

        for (String suit : suits) {
            for (String rank : ranks) {
                int power;
                Card card = new Card();
                switch (rank) {
                    case "Two":    power = 2;  break;
                    case "Three":  power = 3;  break;
                    case "Four":   power = 4;  break;
                    case "Five":   power = 5;  break;
                    case "Six":    power = 6;  break;
                    case "Seven":  power = 7;  break;
                    case "Eight":  power = 8;  break;
                    case "Nine":   power = 9;  break;
                    case "Ten":    power = 10; break;
                    case "Jack":   power = 10; break;
                    case "Queen":  power = 10; break;
                    case "King":   power = 10; break;
                    case "Ace":    power = 11; break;
                    default:       power = 0;  break;
                }

                card.setSuit(suit);
                card.setRank(rank);
                card.setPower(power);
                newDeck.add(card);
            }
        }

        // Shuffle the deck
        Collections.shuffle(newDeck);

        deck = newDeck;
        //cardRepository.saveAll(deck);

        return deck.get(cardNumber);
    }

    public Card nextCard() {
        Card nextCard = deck.get(cardNumber);
        cardNumber++;
        return nextCard;
    }
}

