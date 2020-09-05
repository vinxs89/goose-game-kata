package kata.game.goose.results;

public class PrankResult implements Result {

    private String name;
    private String newPosition;
    private String oldPosition;

    public PrankResult(String name, String newPosition, String oldPosition) {
        this.name = name;
        this.newPosition = newPosition;
        this.oldPosition = oldPosition;
    }

    @Override
    public String print() {
        return String.format("On %s there is %s, who returns to %s", newPosition, name, oldPosition);
    }


    public String getName() {
        return name;
    }

    public String getNewPosition() {
        return newPosition;
    }

    public String getOldPosition() {
        return oldPosition;
    }
}
