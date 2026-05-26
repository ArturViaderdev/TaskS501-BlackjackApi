package service;

import Model.Game;
import Model.Player;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GameService {
    Mono<Game> createGame(String playerName);
    Mono<Game> getGameById(String gameId);
    Mono<Game> play(String gameId, String action);
    Mono<Void> deleteGame(String gameId);
    Flux<Player> getRanking();
    Mono<Player> changePlayerName(Long playerId, String newName);
}
