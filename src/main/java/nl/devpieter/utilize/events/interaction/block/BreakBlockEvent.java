package nl.devpieter.utilize.events.interaction.block;

import net.minecraft.util.math.BlockPos;
import nl.devpieter.sees.event.SCancelableEventBase;

/**
 * It's not recommended to directly cancel this event, as it may cause issues with the game logic.
 * Instead, use the {@link nl.devpieter.utilize.events.interaction.keybinding.AttackKeyPressedEvent} to directly cancel the attack action.
 */
public class BreakBlockEvent extends SCancelableEventBase {

    private final BlockPos blockPos;

    public BreakBlockEvent(BlockPos blockPos) {
        this.blockPos = blockPos;
    }

    public BlockPos blockPos() {
        return this.blockPos;
    }
}
