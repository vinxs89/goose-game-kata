package kata.game.goose.game;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameConfiguration {

    private int length;
    private Map<Integer, Integer> bridges = new HashMap<>();
    private List<Integer> gooses;

    public GameConfiguration() {
        this.length = 63;
        bridges.put(6, 12);
        gooses = Arrays.asList(5, 9, 14, 18, 23, 27);
    }

    public int getLength() {
        return length;
    }

    public Map<Integer, Integer> getBridges() {
        return bridges;
    }

    public boolean isBridge(int position) {
        return bridges.containsKey(position);
    }

    public int getBridgePosition(int position) {
        return bridges.get(position);
    }

    public List<Integer> getGooses() {
        return gooses;
    }

    public boolean isGoose(int position) {
        return gooses.contains(position);
    }
}
