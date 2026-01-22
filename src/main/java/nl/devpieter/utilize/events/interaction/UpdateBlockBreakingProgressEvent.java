package nl.devpieter.utilize.events.interaction;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import nl.devpieter.sees.event.SCancelableEventBase;

public class UpdateBlockBreakingProgressEvent extends SCancelableEventBase {

    private final BlockPos blockPos;
    private final Direction direction;

    public UpdateBlockBreakingProgressEvent(BlockPos blockPos, Direction direction) {
        this.blockPos = blockPos;
        this.direction = direction;
    }

    public BlockPos blockPos() {
        return blockPos;
    }

    public Direction direction() {
        return direction;
    }
}
