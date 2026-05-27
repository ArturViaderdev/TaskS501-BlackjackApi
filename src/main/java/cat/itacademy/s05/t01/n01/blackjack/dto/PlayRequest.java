package cat.itacademy.s05.t01.n01.blackjack.dto;

public class PlayRequest {
    private String action;

    public PlayRequest() {
    }

    public PlayRequest(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
