package cat.itacademy.s05.t01.n01.blackjack.repository;

import cat.itacademy.s05.t01.n01.blackjack.Model.Player;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PlayerRepository extends ReactiveCrudRepository<Player,Long> {
    Mono<Player> findByName(String name);
    Flux<Player> findAllByOrderByScoreDesc();
}
