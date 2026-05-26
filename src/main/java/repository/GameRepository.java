package repository;

import Model.Game;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface GameRepository extends ReactiveMongoRepository<Game,String> {
    Flux<Game> findByPlayerId(Long playerId);
    Flux<Game> findByStatus(String status);
}
