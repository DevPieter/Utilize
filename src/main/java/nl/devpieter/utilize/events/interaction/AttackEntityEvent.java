package nl.devpieter.utilize.events.interaction;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import nl.devpieter.sees.Event.CancelableEventBase;

public class AttackEntityEvent extends CancelableEventBase {

    private final PlayerEntity player;
    private final Entity target;

    public AttackEntityEvent(PlayerEntity player, Entity target) {
        this.player = player;
        this.target = target;
    }

    public PlayerEntity player() {
        return player;
    }

    public Entity target() {
        return target;
    }
}
