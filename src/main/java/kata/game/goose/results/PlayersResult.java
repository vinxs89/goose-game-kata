package kata.game.goose.results;

import java.util.Collection;

public class PlayersResult implements Result {

    private Collection<String> players;

    public PlayersResult(Collection<String> players) {
        this.players = players;
    }

    @Override
    public String print() {
        return String.format("Players: %s", String.join(", ", players));
    }
}
