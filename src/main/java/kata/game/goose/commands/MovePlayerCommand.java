package kata.game.goose.commands;

import kata.game.goose.game.GooseGame;
import kata.game.goose.results.Result;

import java.util.List;

public class MovePlayerCommand implements Command {

    private String name;
    private String dice1;
    private String dice2;

    MovePlayerCommand(String name, String dice1, String dice2) {
        this.name = name;
        this.dice1 = dice1;
        this.dice2 = dice2;
    }

    @Override
    public List<Result> execute(GooseGame game) {
        return game.movePlayer(name, dice1, dice2);
    }

    String getName() {
        return name;
    }

    String getDice1() {
        return dice1;
    }

    String getDice2() {
        return dice2;
    }
}
