package exception;

public class InvalidActionException extends RuntimeException{
    public InvalidActionException()
    {
        super("Invalid action. Use HIT or STAND");
    }
}
