package nl.devpieter.utilize.events.interaction.block;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import nl.devpieter.sees.event.SReturnableEvent;

public class AttackBlockReturnEvent implements SReturnableEvent<Boolean> {

    private final BlockPos blockPos;
    private final Direction direction;
    private boolean returnValue;

    public AttackBlockReturnEvent(BlockPos blockPos, Direction direction, boolean returnValue) {
        this.blockPos = blockPos;
        this.direction = direction;
        this.returnValue = returnValue;
    }

    public BlockPos blockPos() {
        return this.blockPos;
    }

    public Direction direction() {
        return this.direction;
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
