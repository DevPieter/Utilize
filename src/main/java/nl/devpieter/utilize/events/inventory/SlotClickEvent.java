package nl.devpieter.utilize.events.inventory;

import net.minecraft.screen.slot.SlotActionType;
import nl.devpieter.sees.event.SCancelableEventBase;

public class SlotClickEvent extends SCancelableEventBase {

    private final int syncId;
    private final int slotId;
    private final int button;
    private final SlotActionType actionType;

    public SlotClickEvent(int syncId, int slotId, int button, SlotActionType actionType) {
        this.syncId = syncId;
        this.slotId = slotId;
        this.button = button;
        this.actionType = actionType;
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
}
