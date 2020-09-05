package kata.game.goose;

import kata.game.goose.commands.CommandFactory;
import kata.game.goose.game.GooseGame;
import kata.game.goose.game.StandardGooseGame;
import kata.game.goose.io.ConsoleInputOutputHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
        final GooseGame gooseGame = new StandardGooseGame();

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
        Mockito.when(scanner.nextLine()).thenReturn("add player pippo", "move pippo 4, 2", "exit");

        application.startGame();

        Mockito.verify(printStream).println("Players: pippo");
        Mockito.verify(printStream).println("pippo rolls 4, 2. pippo moves from Start to 6");
        Mockito.verifyNoMoreInteractions(printStream);
    }
}