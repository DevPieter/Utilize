package nl.devpieter.utilize.utils;

import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class MathUtils {

    @Contract("_ -> new")
    public static @NotNull Vec3d from(@NotNull Vec3i vec3i) {
        return new Vec3d(vec3i.getX(), vec3i.getY(), vec3i.getZ());
    }

    @Contract("_ -> new")
    public static @NotNull Vec3i from(@NotNull Vec3d vec3d) {
        return new Vec3i((int) vec3d.getX(), (int) vec3d.getY(), (int) vec3d.getZ());
    }

    public static double notNegative(double value) {
        return value < 0 ? 0 : value;
    }

    public static double notPositive(double value) {
        return value > 0 ? 0 : value;
    }

    public static double notBelow(double value, double min) {
        return Math.max(value, min);
    }

    public static double notAbove(double value, double max) {
        return Math.min(value, max);
    }

    /**
     * @param percentage The percentage to reduce by (0-100).
     */
    public static double reduceByPercentage(double value, double percentage) {
        return value - (value * (percentage / 100));
    }

    /**
     * @param percentage The percentage to increase by (0-100).
     */
    public static double increaseByPercentage(double value, double percentage) {
        return value + (value * (percentage / 100));
    }

    /**
     * @param percentage The percentage of the value to return (0-100).
     */
    public static double getPercentage(double value, double percentage) {
        return value * (percentage / 100);
    }
}
