package nl.devpieter.utilize.events.interaction;

import net.minecraft.util.math.BlockPos;
import nl.devpieter.sees.Event.CancelableEventBase;

public class BreakBlockEvent extends CancelableEventBase {

    private final BlockPos blockPos;

    public BreakBlockEvent(BlockPos blockPos) {
        this.blockPos = blockPos;
    }

    public BlockPos blockPos() {
        return blockPos;
    }
}
