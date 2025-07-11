package nl.devpieter.utilize.events.inventory;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.slot.SlotActionType;
import nl.devpieter.sees.Event.CancelableEventBase;

public class SlotClickEvent extends CancelableEventBase {

    private final int syncId;
    private final int slotId;
    private final int button;
    private final SlotActionType actionType;

    @Deprecated(since = "1.0.9", forRemoval = true)
    public SlotClickEvent(int syncId, int slotId, int button, SlotActionType actionType, PlayerEntity player) {
        this.syncId = syncId;
        this.slotId = slotId;
        this.button = button;
        this.actionType = actionType;
    }

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

    @Deprecated(since = "1.0.9", forRemoval = true)
    public PlayerEntity player() {
        return MinecraftClient.getInstance().player;
    }
}
