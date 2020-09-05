package kata.game.goose.game;

import kata.game.goose.results.*;

import java.util.*;

public class StandardGooseGame implements GooseGame {

    Map<String, Integer> players = new LinkedHashMap<>();

    private Map<Integer, String> specialPositions = new LinkedHashMap<>();
    private int gameLength = 63;

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
        int newPosition = calculateNewPosition(moves, name, currentPosition, dice1, dice2);
        players.put(name, newPosition);

        return moves;
    }

    private int calculateNewPosition(List<Result> moves, String name, int currentPosition, int dice1, int dice2) {
        int newPosition = currentPosition + dice1 + dice2;
        if (newPosition < gameLength) {
            moves.add(new MoveResult(name, getSpecialName(currentPosition), getSpecialName(newPosition)));
        } else if (newPosition == gameLength) {
            moves.add(new MoveResult(name, getSpecialName(currentPosition), getSpecialName(newPosition)));
            moves.add(new WinResult(name));
        } else {
            moves.add(new MoveResult(name, getSpecialName(currentPosition), getSpecialName(gameLength)));
            newPosition = gameLength - (newPosition - gameLength);
            moves.add(new BounceResult(name, newPosition));
        }
        return newPosition;
    }

    private String getSpecialName(int position) {
        if (specialPositions.containsKey(position)) {
            return specialPositions.get(position);
        } else {
            return String.valueOf(position);
        }
    }
}
