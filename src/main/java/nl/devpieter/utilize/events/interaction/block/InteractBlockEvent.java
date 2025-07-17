package nl.devpieter.utilize.events.interaction.block;

import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import nl.devpieter.sees.event.SCancelableEventBase;

/**
 * It's not recommended to directly cancel this event, as it may cause issues with the game logic.
 * Instead, use the {@link nl.devpieter.utilize.events.interaction.keybinding.UseKeyPressedEvent} to directly cancel the use action.
 */
public class InteractBlockEvent extends SCancelableEventBase {

    private final Hand hand;
    private final BlockHitResult hitResult;

    public InteractBlockEvent(Hand hand, BlockHitResult hitResult) {
        this.hand = hand;
        this.hitResult = hitResult;
    }

    public Hand hand() {
        return this.hand;
    }

    public BlockHitResult hitResult() {
        return this.hitResult;
    }
}
