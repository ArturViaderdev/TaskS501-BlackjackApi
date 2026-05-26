package Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("players")
public class Player {
    @Id
    private Long id;
    private String name;
    private Integer gamesPlayed;
    private Integer gamesWon;
    private Integer score;

    public Player(){

    }

    public Player(Long id,String name,Integer gamesPlayed,Integer gamesWon, Integer score)
    {
        this.id=id;
        this.name=name;
        this.gamesPlayed=gamesPlayed;
        this.gamesWon=gamesWon;
        this.score=score;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(Integer gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public Integer getGamesWon() {
        return gamesWon;
    }

    public void setGamesWon(Integer gamesWon) {
        this.gamesWon = gamesWon;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
