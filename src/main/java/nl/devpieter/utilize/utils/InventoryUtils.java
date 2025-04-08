package nl.devpieter.utilize.utils;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class InventoryUtils {

    public static boolean hasInventory() {
        return ClientUtils.hasPlayer() && ClientUtils.getPlayer().getInventory() != null;
    }

    public static @Nullable PlayerInventory getInventory() {
        if (!ClientUtils.hasPlayer()) return null;
        return ClientUtils.getPlayer().getInventory();
    }

    public static boolean isMainHandOf(Item item) {
        ItemStack mainHand = getMainHand();
        return mainHand != null && mainHand.isOf(item);
    }

    public static @Nullable ItemStack getMainHand() {
        if (!hasInventory()) return null;
        return ClientUtils.getPlayer().getMainHandStack();
    }

    public static boolean isOffhandOf(Item item) {
        ItemStack offhand = getOffhand();
        return offhand != null && offhand.isOf(item);
    }

    public static @Nullable ItemStack getOffhand() {
        if (!hasInventory()) return null;
        return ClientUtils.getPlayer().getOffHandStack();
    }

    public static void selectHotbarSlot(int slot) {
        if (slot < 0 || slot > 8 || !hasInventory()) return;
        getInventory().selectedSlot = slot;
    }

    public static @NotNull List<Integer> findHotbarSlots(Item item) {
        if (!hasInventory() || item == null) return new ArrayList<>();

        PlayerInventory inventory = getInventory();
        List<Integer> slots = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            ItemStack stack = inventory.getStack(i);
            if (stack.isOf(item)) slots.add(i);
        }

        return slots;
    }

    public static int countItem(Item item) {
        if (!hasInventory() || item == null) return 0;
        PlayerInventory inventory = getInventory();

        int count = 0;

        for (int i = 0; i < inventory.size(); i++) {
            ItemStack stack = inventory.getStack(i);
            if (stack.isOf(item)) count += stack.getCount();
        }

        return count;
    }

    public static void clickSlot(int syncId, int packetSlot, int button, SlotActionType actionType) {
        InteractionUtils.clickInventorySlot(syncId, packetSlot, button, actionType);
    }

    public static void swapItemWithOffhand() {
        NetworkUtils.sendPacket(new PlayerActionC2SPacket(PlayerActionC2SPacket.Action.SWAP_ITEM_WITH_OFFHAND, BlockPos.ORIGIN, Direction.DOWN));
    }
}
