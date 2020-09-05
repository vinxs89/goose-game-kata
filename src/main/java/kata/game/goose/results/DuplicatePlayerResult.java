package kata.game.goose.results;

public class DuplicatePlayerResult implements Result {

    private String name;

    public DuplicatePlayerResult(String name) {
        this.name = name;
    }

    @Override
    public String print() {
        return String.format("%s: already existing player", name);
    }
}
