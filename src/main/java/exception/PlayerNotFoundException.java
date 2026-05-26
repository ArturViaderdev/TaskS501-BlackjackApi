package exception;

public class PlayerNotFoundException extends RuntimeException{
    public PlayerNotFoundException()
    {
        super("Player not found with this id.");
    }
}
