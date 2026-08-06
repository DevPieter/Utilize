package nl.devpieter.utilize.client.utils;

import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

public final class PlayerUtils {

    private PlayerUtils() {
    }

    public static void sendMessage(@NotNull Component message, boolean overlay) {
        if (overlay) sendOverlayMessage(message);
        else sendSystemMessage(message);
    }

    public static void sendOverlayMessage(@NotNull Component message) {
        if (ClientUtils.hasPlayer()) ClientUtils.getPlayer().sendOverlayMessage(message);
    }

    public static void sendSystemMessage(@NotNull Component message) {
        if (ClientUtils.hasPlayer()) ClientUtils.getPlayer().sendSystemMessage(message);
    }
}
