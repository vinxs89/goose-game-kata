package kata.game.goose.game;

import kata.game.goose.results.*;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class StandardGooseGameTest {

    @Test
    void shouldAddNewPlayer() {
        final String player = "pluto";

        StandardGooseGame standardGooseGame = new StandardGooseGame();
        final Collection<Result> results = standardGooseGame.addPlayer(player);
        assertEquals(1, results.size());
        assertEquals(PlayersResult.class, results.iterator().next().getClass());
    }

    @Test
    void shouldReturnDuplicatePlayerResult() {
        final String player = "pluto";

        StandardGooseGame standardGooseGame = new StandardGooseGame();

        final List<Result> results1 = standardGooseGame.addPlayer(player);
        assertEquals(1, results1.size());
        assertEquals(PlayersResult.class, results1.iterator().next().getClass());

        final List<Result> results2 = standardGooseGame.addPlayer(player);
        assertEquals(1, results2.size());
        assertEquals(DuplicatePlayerResult.class, results2.iterator().next().getClass());
    }

    @Test
    void shouldMovePlayer() {
        final String player = "pippo";
        final String dice1 = "4";
        final String dice2 = "2";

        StandardGooseGame game = new StandardGooseGame();
        game.players.put(player, 0);

        final List<Result> results = game.movePlayer(player, dice1, dice2);

        assertEquals(2, results.size());
        assertEquals(RollsResult.class, results.get(0).getClass());
        assertEquals(player, ((RollsResult) results.get(0)).getName());
        assertEquals(dice1, ((RollsResult) results.get(0)).getDice1());
        assertEquals(dice2, ((RollsResult) results.get(0)).getDice2());

        assertEquals(MoveResult.class, results.get(1).getClass());
        assertEquals(player, ((MoveResult) results.get(1)).getName());
        assertEquals("START", ((MoveResult) results.get(1)).getFrom());
        assertEquals("6", ((MoveResult) results.get(1)).getTo());
    }

    @Test
    void shouldThrowExceptionIfPlayerDoesNotExists() {
        final String player = "pippo";
        final String dice1 = "4";
        final String dice2 = "2";

        StandardGooseGame game = new StandardGooseGame();

        try {
            game.movePlayer(player, dice1, dice2);
            fail("Exception expected");
        } catch (IllegalArgumentException ignored) {
        }
    }
}