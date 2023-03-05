package HOOPSDB.app.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Objects;

@Document
public class Score {
  @Id
  private String id;
  @Field
  private String homeTeam;
  @Field
  private String awayTeam;
  @Field
  private String gameRecap;
  @Field
  private int homeScore;
  @Field
  private int awayScore;
  @Field
  private Long gameDate;

  public Score() {
  }

  public Score(String homeTeam, String awayTeam, String gameRecap, int homeScore, int awayScore, Long gameDate) {
    this.homeTeam = homeTeam;
    this.awayTeam = awayTeam;
    this.gameRecap = gameRecap;
    this.homeScore = homeScore;
    this.awayScore = awayScore;
    this.gameDate = gameDate;
  }

  public String getHomeTeam() {
    return homeTeam;
  }

  public void setHomeTeam(String homeTeam) {
    this.homeTeam = homeTeam;
  }

  public String getGameRecap() {
    return gameRecap;
  }

  public void setGameRecap(String gameRecap) {
    this.gameRecap = gameRecap;
  }

  public String getAwayTeam() {
    return awayTeam;
  }

  public void setAwayTeam(String awayTeam) {
    this.awayTeam = awayTeam;
  }

  public int getHomeScore() {
    return homeScore;
  }

  public void setHomeScore(int homeScore) {
    this.homeScore = homeScore;
  }

  public int getAwayScore() {
    return awayScore;
  }

  public void setAwayScore(int awayScore) {
    this.awayScore = awayScore;
  }

  public Long getGameDate() {
    return gameDate;
  }

  public void setGameDate(Long gameDate) {
    this.gameDate = gameDate;
  }

  @Override
  public String toString() {
    return "Score{" + "id='" + id + '\'' + ", homeTeam='" + homeTeam + '\'' + ", awayTeam='" + awayTeam + '\'' +
        ", homeScore=" + homeScore + ", awayScore=" + awayScore + ", gameDate=" +
        Instant.ofEpochMilli(gameDate).atZone(ZoneId.of("UTC")).toLocalDate() + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Score score = (Score) o;
    return homeScore == score.homeScore && awayScore == score.awayScore && Objects.equals(homeTeam, score.homeTeam) &&
        Objects.equals(awayTeam, score.awayTeam) && Objects.equals(gameDate, score.gameDate);
  }
}
