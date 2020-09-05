package kata.game.goose;

import kata.game.goose.commands.Command;
import kata.game.goose.commands.CommandFactory;
import kata.game.goose.commands.ExitCommand;
import kata.game.goose.game.GooseGame;
import kata.game.goose.game.StandardGooseGame;
import kata.game.goose.io.ConsoleInputOutputHandler;
import kata.game.goose.io.InputOutputHandler;
import kata.game.goose.results.Result;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class Application {

    private InputOutputHandler inputOutputHandler;
    private GooseGame gooseGame;

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final PrintStream printStream = System.out;
        final CommandFactory commandFactory = new CommandFactory();
        final ConsoleInputOutputHandler inputOutputHandler = new ConsoleInputOutputHandler(scanner, printStream, commandFactory);

        Application application = new Application(inputOutputHandler, new StandardGooseGame());
        application.startGame();
    }

    Application(InputOutputHandler inputOutputHandler, GooseGame gooseGame) {
        this.inputOutputHandler = inputOutputHandler;
        this.gooseGame = gooseGame;
    }

    void startGame() {
        Command command;
        do {
            command = inputOutputHandler.receiveInput();
            List<Result> results = command.execute(gooseGame);
            inputOutputHandler.handleOutput(results);
        } while (!(command instanceof ExitCommand));
    }
}
