package nl.devpieter.utilize.events.interaction;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import nl.devpieter.sees.Event.CancelableEventBase;

public class InteractBlockEvent extends CancelableEventBase {

    private final Hand hand;
    private final BlockHitResult hitResult;

    @Deprecated(since = "1.0.9", forRemoval = true)
    public InteractBlockEvent(ClientPlayerEntity player, Hand hand, BlockHitResult hitResult) {
        this.hand = hand;
        this.hitResult = hitResult;
    }

    public InteractBlockEvent(Hand hand, BlockHitResult hitResult) {
        this.hand = hand;
        this.hitResult = hitResult;
    }

    @Deprecated(since = "1.0.9", forRemoval = true)
    public ClientPlayerEntity player() {
        return MinecraftClient.getInstance().player;
    }

    public Hand hand() {
        return hand;
    }

    public BlockHitResult hitResult() {
        return hitResult;
    }
}
