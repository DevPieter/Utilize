package nl.devpieter.utilize.utils;

public class InventoryUtils {

    public static int toPacketSlot(int slot) {
        if (slot >= 0 && slot < 9) return 36 + slot; // Hotbar
        if (slot >= 36 && slot < 40) return 44 - slot; // Armor
        if (slot == 40) return 45; // Offhand

        return slot;
    }
}
