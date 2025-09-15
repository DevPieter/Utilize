package nl.devpieter.utilize;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.SharedConstants;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import nl.devpieter.utilize.http.AsyncRequest;
import nl.devpieter.utilize.listeners.packet.OpenScreenPacketListener;
import nl.devpieter.utilize.managers.PacketManager;
import nl.devpieter.utilize.setting.SettingManager;
import nl.devpieter.utilize.task.TaskManager;
import nl.devpieter.utilize.utils.minecraft.ClientUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Utilize implements ClientModInitializer {

    private static Utilize INSTANCE;

    private final Logger logger = LoggerFactory.getLogger("Utilize");
    private final ModContainer modContainer = FabricLoader.getInstance().getModContainer("utilize").orElseThrow();

    private final List<Integer> blockScreenIds = new ArrayList<>();

    private boolean isInitialized;

    @Override
    public void onInitializeClient() {
        INSTANCE = this;

        PacketManager packetManager = PacketManager.getInstance();
        packetManager.subscribe(new OpenScreenPacketListener());

        ClientLifecycleEvents.CLIENT_STOPPING.register((client) -> {
            this.logger.info("Shutting down Utilize...");

            TaskManager.shutdown();
            SettingManager.shutdown();
            AsyncRequest.shutdown();
        });

        this.logger.info("Utilize initialized successfully! Version: {}", this.getUtilizeVersion());
        this.isInitialized = true;
    }

    /**
     * Checks if Utilize has been initialized.
     *
     * @return true if the mod instance exists and is initialized, false otherwise
     */
    public static boolean initialized() {
        return INSTANCE != null && INSTANCE.isInitialized;
    }

    /**
     * Returns the singleton instance of Utilize.
     *
     * @return the initialized Utilize instance
     * @throws IllegalStateException if Utilize has not been initialized yet
     */
    public static Utilize getInstance() {
        if (INSTANCE == null || !INSTANCE.isInitialized) {
            throw new IllegalStateException("Utilize has not been initialized yet!");
        }

        return INSTANCE;
    }

    /**
     * Indicates whether this Utilize instance has completed initialization.
     *
     * @return true if initialized, false otherwise
     */
    public boolean isInitialized() {
        return this.isInitialized;
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

    public boolean shouldBlockScreenId(int screenId) {
        return blockScreenIds.contains(screenId);
    }

    public void blockScreenId(int screenId) {
        if (this.blockScreenIds.contains(screenId)) return;
        this.blockScreenIds.add(screenId);

        if (!ClientUtils.hasPlayer()) return;
        MinecraftClient client = MinecraftClient.getInstance();
        ClientPlayerEntity player = ClientUtils.getPlayer();

        client.execute(() -> {
            if (player == null || player.currentScreenHandler.syncId != screenId) return;

            player.closeHandledScreen();
            blockedScreenId(screenId);
        });
    }

    public void blockedScreenId(int screenId) {
        this.blockScreenIds.removeIf(id -> id == screenId);
    }
}
