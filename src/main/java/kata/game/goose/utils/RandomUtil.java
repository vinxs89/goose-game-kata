package kata.game.goose.utils;

import java.util.Random;

public class RandomUtil {

    private static final RandomUtil INSTANCE = new RandomUtil();

    private RandomUtil() {
    }

    public static RandomUtil getInstance() {
        return INSTANCE;
    }

    // TODO check why random instance is not working
    public String randomDice() {
        return String.valueOf(new Random().nextInt(6) + 1);
    }
}
