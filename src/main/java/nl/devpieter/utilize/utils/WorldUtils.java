package nl.devpieter.utilize.utils;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class WorldUtils {

    public static BlockState getStateAt(BlockPos pos) {
        if (!ClientUtils.hasWorld()) return null;
        return ClientUtils.getWorld().getBlockState(pos);
    }

    public static Block getBlockAt(BlockPos pos) {
        BlockState state = getStateAt(pos);
        if (state == null) return null;

        return state.getBlock();
    }

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

    public static boolean arePlayersNearby(float distance) {
        return !getNearbyPlayers(distance).isEmpty();
    }

    public static List<? extends MobEntity> getNearbyMobs(float distance) {
        if (!ClientUtils.hasPlayer() || !ClientUtils.hasWorld()) return new ArrayList<>();

        World world = ClientUtils.getWorld();
        PlayerEntity clientPlayer = ClientUtils.getPlayer();

        return world.getEntitiesByClass(MobEntity.class, clientPlayer.getBoundingBox().expand(distance), LivingEntity::isAlive);
    }

    public static boolean areMobsNearby(float distance) {
        return !getNearbyMobs(distance).isEmpty();
    }
}
