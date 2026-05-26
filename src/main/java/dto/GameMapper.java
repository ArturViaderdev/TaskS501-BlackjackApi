package dto;

import Model.Game;
import Model.Player;

public class GameMapper {
    private GameMapper() {
    }

    public static GameResponseDTO toResponseDTO(Game game) {
        return new GameResponseDTO(
                game.getId(),
                game.getPlayerId(),
                game.getPlayerName(),
                game.getPlayerCards(),
                game.getDealerCards(),
                game.getPlayerScore(),
                game.getDealerScore(),
                game.getStatus(),
                game.getResult()
        );
    }

    public static PlayerRankingDTO toPlayerRankingDTO(Player player) {
        return new PlayerRankingDTO(
                player.getId(),
                player.getName(),
                player.getGamesPlayed(),
                player.getGamesWon(),
                player.getScore()
        );
    }
}
