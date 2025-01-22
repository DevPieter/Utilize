package nl.devpieter.utilize.events.interaction;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import nl.devpieter.sees.Event.CancelableEventBase;

public class InteractEntityEvent extends CancelableEventBase {

    private final PlayerEntity player;
    private final Entity target;
    private final Hand hand;

    public InteractEntityEvent(PlayerEntity player, Entity target, Hand hand) {
        this.player = player;
        this.target = target;
        this.hand = hand;
    }

    public PlayerEntity player() {
        return player;
    }

    public Entity target() {
        return target;
    }

    public Hand hand() {
        return hand;
    }
}
