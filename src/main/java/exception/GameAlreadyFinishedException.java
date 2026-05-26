package exception;

public class GameAlreadyFinishedException extends RuntimeException {
    public GameAlreadyFinishedException()
    {
        super("Game already finished");
    }
}
