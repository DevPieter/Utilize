package nl.devpieter.utilize.test.listeners;

import nl.devpieter.sees.Annotations.EventListener;
import nl.devpieter.sees.Listener.Listener;
import nl.devpieter.utilize.events.interaction.AttackBlockEvent;
import nl.devpieter.utilize.events.inventory.TotemCountChangedEvent;
import nl.devpieter.utilize.events.inventory.TotemHoldingChangedEvent;

public class EventListeners implements Listener {

//    @EventListener
//    public void onSleepStateChanged(SleepStateChangedEvent event) {
//        System.out.println("SleepStateChangedEvent " + event.previous() + " -> " + event.current());
//    }
//
//    @EventListener
//    public void onUpdateBlockBreakingProgress(UpdateBlockBreakingProgressEvent event) {
//        System.out.println("UpdateBlockBreakingProgressEvent");

    /// /        event.cancel();
//    }
//
    @EventListener
    public void onAttackBlock(AttackBlockEvent event) {
        System.out.println("AttackBlockEvent");
        event.cancel();
    }
//
//    @EventListener
//    public void onAttackEntity(AttackEntityEvent event) {
//        System.out.println("AttackEntityEvent");
////        event.cancel();
//    }
//
//    @EventListener
//    public void onBreakBlock(BreakBlockEvent event) {
//        System.out.println("BreakBlockEvent");
////        event.cancel();
//    }
//
//    @EventListener
//    public void onInteractBlock(InteractBlockEvent event) {
//        System.out.println("InteractBlockEvent");
////        event.cancel();
//    }
//
//    @EventListener
//    public void onInteractEntity(InteractEntityEvent event) {
//        System.out.println("InteractEntityEvent");
////        event.cancel();
//    }
//
//    @EventListener
//    public void onInteractItem(InteractItemEvent event) {
//        System.out.println("InteractItemEvent");

    /// /        event.cancel();
//    }
//    @EventListener
//    public void onScreenChanged(ScreenChangedEvent event) {
//
//        String previous = event.previous() == null ? "null" : event.previous().getTitle().getString();
//        String current = event.current() == null ? "null" : event.current().getTitle().getString();
//
//        System.out.println("ScreenChangedEvent " + previous + " -> " + current);
//    }
//
//    @EventListener
//    public void onSlotClick1(SlotClickEvent event) {
//        System.out.println("SlotClickEvent syncId: " + event.syncId() + " slotId: " + event.slotId() + " button: " + event.button() + " actionType: " + event.actionType());
//
//        if (event.slotId() != 45 || Screen.hasAltDown()) return;
//
//        ItemStack offHandStack = event.player().getInventory().getStack(PlayerInventory.OFF_HAND_SLOT);
//        if (offHandStack.isOf(Items.TOTEM_OF_UNDYING)) event.cancel();
//
//        ItemStack cursorStack = event.player().currentScreenHandler.getCursorStack();
//        if (!cursorStack.isOf(Items.TOTEM_OF_UNDYING)) event.cancel();
//    }
//
//    @EventListener
//    public void onSlotClick2(SlotClickEvent event) {
//
//        // SlotClickEvent syncId: 0 slotId: 9 button: 0 actionType: QUICK_MOVE
//        // SlotClickEvent syncId: 0 slotId: 21 button: 40 actionType: SWAP
//
//        if (event.slotId() <= -999) return;
//        if (event.actionType() != SlotActionType.QUICK_MOVE) return;
//
//        int slot = event.slotId();
//        if (slot >= 36) slot -= 36;
//
//        ItemStack cursorStack = event.player().getInventory().getStack(slot);
//        if (!cursorStack.isOf(Items.TOTEM_OF_UNDYING)) return;
//
//        event.cancel();
//
//        MinecraftClient.getInstance().interactionManager.clickSlot(event.syncId(), event.slotId(), PlayerInventory.OFF_HAND_SLOT, SlotActionType.SWAP, event.player());
//    }
    @EventListener
    public void onTotemCountChanged(TotemCountChangedEvent event) {
        System.out.println("TotemCountChangedEvent " + event.previous() + " -> " + event.current());
    }

    @EventListener
    public void onTotemHoldingChanged(TotemHoldingChangedEvent event) {
        System.out.println("TotemHoldingChangedEvent mainHand: " + event.previousMainHand() + " -> " + event.currentMainHand() + " offhand: " + event.previousOffhand() + " -> " + event.currentOffhand());
    }
}
