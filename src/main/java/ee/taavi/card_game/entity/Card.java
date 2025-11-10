package ee.taavi.card_game.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Card {
    private String suit;
    private String rank;
    private int power;

    public String cardName() {
        return rank + " of " + suit + " (power: " + power + ")";
    }

}

