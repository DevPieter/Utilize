package nl.devpieter.utilize.utils.minecraft;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import nl.devpieter.utilize.utils.common.MathUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class WorldUtils {

    private WorldUtils() {
    }

    public static @Nullable BlockState getStateAt(@NotNull Vec3d vec3d) {
        return getStateAt(MathUtils.from(vec3d));
    }

    public static @Nullable BlockState getStateAt(@NotNull Vec3i vec3i) {
        return getStateAt(new BlockPos(vec3i));
    }

    public static @Nullable BlockState getStateAt(@NotNull BlockPos pos) {
        if (!ClientUtils.hasWorld()) return null;
        return ClientUtils.getWorld().getBlockState(pos);
    }

    public static @Nullable Block getBlockAt(@NotNull Vec3d vec3d) {
        return getBlockAt(MathUtils.from(vec3d));
    }

    public static @Nullable Block getBlockAt(@NotNull Vec3i vec3i) {
        return getBlockAt(new BlockPos(vec3i));
    }

    public static @Nullable Block getBlockAt(@NotNull BlockPos pos) {
        BlockState state = getStateAt(pos);
        if (state == null) return null;

        return state.getBlock();
    }

    public static @Nullable Entity getEntity(int id) {
        if (!ClientUtils.hasWorld()) return null;
        return ClientUtils.getWorld().getEntityById(id);
    }
}
