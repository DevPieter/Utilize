package nl.devpieter.utilize;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.SharedConstants;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import nl.devpieter.utilize.http.AsyncRequest;
import nl.devpieter.utilize.listeners.packet.EntityTrackerUpdatePacketListener;
import nl.devpieter.utilize.listeners.packet.OpenScreenPacketListener;
import nl.devpieter.utilize.listeners.packet.SetTradeOffersPacketListener;
import nl.devpieter.utilize.managers.PacketManager;
import nl.devpieter.utilize.task.TaskManager;
import nl.devpieter.utilize.utils.ClientUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Utilize implements ClientModInitializer {

    private static Utilize INSTANCE;

    private final Logger logger = LoggerFactory.getLogger("Utilize");
    private final ModContainer modContainer = FabricLoader.getInstance().getModContainer("utilize").orElseThrow();

    private static final List<Integer> BLOCK_SCREEN_IDS = new ArrayList<>();

    @Override
    public void onInitializeClient() {
        INSTANCE = this;

        PacketManager packetManager = PacketManager.getInstance();
        packetManager.subscribe(new EntityTrackerUpdatePacketListener());
        packetManager.subscribe(new OpenScreenPacketListener());
        packetManager.subscribe(new SetTradeOffersPacketListener());

        ClientLifecycleEvents.CLIENT_STOPPING.register((client) -> {
            this.logger.info("Shutting down Utilize...");

            TaskManager.shutdown();
            AsyncRequest.shutdown();
        });

        this.logger.info("Utilize initialized successfully! Version: {}", this.getUtilizeVersion());
    }

    public static Utilize getInstance() {
        return INSTANCE;
    }

    public Logger getLogger() {
        return this.logger;
    }

    public String getUtilizeVersion() {
        return this.modContainer.getMetadata().getVersion().getFriendlyString();
    }

    public String getMinecraftVersion() {
        return SharedConstants.getGameVersion().getName();
    }

    public String getUserAgent() {
        return String.format("Utilize/%s (%s;)", this.getUtilizeVersion(), this.getMinecraftVersion());
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
}
