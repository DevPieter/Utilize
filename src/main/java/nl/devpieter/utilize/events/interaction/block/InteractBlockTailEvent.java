package nl.devpieter.utilize.events.interaction.block;

import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import nl.devpieter.sees.event.SEvent;

public record InteractBlockTailEvent(Hand hand, BlockHitResult hitResult) implements SEvent {
}
