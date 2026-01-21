package nl.devpieter.utilize.utils.minecraft;

import net.minecraft.screen.slot.SlotActionType;

public final class InteractionUtils {

    private InteractionUtils() {
    }

    public static void clickInventorySlot(int syncId, int packetSlot, int button, SlotActionType actionType) {
        if (!ClientUtils.hasInteractionManager() || !ClientUtils.hasPlayer()) return;
        ClientUtils.getInteractionManager().clickSlot(syncId, packetSlot, button, actionType, ClientUtils.getPlayer());
    }
}
