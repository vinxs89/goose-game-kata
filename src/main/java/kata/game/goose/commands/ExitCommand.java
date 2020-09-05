package kata.game.goose.commands;

import kata.game.goose.results.Result;
import kata.game.goose.game.GooseGame;

import java.util.Collections;
import java.util.List;

public class ExitCommand implements Command {

    @Override
    public List<Result> execute(GooseGame game) {
        // DO NOTHING, JUST USED TO MARK EXIT
        return Collections.emptyList();
    }
}
