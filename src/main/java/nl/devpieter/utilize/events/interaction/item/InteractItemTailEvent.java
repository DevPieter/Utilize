package nl.devpieter.utilize.events.interaction.item;

import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import nl.devpieter.sees.event.SEvent;

public record InteractItemTailEvent(Hand hand) implements SEvent {
}
