package nl.devpieter.utilize.internal.utils;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

@ApiStatus.Internal
public class ColorUtils {

    public static int parseHexColor(@NotNull String hex) {
        if (hex.startsWith("#")) hex = hex.substring(1);

        if (hex.length() == 3) {
            char r = hex.charAt(0);
            char g = hex.charAt(1);
            char b = hex.charAt(2);
            hex = "" + r + r + g + g + b + b;
        }

        return Integer.parseInt(hex, 16);
    }

    public static boolean isValidHexColor(@NotNull String hex) {
        if (hex.startsWith("#")) hex = hex.substring(1);
        return hex.matches("^[0-9a-fA-F]{3}$") || hex.matches("^[0-9a-fA-F]{6}$");
    }
}
