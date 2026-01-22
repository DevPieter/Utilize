package nl.devpieter.utilize.events.interaction.block;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import nl.devpieter.sees.event.SEvent;

public record AttackBlockTailEvent(BlockPos blockPos, Direction direction) implements SEvent {
}
