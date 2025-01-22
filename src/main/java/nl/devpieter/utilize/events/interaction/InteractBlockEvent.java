package nl.devpieter.utilize.events.interaction;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import nl.devpieter.sees.Event.CancelableEventBase;

public class InteractBlockEvent extends CancelableEventBase {

    private final ClientPlayerEntity player;
    private final Hand hand;
    private final BlockHitResult hitResult;

    public InteractBlockEvent(ClientPlayerEntity player, Hand hand, BlockHitResult hitResult) {
        this.player = player;
        this.hand = hand;
        this.hitResult = hitResult;
    }

    public ClientPlayerEntity player() {
        return player;
    }

    public Hand hand() {
        return hand;
    }

    public BlockHitResult hitResult() {
        return hitResult;
    }
}
