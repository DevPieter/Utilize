package nl.devpieter.utilize.events.packet;

import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.text.Text;
import nl.devpieter.sees.event.SCancelableEventBase;

public class ScreenOpenedPacketEvent extends SCancelableEventBase {

    private final int syncId;
    private final ScreenHandlerType<?> type;
    private final Text name;

    public ScreenOpenedPacketEvent(int syncId, ScreenHandlerType<?> type, Text name) {
        this.syncId = syncId;
        this.type = type;
        this.name = name;
    }

    public int syncId() {
        return syncId;
    }

    public ScreenHandlerType<?> type() {
        return type;
    }

    public Text name() {
        return name;
    }
}
