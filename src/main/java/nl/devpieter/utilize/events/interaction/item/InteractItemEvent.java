package nl.devpieter.utilize.events.interaction.item;

import net.minecraft.util.Hand;
import nl.devpieter.sees.event.SCancelableEventBase;

/**
 * It's not recommended to directly cancel this event, as it may cause issues with the game logic.
 * Instead, use the {@link nl.devpieter.utilize.events.interaction.keybinding.UseKeyPressedEvent} to directly cancel the use action.
 */
public class InteractItemEvent extends SCancelableEventBase {

    private final Hand hand;

    public InteractItemEvent(Hand hand) {
        this.hand = hand;
    }

    public Hand hand() {
        return this.hand;
    }
}
