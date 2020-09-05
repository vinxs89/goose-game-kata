package kata.game.goose.game;

import kata.game.goose.results.Result;

import java.util.List;

public interface GooseGame {

    List<Result> addPlayer(String name);

    List<Result> movePlayer(String name, String dice1, String dice2);

}
