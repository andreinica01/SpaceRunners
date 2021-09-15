package Utilities;

import java.util.Random;

public class Utilities {

    private static final double VALUE = 1000;

    /**
     * @param min.
     * @param max.
     * @return a random double value between two values.
     */
    public static Double getRandomDouble(final Double min, final Double max) {
        return min + new Random().nextDouble() * (max - min);
    }

    /**
     * @param min.
     * @param max.
     * @return a random long value between two values.
     */
    public static Long getRandomMillis(final Double min, final Double max) {
        return (long) (getRandomDouble(min, max) * VALUE);
    }
}