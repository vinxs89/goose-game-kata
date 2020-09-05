package kata.game.goose.game;

import kata.game.goose.exceptions.AlreadyExistingPlayerException;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

public class StandardGooseGame implements GooseGame {

    private Set<String> players = new LinkedHashSet<>();

    @Override
    public Collection<String> addPlayer(String name) throws AlreadyExistingPlayerException {
        if (players.contains(name)) {
            throw new AlreadyExistingPlayerException();
        } else {
            players.add(name);
            return players;
        }
    }
}
