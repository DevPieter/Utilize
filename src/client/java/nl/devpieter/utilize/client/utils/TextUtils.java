package nl.devpieter.utilize.client.utils;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import org.jetbrains.annotations.NotNull;

public final class TextUtils {

    private TextUtils() {
    }

    public static MutableComponent currentOrDefaultStyle(@NotNull Component text, @NotNull Style defaultStyle) {
        return Component.literal("").copy().setStyle(defaultStyle).append(text);
    }

    public static MutableComponent withStyle(@NotNull String text, @NotNull Style style) {
        return withStyle(Component.literal(text), style);
    }

    public static MutableComponent withStyle(@NotNull Component text, @NotNull Style style) {
        return text.copy().setStyle(style);
    }

    public static MutableComponent clearStyle(@NotNull String text) {
        return clearStyle(Component.literal(text));
    }

    public static MutableComponent clearStyle(@NotNull Component text) {
        return text.copy().setStyle(Style.EMPTY);
    }
}