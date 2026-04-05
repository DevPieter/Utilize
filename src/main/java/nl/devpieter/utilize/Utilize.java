package nl.devpieter.utilize;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.SharedConstants;
import net.minecraft.text.Style;
import nl.devpieter.utilize.http.AsyncRequest;
import nl.devpieter.utilize.setting.SettingManager;
import nl.devpieter.utilize.text.formatter.TextFormatRegistry;
import nl.devpieter.utilize.text.formatter.formats.StyleFormatter;
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
            logger.info("Shutting down Utilize...");

            SettingManager.shutdown();
            AsyncRequest.shutdown();
        });

        // https://minecraft.wiki/w/Formatting_codes
        TextFormatRegistry formatRegistry = TextFormatRegistry.getInstance();
        formatRegistry.register("bold", new StyleFormatter(Style.EMPTY.withBold(true)));
        formatRegistry.register("italic", new StyleFormatter(Style.EMPTY.withItalic(true)));
        formatRegistry.register("underline", new StyleFormatter(Style.EMPTY.withUnderline(true)));
        formatRegistry.register("strikethrough", new StyleFormatter(Style.EMPTY.withStrikethrough(true)));
        formatRegistry.register("obfuscated", new StyleFormatter(Style.EMPTY.withObfuscated(true)));
        formatRegistry.register("black", new StyleFormatter(0x000000));
        formatRegistry.register("dark_blue", new StyleFormatter(0x0000AA));
        formatRegistry.register("dark_green", new StyleFormatter(0x00AA00));
        formatRegistry.register("dark_aqua", new StyleFormatter(0x00AAAA));
        formatRegistry.register("dark_red", new StyleFormatter(0xAA0000));
        formatRegistry.register("dark_purple", new StyleFormatter(0xAA00AA));
        formatRegistry.register("gold", new StyleFormatter(0xFFAA00));
        formatRegistry.register("gray", new StyleFormatter(0xAAAAAA));
        formatRegistry.register("dark_gray", new StyleFormatter(0x555555));
        formatRegistry.register("blue", new StyleFormatter(0x5555FF));
        formatRegistry.register("green", new StyleFormatter(0x55FF55));
        formatRegistry.register("aqua", new StyleFormatter(0x55FFFF));
        formatRegistry.register("red", new StyleFormatter(0xFF5555));
        formatRegistry.register("light_purple", new StyleFormatter(0xFF55FF));
        formatRegistry.register("yellow", new StyleFormatter(0xFFFF55));
        formatRegistry.register("white", new StyleFormatter(0xFFFFFF));
//        formatRegistry.register("minecoin_gold", new StyleFormatter(0xDDD605));
//        formatRegistry.register("material_quartz", new StyleFormatter(0xE3D4D1));
//        formatRegistry.register("material_iron", new StyleFormatter(0xCECACA));
//        formatRegistry.register("material_netherite", new StyleFormatter(0x443A3B));
//        formatRegistry.register("material_redstone", new StyleFormatter(0x971607));
//        formatRegistry.register("material_copper", new StyleFormatter(0xB4684D));
//        formatRegistry.register("material_gold", new StyleFormatter(0xDEB12D));
//        formatRegistry.register("material_emerald", new StyleFormatter(0x47A036));
//        formatRegistry.register("material_diamond", new StyleFormatter(0x2CBAA8));
//        formatRegistry.register("material_lapis", new StyleFormatter(0x21497B));
//        formatRegistry.register("material_amethyst", new StyleFormatter(0x9A5CC6));
//        formatRegistry.register("material_resin", new StyleFormatter(0xEB7114));

        logger.info("Utilize initialized successfully! Version: {}", getUtilizeVersion());
        isInitialized = true;

//        TextCacheHelper textCacheHelper = new TextCacheHelper();
//
//        ClientTickEvents.START_CLIENT_TICK.register(client -> {
//            boolean isInGame = client.player != null && client.world != null;
//            if (!isInGame) return;
//
//            PlayerEntity player = client.player;
//            String sneaking = player.isSneaking() ? "<green>Yes</green>" : "<red>No</red>";
//
////            MutableText sneakingText = textCacheHelper.getFormatted("<bold>Sneaking:</bold> <utilize:highlight>%s</utilize:highlight> testing", Style.EMPTY, sneaking);
//            MutableText sneakingText = textCacheHelper.getFormatted("<bold>Sneaking:</bold> %s testing", Style.EMPTY, sneaking);
//            PlayerUtils.sendMessage(sneakingText, true);
//        });
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
        return isInitialized;
    }

    public String getUtilizeVersion() {
        return modContainer.getMetadata().getVersion().getFriendlyString();
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
                getUtilizeVersion(),
                getMinecraftVersion(),
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
                getUtilizeVersion(),
                getMinecraftVersion(),
                System.getProperty("java.version")
        );
    }
}
