package kata.game.goose.io;

import kata.game.goose.results.Result;
import kata.game.goose.commands.Command;
import kata.game.goose.commands.CommandFactory;
import kata.game.goose.commands.ExitCommand;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertSame;

class ConsoleInputOutputHandlerTest {

    private Scanner scanner = Mockito.mock(Scanner.class);
    private PrintStream printStream = Mockito.mock(PrintStream.class);
    private CommandFactory commandFactory = Mockito.mock(CommandFactory.class);

    private ConsoleInputOutputHandler consoleInputOutputHandler = new ConsoleInputOutputHandler(scanner, printStream, commandFactory);

    @Test
    void shouldCallCommandFactoryWithNextLineInput() {
        String input = "input";
        Command expectedCommand = new ExitCommand();

        Mockito.when(scanner.nextLine()).thenReturn(input);
        Mockito.when(commandFactory.createCommand(input)).thenReturn(expectedCommand);

        Command command = consoleInputOutputHandler.receiveInput();

        Mockito.verify(commandFactory).createCommand(input);
        assertSame(expectedCommand, command);
    }

    @Test
    void handleOutput() {
        final Result result1 = Mockito.mock(Result.class);
        final Result result2 = Mockito.mock(Result.class);

        Mockito.when(result1.print()).thenReturn("print1");
        Mockito.when(result2.print()).thenReturn("print2");

        List<Result> results = Arrays.asList(result1, result2);

        consoleInputOutputHandler.handleOutput(results);

        Mockito.verify(result1, Mockito.times(1)).print();
        Mockito.verify(result2, Mockito.times(1)).print();
        Mockito.verify(printStream).println("print1. print2");
        Mockito.verifyNoMoreInteractions(printStream);
    }
}