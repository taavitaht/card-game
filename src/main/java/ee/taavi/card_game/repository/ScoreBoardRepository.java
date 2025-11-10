package ee.taavi.card_game.repository;

import ee.taavi.card_game.entity.ScoreBoard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScoreBoardRepository extends JpaRepository<ScoreBoard, Long> {
    List<ScoreBoard> findAllByName(String name);
}
