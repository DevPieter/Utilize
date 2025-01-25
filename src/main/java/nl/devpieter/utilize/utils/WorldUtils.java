package nl.devpieter.utilize.utils;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class WorldUtils {

    public static List<? extends PlayerEntity> getNearbyPlayers(float distance) {
        if (!ClientUtils.hasPlayer() || !ClientUtils.hasWorld()) return new ArrayList<>();

        World world = ClientUtils.getWorld();
        PlayerEntity clientPlayer = ClientUtils.getPlayer();

        return world.getPlayers().stream()
                .filter(
                        player -> player != clientPlayer &&
                                player.distanceTo(clientPlayer) <= distance &&
                                player.isAlive()
                ).toList();
    }

    public static boolean arePlayersNearby(float distance) {
        return !getNearbyPlayers(distance).isEmpty();
    }
}
