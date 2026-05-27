package cat.itacademy.s05.t01.n01.blackjack.exception;

public class GameNotFoundException extends RuntimeException{
    public GameNotFoundException()
    {
        super("Game not found with this id.");
    }
}
