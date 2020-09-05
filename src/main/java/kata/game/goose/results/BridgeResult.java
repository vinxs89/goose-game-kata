package kata.game.goose.results;

public class BridgeResult implements Result {

    private String name;
    private String to;

    public BridgeResult(String name, String to) {
        this.name = name;
        this.to = to;
    }

    @Override
    public String print() {
        return String.format("%s jumps to %s", name, to);
    }


    public String getName() {
        return name;
    }

    public String getTo() {
        return to;
    }
}
