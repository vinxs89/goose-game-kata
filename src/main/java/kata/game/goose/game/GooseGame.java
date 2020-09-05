package kata.game.goose.game;

import kata.game.goose.exceptions.AlreadyExistingPlayerException;

import java.util.Collection;

public interface GooseGame {

    Collection<String> addPlayer(String name) throws AlreadyExistingPlayerException;

}
