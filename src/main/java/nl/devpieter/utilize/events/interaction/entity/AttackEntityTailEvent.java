package nl.devpieter.utilize.events.interaction.entity;

import net.minecraft.entity.Entity;
import nl.devpieter.sees.event.SEvent;

public record AttackEntityTailEvent(Entity target) implements SEvent {
}
