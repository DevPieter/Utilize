package nl.devpieter.utilize.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import nl.devpieter.utilize.Utilize;
import nl.devpieter.utilize.client.setting.SettingManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UtilizeClient implements ClientModInitializer {

    private static UtilizeClient INSTANCE;

    private final Logger logger = LoggerFactory.getLogger("Utilize Client");
    private boolean isInitialized;

    @Override
    public void onInitializeClient() {
        INSTANCE = this;

        ClientLifecycleEvents.CLIENT_STOPPING.register((client) -> {
            logger.info("Shutting down Utilize Client...");

            SettingManager.shutdown();
//            AsyncRequest.shutdown();
        });

        Utilize utilize = Utilize.getInstance();
        logger.info("Utilize Client initialized successfully! Version: {}", utilize.getUtilizeVersion());

        isInitialized = true;
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
    public static UtilizeClient getInstance() {
        if (INSTANCE == null || !INSTANCE.isInitialized) {
            throw new IllegalStateException("Utilize has not been initialized yet!");
        }

        return INSTANCE;
    }
}
