package kata.game.goose;

import kata.game.goose.commands.CommandFactory;
import kata.game.goose.game.GameConfiguration;
import kata.game.goose.game.GooseGame;
import kata.game.goose.game.StandardGooseGame;
import kata.game.goose.io.ConsoleInputOutputHandler;
import kata.game.goose.utils.RandomUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.io.PrintStream;
import java.util.Scanner;

class ApplicationIntegrationTest {

    private Scanner scanner;
    private PrintStream printStream;
    private Application application;

    @BeforeEach
    void setUp() {
        scanner = Mockito.mock(Scanner.class);
        printStream = Mockito.mock(PrintStream.class);

        final CommandFactory commandFactory = new CommandFactory();
        final ConsoleInputOutputHandler inputOutputHandler = new ConsoleInputOutputHandler(scanner, printStream, commandFactory);
        final GooseGame gooseGame = new StandardGooseGame(new GameConfiguration());

        application = new Application(inputOutputHandler, gooseGame);
    }

    @Test
    void shouldAddNewPlayersAndPrintPlayersThenExit() {
        Mockito.when(scanner.nextLine()).thenReturn("add player pippo", "add player pluto", "exit");

        application.startGame();

        Mockito.verify(printStream).println("Players: pippo");
        Mockito.verify(printStream).println("Players: pippo, pluto");
        Mockito.verifyNoMoreInteractions(printStream);
    }

    @Test
    void shouldPrintDuplicatePlayerThenExit() {
        Mockito.when(scanner.nextLine()).thenReturn("add player pippo", "add player pippo", "exit");

        application.startGame();

        Mockito.verify(printStream).println("Players: pippo");
        Mockito.verify(printStream).println("pippo: already existing player");
        Mockito.verifyNoMoreInteractions(printStream);
    }

    @Test
    void shouldAddPlayerAndMoveIt() {
        Mockito.when(scanner.nextLine()).thenReturn("add player pippo", "move pippo 3, 1", "exit");

        application.startGame();

        Mockito.verify(printStream).println("Players: pippo");
        Mockito.verify(printStream).println("pippo rolls 3, 1. pippo moves from Start to 4");
        Mockito.verifyNoMoreInteractions(printStream);
    }


    @Test
    void shouldAddPlayerBounceAndWin() {
        Mockito.when(scanner.nextLine()).thenReturn("add player pippo")
                .thenReturn("move pippo 6, 6")
                .thenReturn("move pippo 6, 6")
                .thenReturn("move pippo 6, 6")
                .thenReturn("move pippo 6, 6")
                .thenReturn("move pippo 6, 6")
                .thenReturn("move pippo 2, 4")
                .thenReturn("move pippo 1, 2")
                .thenReturn("exit");

        application.startGame();

        Mockito.verify(printStream).println("Players: pippo");
        Mockito.verify(printStream).println("pippo rolls 6, 6. pippo moves from Start to 12");
        Mockito.verify(printStream).println("pippo rolls 6, 6. pippo moves from 12 to 24");
        Mockito.verify(printStream).println("pippo rolls 6, 6. pippo moves from 24 to 36");
        Mockito.verify(printStream).println("pippo rolls 6, 6. pippo moves from 36 to 48");
        Mockito.verify(printStream).println("pippo rolls 6, 6. pippo moves from 48 to 60");
        Mockito.verify(printStream).println("pippo rolls 2, 4. pippo moves from 60 to 63. pippo bounces! pippo returns to 60");
        Mockito.verify(printStream).println("pippo rolls 1, 2. pippo moves from 60 to 63. pippo Wins!!");

        Mockito.verifyNoMoreInteractions(printStream);
    }

    @Test
    void shouldAddPlayerAndDoSomeFakeRandomMoves() {
        Mockito.when(scanner.nextLine()).thenReturn("add player pippo")
                .thenReturn("move pippo")
                .thenReturn("move pippo")
                .thenReturn("move pippo")
                .thenReturn("exit");

        RandomUtil randomUtil = Mockito.mock(RandomUtil.class);
        Mockito.when(randomUtil.randomDice()).thenReturn("1", "2", "3", "4", "5", "6");
        try (final MockedStatic<RandomUtil> randomUtilMockedStatic = Mockito.mockStatic(RandomUtil.class)) {
            randomUtilMockedStatic.when(RandomUtil::getInstance).thenReturn(randomUtil);
            application.startGame();
        }

        Mockito.verify(printStream).println("Players: pippo");
        Mockito.verify(printStream).println("pippo rolls 1, 2. pippo moves from Start to 3");
        Mockito.verify(printStream).println("pippo rolls 3, 4. pippo moves from 3 to 10");
        Mockito.verify(printStream).println("pippo rolls 5, 6. pippo moves from 10 to 21");
        Mockito.verifyNoMoreInteractions(printStream);
    }

    @Test
    void shouldAddPlayerAndPassFromTheBridge() {
        Mockito.when(scanner.nextLine()).thenReturn("add player pippo", "move pippo 4, 2", "move pippo 3, 2", "exit");

        application.startGame();

        Mockito.verify(printStream).println("Players: pippo");
        Mockito.verify(printStream).println("pippo rolls 4, 2. pippo moves from Start to The Bridge. pippo jumps to 12");
        Mockito.verify(printStream).println("pippo rolls 3, 2. pippo moves from 12 to 17");
        Mockito.verifyNoMoreInteractions(printStream);
    }

    @Test
    void shouldAddPlayerAndPassFromTheGoose() {
        Mockito.when(scanner.nextLine()).thenReturn("add player pippo", "move pippo 4, 1", "move pippo 3, 2", "exit");

        application.startGame();

        Mockito.verify(printStream).println("Players: pippo");
        Mockito.verify(printStream).println("pippo rolls 4, 1. pippo moves from Start to 5, The Goose. pippo moves again and goes to 10");
        Mockito.verify(printStream).println("pippo rolls 3, 2. pippo moves from 10 to 15");
        Mockito.verifyNoMoreInteractions(printStream);
    }
}