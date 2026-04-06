package nl.devpieter.utilize.text.formatter.formatters.color;

import net.minecraft.text.Style;
import net.minecraft.text.TextColor;
import nl.devpieter.utilize.internal.utils.ColorUtils;
import nl.devpieter.utilize.text.formatter.ITextFormatter;
import org.jetbrains.annotations.NotNull;

public class HexColorFormatter implements ITextFormatter {

    private final TextColor color;

    public HexColorFormatter(@NotNull String hex) {
        this.color = TextColor.fromRgb(ColorUtils.parseHexColor(hex));
    }

    @Override
    public Style applyFormatting(Style parent) {
        return parent.withColor(color);
    }
}
