package nl.devpieter.utilize.events.interaction.block;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import nl.devpieter.sees.event.SCancelableEventBase;

/**
 * It's not recommended to directly cancel this event, as it may cause issues with the game logic.
 * Instead, use the {@link nl.devpieter.utilize.events.interaction.keybinding.AttackKeyPressedEvent} to directly cancel the attack action.
 */
public class AttackBlockEvent extends SCancelableEventBase {

    private final BlockPos blockPos;
    private final Direction direction;

    public AttackBlockEvent(BlockPos blockPos, Direction direction) {
        this.blockPos = blockPos;
        this.direction = direction;
    }

    public BlockPos blockPos() {
        return this.blockPos;
    }

    public Direction direction() {
        return this.direction;
    }
}
