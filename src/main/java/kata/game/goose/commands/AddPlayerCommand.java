package kata.game.goose.commands;

import kata.game.goose.game.GooseGame;
import kata.game.goose.results.DuplicatePlayerResult;
import kata.game.goose.results.PlayersResult;
import kata.game.goose.results.Result;
import kata.game.goose.exceptions.AlreadyExistingPlayerException;

import java.util.Collection;
import java.util.Collections;
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
        try {
            Collection players = game.addPlayer(name);
            return Collections.singletonList(new PlayersResult(players));
        } catch (AlreadyExistingPlayerException e) {
            return Collections.singletonList(new DuplicatePlayerResult(name));
        }
    }
}
