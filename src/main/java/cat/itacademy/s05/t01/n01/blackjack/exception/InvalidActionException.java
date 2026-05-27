package cat.itacademy.s05.t01.n01.blackjack.exception;

public class InvalidActionException extends RuntimeException{
    public InvalidActionException()
    {
        super("Invalid action. Use HIT or STAND");
    }
}
