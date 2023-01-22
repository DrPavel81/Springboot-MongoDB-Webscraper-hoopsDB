package HOOPSDB.app.repositories;

import HOOPSDB.app.models.Score;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ScoreRepository extends MongoRepository<Score, String> {
  List<Score> findByGameDate(long gameDate);
}
