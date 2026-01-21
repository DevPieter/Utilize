package nl.devpieter.utilize.utils.common;

import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;

public class MathUtils {

    public static int secondsToTicks(int seconds) {
        return seconds * 20;
    }

    public static int ticksToSeconds(int ticks) {
        return ticks / 20;
    }

    public static int durationToTicks(Duration duration) {
        return (int) (duration.toMillis() / 50);
    }

    @Contract("_ -> new")
    public static @NotNull Vec3d from(@NotNull Vec3i vec3i) {
        return new Vec3d(vec3i.getX(), vec3i.getY(), vec3i.getZ());
    }

    @Contract("_ -> new")
    public static @NotNull Vec3i from(@NotNull Vec3d vec3d) {
        return new Vec3i((int) vec3d.getX(), (int) vec3d.getY(), (int) vec3d.getZ());
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
