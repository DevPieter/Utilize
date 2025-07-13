package nl.devpieter.utilize.utils.common;

public class RandomUtils {

    /**
     * @param chanceForTrue The chance to return true (0-100).
     */
    public static boolean randomChance(double chanceForTrue) {
        return Math.random() * 100 < chanceForTrue;
    }

    public static int randomIntInclusive(int min, int max) {
        return (int) (Math.random() * (max - min + 1)) + min;
    }
}
