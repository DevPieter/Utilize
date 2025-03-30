package nl.devpieter.utilize;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.SharedConstants;
import nl.devpieter.sees.Sees;
import nl.devpieter.utilize.test.listeners.EventListeners;
import nl.devpieter.utilize.utils.ClientUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utilize implements ClientModInitializer {

    private static final ModContainer MOD_CONTAINER = FabricLoader.getInstance().getModContainer("utilize").orElseThrow();
    public static final Logger LOGGER = LoggerFactory.getLogger("Utilize");

    private static boolean BLOCK_SWING_HAND_ONCE = false;

    @Override
    public void onInitializeClient() {
        if (!ClientUtils.isDevEnv()) return;
        LOGGER.info("Utilize is running in a development environment.");

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

    public static String getUtilizeVersion() {
        return MOD_CONTAINER.getMetadata().getVersion().getFriendlyString();
    }

    public static String getMinecraftVersion() {
        return SharedConstants.getGameVersion().getName();
    }
}
