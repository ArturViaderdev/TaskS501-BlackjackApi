package dto;

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
