package nl.devpieter.utilize.utils.minecraft;

import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

public final class TextUtils {

    private TextUtils() {
    }

    public static MutableText currentOrDefaultStyle(@NotNull Text text, @NotNull Style defaultStyle) {
        return Text.of("").copy().setStyle(defaultStyle).append(text);
    }

    public static MutableText withStyle(@NotNull String text, @NotNull Style style) {
        return withStyle(Text.of(text), style);
    }

    public static MutableText withStyle(@NotNull Text text, @NotNull Style style) {
        return text.copy().setStyle(style);
    }

    public static MutableText clearStyle(@NotNull String text) {
        return clearStyle(Text.of(text));
    }

    public static MutableText clearStyle(@NotNull Text text) {
        return text.copy().setStyle(Style.EMPTY);
    }
}