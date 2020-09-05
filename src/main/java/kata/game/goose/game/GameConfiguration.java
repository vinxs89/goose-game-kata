package kata.game.goose.game;

import java.util.HashMap;
import java.util.Map;

public class GameConfiguration {

    private int length;
    private Map<Integer, Integer> bridges = new HashMap<>();

    public GameConfiguration() {
        this.length = 63;
        bridges.put(6, 12);
    }

    public int getLength() {
        return length;
    }

    public Map<Integer, Integer> getBridges() {
        return bridges;
    }

    public boolean isBridge(int newPosition) {
        return bridges.containsKey(newPosition);
    }

    public int getBridgePosition(int newPosition) {
        return bridges.get(newPosition);
    }
}
