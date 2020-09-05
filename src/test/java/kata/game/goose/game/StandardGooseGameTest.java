package kata.game.goose.game;

import kata.game.goose.results.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class StandardGooseGameTest {

    private StandardGooseGame standardGooseGame;

    @BeforeEach
    void setUp() {
        standardGooseGame = new StandardGooseGame(new GameConfiguration());
    }

    @Test
    void shouldAddNewPlayer() {
        final String player = "pluto";

        final Collection<Result> results = standardGooseGame.addPlayer(player);
        assertEquals(1, results.size());
        assertEquals(PlayersResult.class, results.iterator().next().getClass());
    }

    @Test
    void shouldReturnDuplicatePlayerResult() {
        final String player = "pluto";

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
        final String dice2 = "1";

        standardGooseGame.players.put(player, 0);
        final List<Result> results = standardGooseGame.movePlayer(player, dice1, dice2);

        assertEquals(2, results.size());
        assertEquals(RollsResult.class, results.get(0).getClass());
        assertEquals(player, ((RollsResult) results.get(0)).getName());
        assertEquals(dice1, ((RollsResult) results.get(0)).getDice1());
        assertEquals(dice2, ((RollsResult) results.get(0)).getDice2());

        assertEquals(MoveResult.class, results.get(1).getClass());
        assertEquals(player, ((MoveResult) results.get(1)).getName());
        assertEquals("Start", ((MoveResult) results.get(1)).getFrom());
        assertEquals("5", ((MoveResult) results.get(1)).getTo());
    }

    @Test
    void shouldThrowExceptionIfPlayerDoesNotExists() {
        final String player = "pippo";
        final String dice1 = "4";
        final String dice2 = "2";

        try {
            standardGooseGame.movePlayer(player, dice1, dice2);
            fail("Exception expected");
        } catch (IllegalArgumentException ignored) {
        }
    }


    @Test
    void shouldWinPlayer() {
        final String player = "pippo";
        final String dice1 = "1";
        final String dice2 = "2";

        standardGooseGame.players.put(player, 60);
        final List<Result> results = standardGooseGame.movePlayer(player, dice1, dice2);

        assertEquals(3, results.size());
        assertEquals(RollsResult.class, results.get(0).getClass());
        assertEquals(player, ((RollsResult) results.get(0)).getName());
        assertEquals(dice1, ((RollsResult) results.get(0)).getDice1());
        assertEquals(dice2, ((RollsResult) results.get(0)).getDice2());

        assertEquals(MoveResult.class, results.get(1).getClass());
        assertEquals(player, ((MoveResult) results.get(1)).getName());
        assertEquals("60", ((MoveResult) results.get(1)).getFrom());
        assertEquals("63", ((MoveResult) results.get(1)).getTo());

        assertEquals(WinResult.class, results.get(2).getClass());
        assertEquals(player, ((WinResult) results.get(2)).getName());
    }


    @Test
    void shouldBouncePlayer() {
        final String player = "pippo";
        final String dice1 = "4";
        final String dice2 = "2";

        standardGooseGame.players.put(player, 60);
        final List<Result> results = standardGooseGame.movePlayer(player, dice1, dice2);

        assertEquals(3, results.size());
        assertEquals(RollsResult.class, results.get(0).getClass());
        assertEquals(player, ((RollsResult) results.get(0)).getName());
        assertEquals(dice1, ((RollsResult) results.get(0)).getDice1());
        assertEquals(dice2, ((RollsResult) results.get(0)).getDice2());

        assertEquals(MoveResult.class, results.get(1).getClass());
        assertEquals(player, ((MoveResult) results.get(1)).getName());
        assertEquals("60", ((MoveResult) results.get(1)).getFrom());
        assertEquals("63", ((MoveResult) results.get(1)).getTo());

        assertEquals(BounceResult.class, results.get(2).getClass());
        assertEquals(player, ((BounceResult) results.get(2)).getName());
        assertEquals(60, ((BounceResult) results.get(2)).getBouncePosition());
    }

    @Test
    void shouldMovePlayerFromTheBridge() {
        final String player = "pippo";
        final String dice1 = "4";
        final String dice2 = "2";

        standardGooseGame.players.put(player, 0);
        final List<Result> results = standardGooseGame.movePlayer(player, dice1, dice2);

        assertEquals(3, results.size());
        assertEquals(RollsResult.class, results.get(0).getClass());
        assertEquals(player, ((RollsResult) results.get(0)).getName());
        assertEquals(dice1, ((RollsResult) results.get(0)).getDice1());
        assertEquals(dice2, ((RollsResult) results.get(0)).getDice2());

        assertEquals(MoveResult.class, results.get(1).getClass());
        assertEquals(player, ((MoveResult) results.get(1)).getName());
        assertEquals("Start", ((MoveResult) results.get(1)).getFrom());
        assertEquals("The Bridge", ((MoveResult) results.get(1)).getTo());

        assertEquals(BridgeResult.class, results.get(2).getClass());
        assertEquals(player, ((BridgeResult) results.get(2)).getName());
        assertEquals("12", ((BridgeResult) results.get(2)).getTo());
    }
}