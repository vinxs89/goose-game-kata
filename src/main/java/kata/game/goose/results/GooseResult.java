package kata.game.goose.results;

public class GooseResult implements Result {

    private String name;
    private String to;

    public GooseResult(String name, String to) {
        this.name = name;
        this.to = to;
    }

    @Override
    public String print() {
        return String.format("%s moves again and goes to %s", name, to);
    }


    public String getName() {
        return name;
    }

    public String getTo() {
        return to;
    }
}
