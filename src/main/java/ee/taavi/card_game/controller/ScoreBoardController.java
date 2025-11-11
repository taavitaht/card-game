package ee.taavi.card_game.controller;

import ee.taavi.card_game.entity.ScoreBoard;
import ee.taavi.card_game.repository.ScoreBoardRepository;
import ee.taavi.card_game.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class ScoreBoardController {

    @Autowired
    private ScoreBoardRepository scoreBoardRepository;

    @Autowired
    private GameService gameService;

    @GetMapping("scoreboard")
    public List<ScoreBoard> getAllScores(@RequestParam String name){
        // RequestParam?
        //String name = request.get("name");
        if(name != null && !name.isEmpty()){
            return scoreBoardRepository.findAllByName(name);
        }
        return scoreBoardRepository.findAll();
    }

    @PostMapping("scoreboard")
    public List<ScoreBoard> addScore(@RequestBody Map<String, String> request){
        String name = request.get("name");
        if (name == null || name.isEmpty()){
            System.out.println("No name!");
            return null;
        }

        Duration gameTime = gameService.getGameTime();
        int points = gameService.getScore();

        ScoreBoard newScore = new ScoreBoard();
        newScore.setName(name);
        newScore.setPoints(points);
        newScore.setSeconds(gameTime.getSeconds());
        scoreBoardRepository.save(newScore);
        return scoreBoardRepository.findAll();
    }

    @DeleteMapping("scoreboard")
    public List<ScoreBoard> deleteAllScores(){
        scoreBoardRepository.deleteAll();
        return scoreBoardRepository.findAll();
    }
}