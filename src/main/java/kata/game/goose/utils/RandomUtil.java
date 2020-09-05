package kata.game.goose.utils;

import java.util.Random;

public class RandomUtil {

    private static final RandomUtil INSTANCE = new RandomUtil();
    private Random random = new Random();

    private RandomUtil() {
    }

    public static RandomUtil getInstance() {
        return INSTANCE;
    }

    public String randomDice() {
        return String.valueOf(random.nextInt(6) + 1);
    }
}
