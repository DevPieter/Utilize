package nl.devpieter.utilize.events.interaction.entity;

import net.minecraft.entity.Entity;
import net.minecraft.util.Hand;
import nl.devpieter.sees.event.SEvent;

public record InteractEntityTailEvent(Entity target, Hand hand) implements SEvent {
}
