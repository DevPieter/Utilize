package nl.devpieter.utilize.utils;

import java.time.Duration;

public final class MathUtils {

    private MathUtils() {
    }

    public static int secondsToTicks(int seconds) {
        return seconds * 20;
    }

    public static int ticksToSeconds(int ticks) {
        return ticks / 20;
    }

    public static int durationToTicks(Duration duration) {
        return (int) (duration.toMillis() / 50);
    }

    public static double notNegative(double value) {
        return Math.max(0, value);
    }

    public static double notPositive(double value) {
        return Math.min(0, value);
    }

    public static double notBelow(double value, double min) {
        return Math.max(value, min);
    }

    public static double notAbove(double value, double max) {
        return Math.min(value, max);
    }

    /**
     * @param percentage The percentage to reduce by (e.g., 10 for 10%).
     */
    public static double reduceByPercentage(double value, double percentage) {
        return value - (value * (percentage / 100));
    }

    /**
     * @param percentage The percentage to increase by (e.g., 10 for 10%).
     */
    public static double increaseByPercentage(double value, double percentage) {
        return value + (value * (percentage / 100));
    }

    /**
     * @param percentage The percentage of the value to return (e.g., 10 for 10%).
     */
    public static double getPercentage(double value, double percentage) {
        return value * (percentage / 100);
    }
}
