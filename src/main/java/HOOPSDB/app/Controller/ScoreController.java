package HOOPSDB.app.Controller;

import HOOPSDB.app.models.Score;
import HOOPSDB.app.repositories.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ScoreController {
  private final ScoreRepository scoreRepository;

  @Autowired
  public ScoreController(ScoreRepository scoreRepository) {
    this.scoreRepository = scoreRepository;
  }

  @GetMapping("/scores")
  public String getAllScoresByGameDate(Long gameDate) {
    List<Score> scores = scoreRepository.findByGameDate(gameDate);
    return "scores";
  }
}