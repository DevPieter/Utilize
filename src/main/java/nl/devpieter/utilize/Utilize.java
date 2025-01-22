package nl.devpieter.utilize;

import net.fabricmc.api.ClientModInitializer;
import nl.devpieter.sees.Sees;
import nl.devpieter.utilize.test.listeners.EventListeners;

public class Utilize implements ClientModInitializer {

    private static boolean BLOCK_SWING_HAND_ONCE = false;

    @Override
    public void onInitializeClient() {
        Sees sees = Sees.getInstance();
        sees.subscribe(new EventListeners());
    }

    public static boolean shouldBlockSwingHandOnce() {
        return BLOCK_SWING_HAND_ONCE;
    }

    public static void blockSwingHandOnce() {
        BLOCK_SWING_HAND_ONCE = true;
    }

    public static void blockedSwingHandOnce() {
        BLOCK_SWING_HAND_ONCE = false;
    }
}
