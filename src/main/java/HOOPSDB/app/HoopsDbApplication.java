package HOOPSDB.app;

import HOOPSDB.app.repositories.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class HoopsDbApplication implements CommandLineRunner {
  private final ScoreRepository scoreRepository;

  @Autowired
  public HoopsDbApplication(ScoreRepository scoreRepository) {
    this.scoreRepository = scoreRepository;
  }

  public static void main(String[] args) {
    SpringApplication.run(HoopsDbApplication.class, args);
  }

  @Override
  public void run(String... args) throws IOException {
    new NbaScraper(scoreRepository).saveNbaScores(1);
  }
}