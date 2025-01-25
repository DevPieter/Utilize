package nl.devpieter.utilize.events.screen;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.slot.SlotActionType;
import nl.devpieter.sees.Event.CancelableEventBase;

public class SlotClickEvent extends CancelableEventBase {

    private final int syncId;
    private final int slotId;
    private final int button;
    private final SlotActionType actionType;
    private final PlayerEntity player;

    public SlotClickEvent(int syncId, int slotId, int button, SlotActionType actionType, PlayerEntity player) {
        this.syncId = syncId;
        this.slotId = slotId;
        this.button = button;
        this.actionType = actionType;
        this.player = player;
    }

    public int syncId() {
        return syncId;
    }

    public int slotId() {
        return slotId;
    }

    public int button() {
        return button;
    }

    public SlotActionType actionType() {
        return actionType;
    }

    public PlayerEntity player() {
        return player;
    }
}
