package kata.game.goose.commands;

import kata.game.goose.game.GooseGame;
import kata.game.goose.results.PlayersResult;
import kata.game.goose.results.Result;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class AddPlayerCommandTest {

    private GooseGame gooseGame = Mockito.mock(GooseGame.class);

    @Test
    void shouldReturnGameResult() {
        String newPlayer = "test";
        PlayersResult playersResult = new PlayersResult(Collections.singletonList(newPlayer));
        Mockito.when(gooseGame.addPlayer(newPlayer)).thenReturn(Collections.singletonList(playersResult));

        AddPlayerCommand addPlayerCommand = new AddPlayerCommand(newPlayer);
        List<Result> results = addPlayerCommand.execute(gooseGame);

        assertEquals(1, results.size());
        assertSame(playersResult, results.get(0));
    }
}