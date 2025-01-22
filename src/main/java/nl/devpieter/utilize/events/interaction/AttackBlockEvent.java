package nl.devpieter.utilize.events.interaction;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import nl.devpieter.sees.Event.CancelableEventBase;

public class AttackBlockEvent extends CancelableEventBase {

    private final BlockPos blockPos;
    private final Direction direction;

    public AttackBlockEvent(BlockPos blockPos, Direction direction) {
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
