package kata.game.goose.commands;

import kata.game.goose.game.GooseGame;
import kata.game.goose.results.Result;

import java.util.List;

public interface Command {

    List<Result> execute(GooseGame game);

}
