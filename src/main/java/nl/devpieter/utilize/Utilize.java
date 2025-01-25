package nl.devpieter.utilize;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.SharedConstants;

public class Utilize implements ClientModInitializer {

    private static final ModContainer MOD_CONTAINER = FabricLoader.getInstance().getModContainer("utilize").orElseThrow();

    private static boolean BLOCK_SWING_HAND_ONCE = false;

    @Override
    public void onInitializeClient() {
//        Sees sees = Sees.getInstance();
//        sees.subscribe(new EventListeners());
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

    public static String getUtilizeVersion() {
        return MOD_CONTAINER.getMetadata().getVersion().getFriendlyString();
    }

    public static String getMinecraftVersion() {
        return SharedConstants.getGameVersion().getName();
    }
}
