package nl.devpieter.utilize.events.interaction;

import net.minecraft.entity.Entity;
import nl.devpieter.sees.event.SCancelableEventBase;

public class AttackEntityEvent extends SCancelableEventBase {

    private final Entity target;

    public AttackEntityEvent(Entity target) {
        this.target = target;
    }

    public Entity target() {
        return target;
    }
}
