package kata.game.goose.game;

import kata.game.goose.exceptions.AlreadyExistingPlayerException;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class StandardGooseGameTest {

    @Test
    void shouldAddNewPlayer() {
        final String player = "pluto";

        StandardGooseGame standardGooseGame = new StandardGooseGame();
        try {
            final Collection<String> players = standardGooseGame.addPlayer(player);
            assertEquals(1, players.size());
            assertEquals(player, players.iterator().next());
        } catch (AlreadyExistingPlayerException e) {
            fail("Exception not expected");
        }
    }

    @Test
    void shouldThrowAlreadyExistingPlayerException() {
        final String player = "pluto";

        StandardGooseGame standardGooseGame = new StandardGooseGame();
        try {
            standardGooseGame.addPlayer(player);
            standardGooseGame.addPlayer(player);
            fail("Exception expected");
        } catch (AlreadyExistingPlayerException ignored) {
        }
    }
}