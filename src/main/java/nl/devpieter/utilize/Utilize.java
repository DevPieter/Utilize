package nl.devpieter.utilize;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.SharedConstants;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import nl.devpieter.utilize.listeners.packet.EntityTrackerUpdatePacketListener;
import nl.devpieter.utilize.listeners.packet.OpenScreenPacketListener;
import nl.devpieter.utilize.listeners.packet.SetTradeOffersPacketListener;
import nl.devpieter.utilize.managers.PacketManager;
import nl.devpieter.utilize.utils.ClientUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Utilize implements ClientModInitializer {

    private static final ModContainer MOD_CONTAINER = FabricLoader.getInstance().getModContainer("utilize").orElseThrow();
    public static final Logger LOGGER = LoggerFactory.getLogger("Utilize");

    private static boolean BLOCK_SWING_HAND_ONCE = false;
    private static final List<Integer> BLOCK_SCREEN_IDS = new ArrayList<>();

    @Override
    public void onInitializeClient() {
        PacketManager packetManager = PacketManager.getInstance();
        packetManager.subscribe(new EntityTrackerUpdatePacketListener());
        packetManager.subscribe(new OpenScreenPacketListener());
        packetManager.subscribe(new SetTradeOffersPacketListener());

        if (!ClientUtils.isDevEnv()) return;
        LOGGER.info("Utilize is running in a development environment.");

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

    public static boolean shouldBlockScreenId(int screenId) {
        return BLOCK_SCREEN_IDS.contains(screenId);
    }

    public static void blockScreenId(int screenId) {
        if (BLOCK_SCREEN_IDS.contains(screenId)) return;
        BLOCK_SCREEN_IDS.add(screenId);

        if (!ClientUtils.hasPlayer()) return;
        MinecraftClient client = MinecraftClient.getInstance();
        ClientPlayerEntity player = ClientUtils.getPlayer();

        client.execute(() -> {
            if (player == null || player.currentScreenHandler.syncId != screenId) return;

            player.closeHandledScreen();
            blockedScreenId(screenId);
        });
    }

    public static void blockedScreenId(int screenId) {
        BLOCK_SCREEN_IDS.removeIf(id -> id == screenId);
    }

    public static String getUtilizeVersion() {
        return MOD_CONTAINER.getMetadata().getVersion().getFriendlyString();
    }

    public static String getMinecraftVersion() {
        return SharedConstants.getGameVersion().getName();
    }
}
