package exception;

public class GameNotFoundException extends RuntimeException{
    public GameNotFoundException()
    {
        super("Game not found with this id.");
    }
}
