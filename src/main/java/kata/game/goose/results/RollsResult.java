package kata.game.goose.results;

public class RollsResult implements Result {

    private String name;
    private String dice1;
    private String dice2;

    public RollsResult(String name, String dice1, String dice2) {
        this.name = name;
        this.dice1 = dice1;
        this.dice2 = dice2;
    }

    @Override
    public String print() {
        return String.format("%s rolls %s, %s", name, dice1, dice2);
    }

    public String getName() {
        return name;
    }

    public String getDice1() {
        return dice1;
    }

    public String getDice2() {
        return dice2;
    }
}
