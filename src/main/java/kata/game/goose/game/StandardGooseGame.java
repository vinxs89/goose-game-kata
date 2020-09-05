package kata.game.goose.game;

import kata.game.goose.results.*;

import java.util.*;

public class StandardGooseGame implements GooseGame {

    Map<String, Integer> players = new LinkedHashMap<>();
    Map<Integer, String> specialPositions = new LinkedHashMap<>();

    public StandardGooseGame() {
        specialPositions.put(0, "Start");
    }

    @Override
    public List<Result> addPlayer(String name) {
        if (players.containsKey(name)) {
            return Collections.singletonList(new DuplicatePlayerResult(name));
        } else {
            players.put(name, 0);
            return Collections.singletonList(new PlayersResult(players.keySet()));
        }
    }

    @Override
    public List<Result> movePlayer(String name, String dice1String, String dice2String) {
        if (!players.containsKey(name)) {
            throw new IllegalArgumentException("Player not existing");
        }

        List<Result> moves = new ArrayList<>();
        int currentPosition = players.get(name);
        moves.add(new RollsResult(name, dice1String, dice2String));

        int dice1 = Integer.parseInt(dice1String);
        int dice2 = Integer.parseInt(dice2String);
        int newPosition = currentPosition + dice1 + dice2;
        moves.add(new MoveResult(name, getSpecialName(currentPosition), getSpecialName(newPosition)));

        players.put(name, newPosition);
        return moves;
    }

    private String getSpecialName(int position) {
        if (specialPositions.containsKey(position)) {
            return specialPositions.get(position);
        } else {
            return String.valueOf(position);
        }
    }
}
