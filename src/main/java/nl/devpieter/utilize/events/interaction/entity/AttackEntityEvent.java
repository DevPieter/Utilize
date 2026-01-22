package nl.devpieter.utilize.events.interaction.entity;

import net.minecraft.entity.Entity;
import nl.devpieter.sees.event.SCancelableEventBase;

/**
 * It's not recommended to directly cancel this event, as it may cause issues with the game logic.
 * Instead, use the {@link nl.devpieter.utilize.events.interaction.keybinding.AttackKeyPressedEvent} to directly cancel the attack action.
 */
public class AttackEntityEvent extends SCancelableEventBase {

    private final Entity target;

    public AttackEntityEvent(Entity target) {
        this.target = target;
    }

    public Entity target() {
        return this.target;
    }
}
