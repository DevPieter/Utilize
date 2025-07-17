package nl.devpieter.utilize.events.interaction.entity;

import net.minecraft.entity.Entity;
import net.minecraft.util.Hand;
import nl.devpieter.sees.event.SCancelableEventBase;

/**
 * It's not recommended to directly cancel this event, as it may cause issues with the game logic.
 * Instead, use the {@link nl.devpieter.utilize.events.interaction.keybinding.UseKeyPressedEvent} to directly cancel the use action.
 */
public class InteractEntityEvent extends SCancelableEventBase {

    private final Entity target;
    private final Hand hand;

    public InteractEntityEvent(Entity target, Hand hand) {
        this.hand = hand;
        this.target = target;
    }

    public Entity target() {
        return this.target;
    }

    public Hand hand() {
        return this.hand;
    }
}
