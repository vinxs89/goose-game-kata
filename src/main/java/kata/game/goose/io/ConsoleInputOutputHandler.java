package kata.game.goose.io;

import kata.game.goose.results.Result;
import kata.game.goose.commands.Command;
import kata.game.goose.commands.CommandFactory;

import java.io.PrintStream;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ConsoleInputOutputHandler implements InputOutputHandler {

    private PrintStream printStream;
    private Scanner scanner;
    private CommandFactory commandFactory;

    public ConsoleInputOutputHandler(Scanner scanner, PrintStream printStream, CommandFactory commandFactory) {
        this.scanner = scanner;
        this.printStream = printStream;
        this.commandFactory = commandFactory;
    }

    @Override
    public Command receiveInput() {
        return commandFactory.createCommand(scanner.nextLine());
    }

    @Override
    public void handleOutput(Collection<Result> commandResult) {
        if (!commandResult.isEmpty()) {
            List<String> formattedResults = commandResult.stream().map(Result::print).collect(Collectors.toList());
            printStream.println(String.join(". ", formattedResults));
        }
    }
}
