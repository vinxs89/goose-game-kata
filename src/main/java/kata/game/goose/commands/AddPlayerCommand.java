package kata.game.goose.commands;

import kata.game.goose.game.GooseGame;
import kata.game.goose.results.Result;

import java.util.List;

public class AddPlayerCommand implements Command {

    private String name;

    public AddPlayerCommand(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }

    @Override
    public List<Result> execute(GooseGame game) {
        return game.addPlayer(name);
    }
}
