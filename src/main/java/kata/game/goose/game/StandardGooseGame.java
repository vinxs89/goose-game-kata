package kata.game.goose.game;

import kata.game.goose.results.*;

import java.util.*;

public class StandardGooseGame implements GooseGame {

    Map<String, Integer> players = new LinkedHashMap<>();

    private GameConfiguration gameConfiguration;
    private Map<Integer, String> specialPositions = new LinkedHashMap<>();

    // TODO Move translations to specific classs
    public StandardGooseGame(GameConfiguration gameConfiguration) {
        this.gameConfiguration = gameConfiguration;
        specialPositions.put(0, "Start");
        gameConfiguration.getBridges().keySet().forEach(b -> specialPositions.put(b, "The Bridge"));
        gameConfiguration.getGooses().forEach(g -> specialPositions.put(g, g + ", The Goose"));
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

        if (newPosition <= gameConfiguration.getLength()) {
            moves.add(new MoveResult(name, getSpecialName(currentPosition), getSpecialName(newPosition)));
        } else {
            moves.add(new MoveResult(name, getSpecialName(currentPosition), getSpecialName(gameConfiguration.getLength())));
            newPosition = gameConfiguration.getLength() - (newPosition - gameConfiguration.getLength());
            moves.add(new BounceResult(name, newPosition));
        }

        if (newPosition == gameConfiguration.getLength()) {
            moves.add(new WinResult(name));
        } else {
            newPosition = checkForBridge(newPosition, name, moves);
            newPosition = checkForGoose(newPosition, name, dice1, dice2, moves);
            doPrankIfPossible(name, newPosition, currentPosition, moves);
        }

        return newPosition;
    }

    private int checkForBridge(int newPosition, String name, List<Result> moves) {
        if (gameConfiguration.isBridge(newPosition)) {
            newPosition = gameConfiguration.getBridgePosition(newPosition);
            moves.add(new BridgeResult(name, getSpecialName(newPosition)));
        }
        return newPosition;
    }

    private int checkForGoose(int newPosition, String name, int dice1, int dice2, List<Result> moves) {
        while (gameConfiguration.isGoose(newPosition)) {
            newPosition += dice1 + dice2;
            moves.add(new GooseResult(name, getSpecialName(newPosition)));
        }
        return newPosition;
    }

    private void doPrankIfPossible(String name, int newPosition, int oldPosition, List<Result> moves) {
        players.forEach((key, value) -> {
            if (newPosition == value && !(name.equals(key))) {
                players.put(key, oldPosition);
                moves.add(new PrankResult(key, getSpecialName(newPosition), getSpecialName(oldPosition)));
            }
        });
    }

    private String getSpecialName(int position) {
        if (specialPositions.containsKey(position)) {
            return specialPositions.get(position);
        } else {
            return String.valueOf(position);
        }
    }
}
