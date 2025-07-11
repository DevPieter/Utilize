package nl.devpieter.utilize.events.interaction;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import nl.devpieter.sees.Event.CancelableEventBase;

public class InteractItemEvent extends CancelableEventBase {

    private final Hand hand;

    @Deprecated(since = "1.0.9", forRemoval = true)
    public InteractItemEvent(PlayerEntity player, Hand hand) {
        this.hand = hand;
    }

    public InteractItemEvent(Hand hand) {
        this.hand = hand;
    }

    @Deprecated(since = "1.0.9", forRemoval = true)
    public PlayerEntity player() {
        return MinecraftClient.getInstance().player;
    }

    public Hand hand() {
        return hand;
    }
}
