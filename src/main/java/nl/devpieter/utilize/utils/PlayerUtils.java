package nl.devpieter.utilize.utils;

import net.minecraft.text.Text;

public class PlayerUtils {

    public static void sendMessage(Text message, boolean overlay) {
        if (!ClientUtils.hasPlayer()) return;
        ClientUtils.getPlayer().sendMessage(message, overlay);
    }
}
