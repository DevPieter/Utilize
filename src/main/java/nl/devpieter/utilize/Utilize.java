package nl.devpieter.utilize;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.SharedConstants;
import nl.devpieter.utilize.http.AsyncRequest;
import nl.devpieter.utilize.setting.SettingManager;
import nl.devpieter.utilize.text.formatter.TextFormatRegistry;
import nl.devpieter.utilize.text.formatter.formatters.color.ColorFormatter;
import nl.devpieter.utilize.text.formatter.formatters.format.*;
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
        formatRegistry.register("bold", new BoldFormatter());
        formatRegistry.register("italic", new ItalicFormatter());
        formatRegistry.register("underline", new UnderlineFormatter());
        formatRegistry.register("strikethrough", new StrikethroughFormatter());
        formatRegistry.register("obfuscated", new ObfuscatedFormatter());
        formatRegistry.register("black", new ColorFormatter(0x000000));
        formatRegistry.register("dark_blue", new ColorFormatter(0x0000AA));
        formatRegistry.register("dark_green", new ColorFormatter(0x00AA00));
        formatRegistry.register("dark_aqua", new ColorFormatter(0x00AAAA));
        formatRegistry.register("dark_red", new ColorFormatter(0xAA0000));
        formatRegistry.register("dark_purple", new ColorFormatter(0xAA00AA));
        formatRegistry.register("gold", new ColorFormatter(0xFFAA00));
        formatRegistry.register("gray", new ColorFormatter(0xAAAAAA));
        formatRegistry.register("dark_gray", new ColorFormatter(0x555555));
        formatRegistry.register("blue", new ColorFormatter(0x5555FF));
        formatRegistry.register("green", new ColorFormatter(0x55FF55));
        formatRegistry.register("aqua", new ColorFormatter(0x55FFFF));
        formatRegistry.register("red", new ColorFormatter(0xFF5555));
        formatRegistry.register("light_purple", new ColorFormatter(0xFF55FF));
        formatRegistry.register("yellow", new ColorFormatter(0xFFFF55));
        formatRegistry.register("white", new ColorFormatter(0xFFFFFF));
//        formatRegistry.register("minecoin_gold", new ColorFormatter(0xDDD605));
//        formatRegistry.register("material_quartz", new ColorFormatter(0xE3D4D1));
//        formatRegistry.register("material_iron", new ColorFormatter(0xCECACA));
//        formatRegistry.register("material_netherite", new ColorFormatter(0x443A3B));
//        formatRegistry.register("material_redstone", new ColorFormatter(0x971607));
//        formatRegistry.register("material_copper", new ColorFormatter(0xB4684D));
//        formatRegistry.register("material_gold", new ColorFormatter(0xDEB12D));
//        formatRegistry.register("material_emerald", new ColorFormatter(0x47A036));
//        formatRegistry.register("material_diamond", new ColorFormatter(0x2CBAA8));
//        formatRegistry.register("material_lapis", new ColorFormatter(0x21497B));
//        formatRegistry.register("material_amethyst", new ColorFormatter(0x9A5CC6));
//        formatRegistry.register("material_resin", new ColorFormatter(0xEB7114));

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
//            MutableText sneakingText = textCacheHelper.getFormatted("2 <bold>Sneaking: %s</bold> tt0 <bold>t0 <red>t2</red></bold> tt1 <#bda2f2>tt2</#bda2f2>", Style.EMPTY, sneaking);
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
