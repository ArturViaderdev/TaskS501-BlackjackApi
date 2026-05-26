package dto;

public class PlayerRankingDTO {
    private Long playerId;
    private String playerName;
    private Integer gamesPlayed;
    private Integer gamesWon;
    private Integer score;

    public PlayerRankingDTO() {
    }

    public PlayerRankingDTO(Long playerId, String playerName, Integer gamesPlayed, Integer gamesWon, Integer score) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.gamesPlayed = gamesPlayed;
        this.gamesWon = gamesWon;
        this.score = score;
    }

    public Long getPlayerId() { return playerId; }
    public void setPlayerId(Long playerId) { this.playerId = playerId; }

    public String getPlayerName() { return playerName; }
    public void setPlayerName(String playerName) { this.playerName = playerName; }

    public Integer getGamesPlayed() { return gamesPlayed; }
    public void setGamesPlayed(Integer gamesPlayed) { this.gamesPlayed = gamesPlayed; }

    public Integer getGamesWon() { return gamesWon; }
    public void setGamesWon(Integer gamesWon) { this.gamesWon = gamesWon; }

    public Integer getScore() { return score; }
    public void setScore(Integer score) { this.score = score; }
}
