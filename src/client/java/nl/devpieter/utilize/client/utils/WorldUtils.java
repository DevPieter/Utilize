package nl.devpieter.utilize.client.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class WorldUtils {

    private WorldUtils() {
    }

    public static @Nullable BlockState getStateAt(@NotNull BlockPos pos) {
        if (!ClientUtils.hasLevel()) return null;
        return ClientUtils.getLevel().getBlockState(pos);
    }

    public static @Nullable Block getBlockAt(@NotNull BlockPos pos) {
        BlockState state = getStateAt(pos);
        if (state == null) return null;

        return state.getBlock();
    }

    public static @Nullable Entity getEntity(int id) {
        if (!ClientUtils.hasLevel()) return null;
        return ClientUtils.getLevel().getEntity(id);
    }
}
