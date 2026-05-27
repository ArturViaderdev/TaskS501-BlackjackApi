package cat.itacademy.s05.t01.n01.blackjack.dto;

import java.util.List;

public class GameResponseDTO {
    private String gameId;
    private Long playerId;
    private String playerName;
    private List<String> playerCards;
    private List<String> dealerCards;
    private Integer playerScore;
    private Integer dealerScore;
    private String status;
    private String result;

    public GameResponseDTO() {
    }

    public GameResponseDTO(String gameId, Long playerId, String playerName,
                           List<String> playerCards, List<String> dealerCards,
                           Integer playerScore, Integer dealerScore,
                           String status, String result) {
        this.gameId = gameId;
        this.playerId = playerId;
        this.playerName = playerName;
        this.playerCards = playerCards;
        this.dealerCards = dealerCards;
        this.playerScore = playerScore;
        this.dealerScore = dealerScore;
        this.status = status;
        this.result = result;
    }

    public String getGameId() { return gameId; }
    public void setGameId(String gameId) { this.gameId = gameId; }

    public Long getPlayerId() { return playerId; }
    public void setPlayerId(Long playerId) { this.playerId = playerId; }

    public String getPlayerName() { return playerName; }
    public void setPlayerName(String playerName) { this.playerName = playerName; }

    public List<String> getPlayerCards() { return playerCards; }
    public void setPlayerCards(List<String> playerCards) { this.playerCards = playerCards; }

    public List<String> getDealerCards() { return dealerCards; }
    public void setDealerCards(List<String> dealerCards) { this.dealerCards = dealerCards; }

    public Integer getPlayerScore() { return playerScore; }
    public void setPlayerScore(Integer playerScore) { this.playerScore = playerScore; }

    public Integer getDealerScore() { return dealerScore; }
    public void setDealerScore(Integer dealerScore) { this.dealerScore = dealerScore; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getResult() { return result; }
    public void setResult(String result) { this.result = result; }
}
