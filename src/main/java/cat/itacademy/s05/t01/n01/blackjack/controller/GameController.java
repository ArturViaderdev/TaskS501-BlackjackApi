package cat.itacademy.s05.t01.n01.blackjack.controller;

import cat.itacademy.s05.t01.n01.blackjack.dto.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import cat.itacademy.s05.t01.n01.blackjack.service.GameService;

@RestController
@RequestMapping
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/game/new")
    public Mono<ResponseEntity<GameResponseDTO>> createGame(@RequestBody CreateGameRequest request) {
        return gameService.createGame(request.getPlayerName())
                .map(GameMapper::toResponseDTO)
                .map(dto -> ResponseEntity.status(HttpStatus.CREATED).body(dto));
    }

    @GetMapping("/game/{id}")
    public Mono<ResponseEntity<GameResponseDTO>> getGameById(@PathVariable String id) {
        return gameService.getGameById(id)
                .map(GameMapper::toResponseDTO)
                .map(ResponseEntity::ok);
    }

    @PostMapping("/game/{id}/play")
    public Mono<ResponseEntity<GameResponseDTO>> play(@PathVariable String id,
                                                      @RequestBody PlayRequest request) {
        return gameService.play(id, request.getAction())
                .map(GameMapper::toResponseDTO)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/game/{id}/delete")
    public Mono<ResponseEntity<Void>> deleteGame(@PathVariable String id) {
        return gameService.deleteGame(id)
                .then(Mono.just(ResponseEntity.noContent().build()));
    }

    @GetMapping("/ranking")
    public Flux<PlayerRankingDTO> getRanking() {
        return gameService.getRanking()
                .map(GameMapper::toPlayerRankingDTO);
    }

    @PutMapping("/player/{playerId}")
    public Mono<ResponseEntity<PlayerRankingDTO>> changePlayerName(
            @PathVariable Long playerId,
            @RequestBody ChangePlayerNameRequest request) {

        return gameService.changePlayerName(playerId, request.getNewName())
                .map(GameMapper::toPlayerRankingDTO)
                .map(ResponseEntity::ok);
    }
}
