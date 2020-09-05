package kata.game.goose.results;

public class MoveResult implements Result {

    private String name;
    private String from;
    private String to;

    public MoveResult(String name, String from, String to) {
        this.name = name;
        this.from = from;
        this.to = to;
    }

    @Override
    public String print() {
        return String.format("%s moves from %s to %s", name, from, to);
    }


    public String getName() {
        return name;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }
}
