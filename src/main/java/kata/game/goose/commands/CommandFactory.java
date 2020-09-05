package kata.game.goose.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandFactory {

    private static final Pattern EXIT = Pattern.compile("exit");
    private static final Pattern ADD_PLAYER = Pattern.compile("add player (?<name>[0-z]+)");
    private static final Pattern MOVE_PLAYER = Pattern.compile("move (?<name>[0-z]+) (?<dice1>[1-6]+), (?<dice2>[1-6]+)");
    private static final Pattern AUTO_MOVE_PLAYER = Pattern.compile("move (?<name>[0-z]+)");

    public Command createCommand(String input) {
        final Matcher exitMatch = EXIT.matcher(input);
        if (exitMatch.matches()) {
            return new ExitCommand();
        }

        final Matcher addPlayerMatch = ADD_PLAYER.matcher(input);
        if (addPlayerMatch.matches()) {
            return new AddPlayerCommand(addPlayerMatch.group("name"));
        }

        final Matcher movePlayerMatch = MOVE_PLAYER.matcher(input);
        if (movePlayerMatch.matches()) {
            return new MovePlayerCommand(movePlayerMatch.group("name"), movePlayerMatch.group("dice1"), movePlayerMatch.group("dice2"));
        }

        final Matcher autoMovePlayerMatch = AUTO_MOVE_PLAYER.matcher(input);
        if (autoMovePlayerMatch.matches()) {
            return new MovePlayerCommand(autoMovePlayerMatch.group("name"));
        }

        throw new IllegalArgumentException("Command not recognized");
    }
}
