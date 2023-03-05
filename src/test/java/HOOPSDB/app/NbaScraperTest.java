package HOOPSDB.app;


import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.time.ZoneOffset;
import java.util.Arrays;


import static org.junit.jupiter.api.Assertions.assertEquals;

import HOOPSDB.app.models.Score;
import HOOPSDB.app.repositories.ScoreRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class NbaScraperTest {

  @Test
  void testHandleTrailBlazers() {
    String[] input = {"Clippers", "Trail", "Blazers", "Nets", "Pacers", "Nuggets", "Spurs"};
    String[] expected = {"Clippers", "Trail+Blazers", "Nets", "Pacers", "Nuggets", "Spurs"};

    String[] result = NbaScraper.handleTrailBlazers(input);

    assertEquals(Arrays.toString(expected), Arrays.toString(result));
  }

  @InjectMocks

  private NbaScraper nbaScraper;
  @Mock

  private ScoreRepository mockScoreRepository;

  @Before
  public void setup() {
    mockScoreRepository = mock(ScoreRepository.class);
    nbaScraper = new NbaScraper(mockScoreRepository);
  }

  @Test
  public void testSaveNbaScores() throws IOException {
    String homeTeam = "Home Team";
    String awayTeam = "Away Team";
    String gameRecap = "https://www.youtube.com/watch?v=game-recap";
    int homeScore = 100;
    int awayScore = 90;
    LocalDate date = LocalDate.now().minusDays(1);
    long longDate = date.atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli();
    String[] teams = {"Team1", "Team2", homeTeam, awayTeam};
    String[] results = {"90", "100", "80", "90"};
    List<Score> dbScores = new ArrayList<>();

    when(mockScoreRepository.findByGameDate(anyLong())).thenReturn(dbScores);
    doNothing().when(mockScoreRepository).save(any(Score.class));

    nbaScraper.saveNbaScores(1);

    verify(mockScoreRepository, times(1)).findByGameDate(longDate);
    verify(mockScoreRepository, times(1)).save(any(Score.class));
    Score expectedScore = new Score(homeTeam, awayTeam, gameRecap, homeScore, awayScore, longDate);
    verify(mockScoreRepository, times(1)).save(expectedScore);
  }
}