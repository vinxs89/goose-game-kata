package kata.game.goose.commands;

import kata.game.goose.game.GooseGame;
import kata.game.goose.results.DuplicatePlayerResult;
import kata.game.goose.results.PlayersResult;
import kata.game.goose.results.Result;
import kata.game.goose.exceptions.AlreadyExistingPlayerException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddPlayerCommandTest {

    private GooseGame gooseGame = Mockito.mock(GooseGame.class);

    @Test
    void shouldReturnPlayersResult() throws AlreadyExistingPlayerException {
        String newPlayer = "test";
        Mockito.when(gooseGame.addPlayer(newPlayer)).thenReturn(Collections.singletonList(newPlayer));

        AddPlayerCommand addPlayerCommand = new AddPlayerCommand(newPlayer);
        List<Result> results = addPlayerCommand.execute(gooseGame);

        assertEquals(1, results.size());
        assertEquals(PlayersResult.class, results.get(0).getClass());
    }


    @Test
    void shouldReturnDuplicatePlayersResult() throws AlreadyExistingPlayerException {
        String newPlayer = "test";
        Mockito.when(gooseGame.addPlayer(newPlayer)).thenThrow(AlreadyExistingPlayerException.class);

        AddPlayerCommand addPlayerCommand = new AddPlayerCommand(newPlayer);
        List<Result> results = addPlayerCommand.execute(gooseGame);

        assertEquals(1, results.size());
        assertEquals(DuplicatePlayerResult.class, results.get(0).getClass());
    }
}