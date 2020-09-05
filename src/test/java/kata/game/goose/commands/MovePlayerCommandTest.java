package kata.game.goose.commands;

import kata.game.goose.game.GooseGame;
import kata.game.goose.results.MoveResult;
import kata.game.goose.results.Result;
import kata.game.goose.utils.RandomUtil;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class MovePlayerCommandTest {

    private GooseGame gooseGame = Mockito.mock(GooseGame.class);

    @Test
    void shouldMovePlayer() {
        String name = "name";
        String dice1 = "4";
        String dice2 = "2";

        List<Result> moves = Collections.singletonList(new MoveResult(name, dice1, dice2));
        Mockito.when(gooseGame.movePlayer(name, dice1, dice2)).thenReturn(moves);

        MovePlayerCommand movePlayerCommand = new MovePlayerCommand(name, dice1, dice2);
        final List<Result> results = movePlayerCommand.execute(gooseGame);

        assertSame(moves, results);
        Mockito.verify(gooseGame).movePlayer(name, dice1, dice2);
    }

    @Test
    void shouldCreateRandomMove() {
        String name = "name";
        String dice1 = "1";
        String dice2 = "5";

        RandomUtil randomUtil = Mockito.mock(RandomUtil.class);
        Mockito.when(randomUtil.randomDice()).thenReturn(dice1, dice2);
        try (final MockedStatic<RandomUtil> randomUtilMockedStatic = Mockito.mockStatic(RandomUtil.class)) {
            randomUtilMockedStatic.when(RandomUtil::getInstance).thenReturn(randomUtil);

            MovePlayerCommand movePlayerCommand = new MovePlayerCommand(name);
            assertEquals(dice1, movePlayerCommand.getDice1());
            assertEquals(dice2, movePlayerCommand.getDice2());
        }

    }
}