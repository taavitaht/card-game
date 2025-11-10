package ee.taavi.card_game.controller;

import ee.taavi.card_game.entity.GameResponse;
import ee.taavi.card_game.service.GameService;
import ee.taavi.card_game.entity.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping("/start")
    public GameResponse startGame() {
        Card baseCard = gameService.resetGame();

        // Build response
        return new GameResponse(
                gameService.getScore(),
                gameService.getLives(),
                "Game Started",
                baseCard.cardName()
        );
    }

    @PostMapping("/guess")
    public GameResponse guessCard(@RequestBody Map<String, String> request) {

        return gameService.guess(request.get("guess"));
    }
}
