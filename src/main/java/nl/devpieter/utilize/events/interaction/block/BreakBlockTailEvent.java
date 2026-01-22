package nl.devpieter.utilize.events.interaction.block;

import net.minecraft.util.math.BlockPos;
import nl.devpieter.sees.event.SEvent;

public record BreakBlockTailEvent(BlockPos blockPos) implements SEvent {
}
