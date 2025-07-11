package nl.devpieter.utilize.events.interaction;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import nl.devpieter.sees.Event.CancelableEventBase;

public class InteractEntityEvent extends CancelableEventBase {

    private final Entity target;
    private final Hand hand;

    @Deprecated(since = "1.0.9", forRemoval = true)
    public InteractEntityEvent(PlayerEntity player, Entity target, Hand hand) {
        this.target = target;
        this.hand = hand;
    }

    public InteractEntityEvent(Entity target, Hand hand) {
        this.target = target;
        this.hand = hand;
    }

    @Deprecated(since = "1.0.9", forRemoval = true)
    public PlayerEntity player() {
        return MinecraftClient.getInstance().player;
    }

    public Entity target() {
        return target;
    }

    public Hand hand() {
        return hand;
    }
}
