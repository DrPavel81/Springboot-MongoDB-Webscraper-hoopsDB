package HOOPSDB.app;

import HOOPSDB.app.models.Score;
import HOOPSDB.app.repositories.ScoreRepository;
import org.apache.commons.lang3.ArrayUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;

import static java.lang.Integer.parseInt;

public class NbaScraper {
  private ScoreRepository scoreRepository;

  public NbaScraper(ScoreRepository scoreRepository) {
    this.scoreRepository = scoreRepository;
  }

  public static String[] handleTrailBlazers(String[] arr) {
    for (int i = 0; i < arr.length; i++) {
      if (arr[i].equals("Trail")) {
        arr[i] = arr[i] + "+" + arr[i + 1];
        arr = ArrayUtils.remove(arr, i + 1);
        break;
      }
    }
    return arr;
  }

  public void saveNbaScores(int days) throws IOException {
    String homeTeam;
    String awayTeam;
    String gameRecap;
    int homeScore;
    int awayScore;
    LocalDate date = LocalDate.now().minusDays(days);
    long longDate = date.atStartOfDay().toInstant(ZoneOffset.UTC).toEpochMilli();
    Document doc = Jsoup.connect("https://www.nba.com/games?date=" + date).get();
    String[] teams = doc.select("span.MatchupCardTeamName_teamName__9YaBA").text().split(" ");
    teams = handleTrailBlazers(teams);
    String[] results = doc.select("p.MatchupCardScore_p__dfNvc").text().split(" ");
    List<Score> dbScores = scoreRepository.findByGameDate(longDate);
    if (results.length > 1) {
      for (int i = 2; i < teams.length + 2; i = i + 2) {
        homeTeam = teams[i - 1];
        awayTeam = teams[i - 2];
        homeScore = parseInt(results[i - 1]);
        awayScore = parseInt(results[i - 2]);
        if (homeScore > awayScore) {
          gameRecap = Jsoup.connect(
              "https://www.google.com/search?q=site:youtube.com+Game+Recap%3A+" + homeTeam + "+" + homeScore + "%2C+" +
                  awayTeam + "+" + awayScore).get().selectFirst("div.DhN8Cf").selectFirst("a").attr("href");
          System.out.println(
              "https://www.google.com/search?q=site:youtube.com+Game+Recap%3A+" + homeTeam + "+" + homeScore + "%2C+" +
                  awayTeam + "+" + awayScore);
        } else {
          gameRecap = Jsoup.connect(
              "https://www.google.com/search?q=site:youtube.com+Game+Recap%3A+" + awayTeam + "+" + awayScore + "%2C+" +
                  homeTeam + "+" + homeScore).get().selectFirst("div.DhN8Cf").selectFirst("a").attr("href");
          System.out.println(
              "https://www.google.com/search?q=site:youtube.com+Game+Recap%3A+" + awayTeam + "+" + awayScore + "%2C+" +
                  homeTeam + "+" + homeScore);
        }
        if (homeTeam.equals("Trail+Blazers")) {
          homeTeam = "Trail Blazers";
        }
        if (awayTeam.equals("Trail+Blazers")) {
          awayTeam = "Trail Blazers";
        }
        Score scoreToSave = new Score(homeTeam, awayTeam, gameRecap, homeScore, awayScore, longDate);
        if (!dbScores.contains(scoreToSave)) {
          scoreRepository.save(scoreToSave);
        }
      }
    }
  }
}