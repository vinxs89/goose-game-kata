package kata.game.goose;

import kata.game.goose.commands.CommandFactory;
import kata.game.goose.game.GameConfiguration;
import kata.game.goose.game.GooseGame;
import kata.game.goose.game.StandardGooseGame;
import kata.game.goose.io.ConsoleInputOutputHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

class ApplicationScenarioTest {

    private Scanner scanner;
    private Application application;

    @BeforeEach
    void setUp() {
        scanner = Mockito.mock(Scanner.class);

        final CommandFactory commandFactory = new CommandFactory();
        final ConsoleInputOutputHandler inputOutputHandler = new ConsoleInputOutputHandler(scanner, System.out, commandFactory);
        final GooseGame gooseGame = new StandardGooseGame(new GameConfiguration());

        application = new Application(inputOutputHandler, gooseGame);
    }


    @Test
    void shouldSimulateRealGame() {
        List<String> players = Arrays.asList("Pippo", "Pluto", "Paperino");
        AtomicInteger currentPlayer = new AtomicInteger(0);
        AtomicInteger iteration = new AtomicInteger(0);

        Mockito.when(scanner.nextLine())
                .thenReturn("add player Pippo")
                .thenReturn("add player Pluto")
                .thenReturn("add player Paperino")
                .thenAnswer(mock -> {
                    if(iteration.addAndGet(1) > 300) {
                        // Too time consuming otherwise
                        return "exit";
                    }
                    String command = "move " + players.get(currentPlayer.get());
                    currentPlayer.set((currentPlayer.get() + 1) % players.size());
                    return command;
                });

        application.startGame();
    }
}