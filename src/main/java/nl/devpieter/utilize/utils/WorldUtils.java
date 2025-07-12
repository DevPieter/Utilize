package nl.devpieter.utilize.utils;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class WorldUtils {

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

    @Deprecated(since = "1.0.11", forRemoval = true)
    public static List<? extends PlayerEntity> getNearbyPlayers(float distance) {
        if (!ClientUtils.hasPlayer() || !ClientUtils.hasWorld()) return new ArrayList<>();

        World world = ClientUtils.getWorld();
        PlayerEntity clientPlayer = ClientUtils.getPlayer();

        return world.getPlayers().stream()
                .filter(player ->
                        player != clientPlayer &&
                                player.distanceTo(clientPlayer) <= distance &&
                                player.isAlive()
                ).toList();
    }

    @Deprecated(since = "1.0.11", forRemoval = true)
    public static boolean arePlayersNearby(float distance) {
        return !getNearbyPlayers(distance).isEmpty();
    }

    @Deprecated(since = "1.0.11", forRemoval = true)
    public static List<? extends MobEntity> getNearbyMobs(float distance) {
        if (!ClientUtils.hasPlayer() || !ClientUtils.hasWorld()) return new ArrayList<>();

        World world = ClientUtils.getWorld();
        PlayerEntity clientPlayer = ClientUtils.getPlayer();

        return world.getEntitiesByClass(MobEntity.class, clientPlayer.getBoundingBox().expand(distance), LivingEntity::isAlive);
    }

    @Deprecated(since = "1.0.11", forRemoval = true)
    public static boolean areMobsNearby(float distance) {
        return !getNearbyMobs(distance).isEmpty();
    }
}
