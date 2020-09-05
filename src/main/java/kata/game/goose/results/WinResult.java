package kata.game.goose.results;

public class WinResult implements Result {

    private String name;

    public WinResult(String name) {
        this.name = name;
    }

    @Override
    public String print() {
        return String.format("%s Wins!!", name);
    }

    public String getName() {
        return name;
    }
}
