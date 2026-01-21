package nl.devpieter.utilize;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.SharedConstants;
import nl.devpieter.utilize.http.AsyncRequest;
import nl.devpieter.utilize.setting.SettingManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utilize implements ClientModInitializer {

    private static Utilize INSTANCE;

    private final Logger logger = LoggerFactory.getLogger("Utilize");
    private final ModContainer modContainer = FabricLoader.getInstance().getModContainer("utilize").orElseThrow();

    private boolean isInitialized;

    @Override
    public void onInitializeClient() {
        INSTANCE = this;

        ClientLifecycleEvents.CLIENT_STOPPING.register((client) -> {
            this.logger.info("Shutting down Utilize...");

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
        return SharedConstants.getGameVersion().name();
    }

    /**
     * Constructs a default User-Agent string for Utilize.
     * The format is: "Utilize/{utilizeVersion} (Mc/{minecraftVersion}; Java/{javaVersion})"
     *
     * @return the User-Agent string containing Utilize, Minecraft, and Java versions
     */
    public String getUserAgent() {
        return String.format(
                "Utilize/%s (Mc/%s; Java/%s)",
                this.getUtilizeVersion(),
                this.getMinecraftVersion(),
                System.getProperty("java.version")
        );
    }

    /**
     * Constructs a custom User-Agent string with the provided name and version.
     * The format is: "{name}/{version} (Utilize/{utilizeVersion}; Mc/{minecraftVersion}; Java/{javaVersion})"
     *
     * @param name    the name to include in the User-Agent
     * @param version the version to include in the User-Agent
     * @return the User-Agent string containing the custom name/version, Utilize, Minecraft, and Java versions
     */
    public String getUserAgent(String name, String version) {
        return String.format(
                "%s/%s (Utilize/%s; Mc/%s; Java/%s)",
                name,
                version,
                this.getUtilizeVersion(),
                this.getMinecraftVersion(),
                System.getProperty("java.version")
        );
    }
}
