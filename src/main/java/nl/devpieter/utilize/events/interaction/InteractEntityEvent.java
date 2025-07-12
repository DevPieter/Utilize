package nl.devpieter.utilize.events.interaction;

import net.minecraft.entity.Entity;
import net.minecraft.util.Hand;
import nl.devpieter.sees.event.SCancelableEventBase;

public class InteractEntityEvent extends SCancelableEventBase {

    private final Entity target;
    private final Hand hand;

    public InteractEntityEvent(Entity target, Hand hand) {
        this.target = target;
        this.hand = hand;
    }

    public Entity target() {
        return target;
    }

    public Hand hand() {
        return hand;
    }
}
