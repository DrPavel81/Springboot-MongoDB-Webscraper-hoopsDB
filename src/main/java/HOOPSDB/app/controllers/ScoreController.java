package HOOPSDB.app.controllers;

import HOOPSDB.app.models.Score;
import HOOPSDB.app.repositories.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class ScoreController {

  private final ScoreRepository scoreRepository;

  @Autowired
  public ScoreController(ScoreRepository scoreRepository) {
    this.scoreRepository = scoreRepository;
  }

  @GetMapping("/scores")
  public List<Score> getAllScoresByGameDate(@RequestParam("date") Long gameDate) {
    return scoreRepository.findByGameDate(gameDate);
  }
}