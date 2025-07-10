package nl.devpieter.utilize.events.interaction;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import nl.devpieter.sees.Event.CancelableEventBase;

/**
 * Event fired when an entity is attacked by the player.
 * <p>
 * This event is cancelable.
 */
public class AttackEntityEvent extends CancelableEventBase {

    /**
     * The entity being attacked.
     */
    private final Entity target;

    /**
     * Constructs a new AttackEntityEvent with the specified player and target entity.
     *
     * @param player The player who is attacking the entity.
     * @param target The entity being attacked.
     */
    @Deprecated(since = "1.0.9", forRemoval = true)
    public AttackEntityEvent(PlayerEntity player, Entity target) {
        this.target = target;
    }

    /**
     * Constructs a new AttackEntityEvent with the specified target entity.
     *
     * @param target The entity being attacked.
     */
    public AttackEntityEvent(Entity target) {
        this.target = target;
    }

    @Deprecated(since = "1.0.9", forRemoval = true)
    public PlayerEntity player() {
        return MinecraftClient.getInstance().player;
    }

    /**
     * Gets the entity being attacked.
     *
     * @return The target entity.
     */
    public Entity target() {
        return target;
    }
}
