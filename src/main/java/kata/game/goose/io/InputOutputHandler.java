package kata.game.goose.io;

import kata.game.goose.results.Result;
import kata.game.goose.commands.Command;

import java.util.Collection;

public interface InputOutputHandler {

    Command receiveInput();

    void handleOutput(Collection<Result> commandResult);

}
