package kata.game.goose.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandFactory {

    private static final Pattern EXIT = Pattern.compile("exit");
    private static final Pattern ADD_PLAYER = Pattern.compile("add player (?<name>[0-z]+)");

    public Command createCommand(String input) {
        final Matcher exitMatch = EXIT.matcher(input);
        if (exitMatch.matches()) {
            return new ExitCommand();
        }

        final Matcher addPlayerMatch = ADD_PLAYER.matcher(input);
        if (addPlayerMatch.matches()) {
            return new AddPlayerCommand(addPlayerMatch.group("name"));
        }

        throw new IllegalArgumentException("Command not recognized");
    }
}
