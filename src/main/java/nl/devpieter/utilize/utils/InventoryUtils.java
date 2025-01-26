package nl.devpieter.utilize.utils;

import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class InventoryUtils {

    public static void clickSlot(int syncId, int packetSlot, int button, SlotActionType actionType) {
        ClientPlayerInteractionManager interactionManager = ClientUtils.getInteractionManager();
        if (interactionManager == null) return;

        interactionManager.clickSlot(syncId, packetSlot, button, actionType, ClientUtils.getPlayer());
    }

    public static void swapItemWithOffhand() {
        ClientUtils.sendPacket(new PlayerActionC2SPacket(PlayerActionC2SPacket.Action.SWAP_ITEM_WITH_OFFHAND, BlockPos.ORIGIN, Direction.DOWN));
    }

    public static int toPacketSlot(int slot) {
        if (slot >= 0 && slot < 9) return 36 + slot; // Hotbar
        if (slot >= 36 && slot < 40) return 44 - slot; // Armor
        if (slot == 40) return 45; // Offhand

        return slot;
    }
}
