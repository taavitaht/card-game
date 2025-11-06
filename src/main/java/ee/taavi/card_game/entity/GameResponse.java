package ee.taavi.card_game.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameResponse {
    private int score;
    private int lives;
    private String message;
    private String baseCard;
}
