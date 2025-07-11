package nl.devpieter.utilize.events.interaction;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import nl.devpieter.sees.Event.CancelableEventBase;

/**
 * Event fired when a block is attacked by the player.
 * <p>
 * This event is cancelable.
 */
public class AttackBlockEvent extends CancelableEventBase {

    /**
     * The position of the block being attacked.
     */
    private final BlockPos blockPos;

    /**
     * The direction from which the block is attacked.
     */
    private final Direction direction;

    /**
     * Constructs a new AttackBlockEvent with the specified block position and direction.
     *
     * @param blockPos  The position of the block being attacked.
     * @param direction The direction from which the block is attacked.
     */
    public AttackBlockEvent(BlockPos blockPos, Direction direction) {
        this.blockPos = blockPos;
        this.direction = direction;
    }

    /**
     * Gets the position of the block being attacked.
     *
     * @return The position of the block.
     */
    public BlockPos blockPos() {
        return blockPos;
    }

    /**
     * Gets the direction from which the block is attacked.
     *
     * @return The direction of the attack.
     */
    public Direction direction() {
        return direction;
    }
}
