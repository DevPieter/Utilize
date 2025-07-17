package nl.devpieter.utilize.events.interaction.block;

import net.minecraft.util.math.BlockPos;
import nl.devpieter.sees.event.SCancelableEventBase;
import nl.devpieter.sees.event.SReturnableEvent;

public class BreakBlockReturnEvent implements SReturnableEvent<Boolean> {

    private final BlockPos blockPos;
    private boolean returnValue;

    public BreakBlockReturnEvent(BlockPos blockPos, boolean returnValue) {
        this.blockPos = blockPos;
        this.returnValue = returnValue;
    }

    public BlockPos blockPos() {
        return this.blockPos;
    }

    @Override
    public Boolean getResult() {
        return this.returnValue;
    }

    @Override
    public void setResult(Boolean result) {
        this.returnValue = result;
    }
}
