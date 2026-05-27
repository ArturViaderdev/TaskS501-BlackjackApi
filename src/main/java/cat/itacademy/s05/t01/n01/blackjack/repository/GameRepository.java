package cat.itacademy.s05.t01.n01.blackjack.repository;

import cat.itacademy.s05.t01.n01.blackjack.Model.Game;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface GameRepository extends ReactiveMongoRepository<Game,String> {
    Flux<Game> findByPlayerId(Long playerId);
    Flux<Game> findByStatus(String status);
}
