package cat.itacademy.s05.t01.n01.blackjack.service;

import cat.itacademy.s05.t01.n01.blackjack.Model.Game;
import cat.itacademy.s05.t01.n01.blackjack.Model.Player;
import cat.itacademy.s05.t01.n01.blackjack.exception.GameAlreadyFinishedException;
import cat.itacademy.s05.t01.n01.blackjack.exception.GameNotFoundException;
import cat.itacademy.s05.t01.n01.blackjack.exception.InvalidActionException;
import cat.itacademy.s05.t01.n01.blackjack.exception.PlayerNotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import cat.itacademy.s05.t01.n01.blackjack.repository.GameRepository;
import cat.itacademy.s05.t01.n01.blackjack.repository.PlayerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
@Service
public class GameServiceImpl implements GameService {
    private final PlayerRepository playerRepository;
    private final GameRepository gameRepository;

    public GameServiceImpl(PlayerRepository playerRepository, GameRepository gameRepository) {
        this.playerRepository = playerRepository;
        this.gameRepository = gameRepository;
    }

    @Override
    public Mono<Game> createGame(String playerName) {
        return playerRepository.findByName(playerName)
                .switchIfEmpty(Mono.defer(() -> {
                    Player newPlayer = new Player(null, playerName, 0, 0, 0);
                    return playerRepository.save(newPlayer);
                }))
                .flatMap(player -> {
                    List<String> playerCards = new ArrayList<>();
                    List<String> dealerCards = new ArrayList<>();

                    playerCards.add(drawCard());
                    playerCards.add(drawCard());
                    dealerCards.add(drawCard());
                    dealerCards.add(drawCard());

                    int playerScore = calculateScore(playerCards);
                    int dealerScore = calculateScore(dealerCards);

                    Game game = new Game(
                            null,
                            player.getId(),
                            player.getName(),
                            playerCards,
                            dealerCards,
                            playerScore,
                            dealerScore,
                            "IN_PROGRESS",
                            null
                    );
                    return gameRepository.save(game);
                });
    }

    @Override
    public Mono<Game> getGameById(String gameId) {
        return gameRepository.findById(gameId)
                .switchIfEmpty(Mono.error(new GameNotFoundException()));
    }

    @Override
    public Mono<Game> play(String gameId, String action) {
        return gameRepository.findById(gameId)
                .switchIfEmpty(Mono.error(new GameNotFoundException()))
                .flatMap(game -> {
                    if ("FINISHED".equals(game.getStatus())) {
                        return Mono.error(new GameAlreadyFinishedException());
                    }
                    if ("HIT".equalsIgnoreCase(action)) {
                        game.getPlayerCards().add(drawCard());
                        game.setPlayerScore(calculateScore(game.getPlayerCards()));

                        if (game.getPlayerScore() > 21) {
                            game.setStatus("FINISHED");
                            game.setResult("LOSE");
                            return updatePlayerStats(game).then(gameRepository.save(game));
                        }

                        return gameRepository.save(game);
                    }

                    if ("STAND".equalsIgnoreCase(action)) {
                        while (game.getDealerScore() < 17) {
                            game.getDealerCards().add(drawCard());
                            game.setDealerScore(calculateScore(game.getDealerCards()));
                        }

                        game.setStatus("FINISHED");
                        game.setResult(resolveResult(game.getPlayerScore(), game.getDealerScore()));

                        return updatePlayerStats(game).then(gameRepository.save(game));
                    }

                    return Mono.error(new InvalidActionException());
                });
    }

    @Override
    public Mono<Void> deleteGame(String gameId) {
        return gameRepository.findById(gameId)
                .switchIfEmpty(Mono.error(new GameNotFoundException()))
                .flatMap(gameRepository::delete);
    }

    @Override
    public Flux<Player> getRanking() {
        return playerRepository.findAllByOrderByScoreDesc();
    }

    @Override
    public Mono<Player> changePlayerName(Long playerId, String newName) {
        return playerRepository.findById(playerId)
                .switchIfEmpty(Mono.error(new PlayerNotFoundException()))
                .flatMap(player -> {
                    player.setName(newName);
                    return playerRepository.save(player);
                });
    }

    private Mono<Player> updatePlayerStats(Game game) {
        return playerRepository.findById(game.getPlayerId())
                .flatMap(player -> {
                    int played = player.getGamesPlayed() == null ? 0 : player.getGamesPlayed();
                    int won = player.getGamesWon() == null ? 0 : player.getGamesWon();
                    int score = player.getScore() == null ? 0 : player.getScore();

                    player.setGamesPlayed(played + 1);

                    if ("WIN".equals(game.getResult())) {
                        player.setGamesWon(won + 1);
                        player.setScore(score + 10);
                    } else if ("DRAW".equals(game.getResult())) {
                        player.setScore(score + 2);
                    } else {
                        player.setScore(score);
                    }
                    return playerRepository.save(player);
                });
    }

    private String drawCard() {
        String[] cards = {
                "2", "3", "4", "5", "6", "7", "8", "9", "10",
                "J", "Q", "K", "A"
        };
        return cards[new Random().nextInt(cards.length)];
    }

    private int calculateScore(List<String> cards) {
        int score = 0;
        int aces = 0;

        for (String card : cards) {
            switch (card) {
                case "J":
                case "Q":
                case "K":
                    score += 10;
                    break;
                case "A":
                    score += 11;
                    aces++;
                    break;
                default:
                    score += Integer.parseInt(card);
            }
        }

        while (score > 21 && aces > 0) {
            score -= 10;
            aces--;
        }

        return score;
    }

    private String resolveResult(int playerScore, int dealerScore) {
        if (dealerScore > 21) {
            return "WIN";
        }
        if (playerScore > dealerScore) {
            return "WIN";
        }
        if (playerScore < dealerScore) {
            return "LOSE";
        }
        return "DRAW";
    }
}

