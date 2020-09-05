package kata.game.goose.results;

public class BounceResult implements Result {

    private String name;
    private int bouncePosition;

    public BounceResult(String name, int bouncePosition) {
        this.name = name;
        this.bouncePosition = bouncePosition;
    }

    @Override
    public String print() {
        return String.format("%s bounces! %s returns to %s", name, name, bouncePosition);
    }

    public String getName() {
        return name;
    }

    public int getBouncePosition() {
        return bouncePosition;
    }

}
