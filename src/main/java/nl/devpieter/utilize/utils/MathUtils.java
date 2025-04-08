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
}
