package nl.devpieter.utilize.managers;

import net.minecraft.item.Items;
import nl.devpieter.sees.Sees;
import nl.devpieter.utilize.events.inventory.TotemCountChangedEvent;
import nl.devpieter.utilize.events.inventory.TotemHoldingChangedEvent;
import nl.devpieter.utilize.utils.InventoryUtils;

public class TotemManager {

    private static TotemManager INSTANCE;

    private final Sees sees = Sees.getInstance();

    private int currentTotems = 0;
    private boolean holdingMainHand = false;
    private boolean holdingOffhand = false;

    private TotemManager() {
    }

    public static TotemManager getInstance() {
        if (INSTANCE == null) INSTANCE = new TotemManager();
        return INSTANCE;
    }

    public void tick() {
        this.tickTotemCount();
        this.tickHoldingTotem();
    }

    public int getCurrentTotems() {
        return this.currentTotems;
    }

    public boolean isHoldingTotem() {
        return this.holdingMainHand || this.holdingOffhand;
    }

    public boolean isHoldingTotemInMainHand() {
        return this.holdingMainHand;
    }

    public boolean isHoldingTotemInOffhand() {
        return this.holdingOffhand;
    }

    private void tickTotemCount() {
        int totemCount = InventoryUtils.countItem(Items.TOTEM_OF_UNDYING);
        if (totemCount == this.currentTotems) return;

        this.sees.call(new TotemCountChangedEvent(this.currentTotems, totemCount));
        this.currentTotems = totemCount;
    }

    private void tickHoldingTotem() {
        boolean mainHand = InventoryUtils.isMainHandOf(Items.TOTEM_OF_UNDYING);
        boolean offhand = InventoryUtils.isOffhandOf(Items.TOTEM_OF_UNDYING);
        if (mainHand == this.holdingMainHand && offhand == this.holdingOffhand) return;

        this.sees.call(new TotemHoldingChangedEvent(this.holdingMainHand, mainHand, this.holdingOffhand, offhand));
        this.holdingMainHand = mainHand;
        this.holdingOffhand = offhand;
    }
}
