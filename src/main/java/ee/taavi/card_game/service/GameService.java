package ee.taavi.card_game.service;

import ee.taavi.card_game.entity.Card;
import ee.taavi.card_game.entity.GameResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Service
public class GameService {

    // Game state
    private int lives = 3;
    private int score = 0;
    private int cardNumber = 0;
    private List<Card> deck = new ArrayList<>();
    private Card baseCard;
    private Date startTime;
    private Date endTime;
    private Duration gameTime;

    // Initialize game
    public Card resetGame(){

        lives = 3;
        score = 0;
        cardNumber = 0;
        startTime = new Date();

        prepareDeck();

        return dealCard();
    }

    // Create a new deck of shuffled cards
    public void prepareDeck() {

        // Generate all cards in deck
        deck.clear();
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
                    case "Ace":    power = 10; break;
                    default:       power = 0;  break;
                }

                card.setSuit(suit);
                card.setRank(rank);
                card.setPower(power);
                deck.add(card);
            }
        }

        // Shuffle the deck
        Collections.shuffle(deck);

        baseCard = deck.get(0);
    }

    public Card dealCard() {
        cardNumber++;
        System.out.println("CardNumber : "+ cardNumber);
        System.out.println("CardName : "+ deck.get(cardNumber).cardName());
        baseCard = deck.get(cardNumber);
        return baseCard;
    }

    public GameResponse guess(String guess){
        if (lives <= 0){
            return new GameResponse(
                    score,
                    lives,
                    "Game is over!",
                    ""
            );
        }

        int previousPower = baseCard.getPower();
        // Draw next card
        int newPower = dealCard().getPower();

        // Compare
        // Guess was correct
        String message = "You guessed right!";
        if("equal".equals(guess) && newPower == previousPower){
            score++;
        }
        else if("higher".equals(guess) && newPower > previousPower){
            score++;
        }
        else if("lower".equals(guess) && newPower < previousPower){
            score++;
        }

        // Guess was wrong
        else {
            lives--;
            if (lives > 0) {
                message = "You guessed wrong!";
            }
            else {
                message = "Game over! Enter your name to save this score";
                endTime = new Date();
                Instant startInstant = startTime.toInstant();
                Instant endInstant = endTime.toInstant();
                gameTime = Duration.between(startInstant, endInstant);
            }
        }


        // Genereate response
        return new GameResponse(
                score,
                lives,
                message,
                baseCard.cardName()
        );
    }
}

