package dto;

public class ChangePlayerNameRequest {
    private String newName;

    public ChangePlayerNameRequest() {
    }

    public ChangePlayerNameRequest(String newName) {
        this.newName = newName;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }
}
