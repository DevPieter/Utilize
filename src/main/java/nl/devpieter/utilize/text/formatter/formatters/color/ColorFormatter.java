package nl.devpieter.utilize.text.formatter.formatters.color;

import net.minecraft.text.Style;
import net.minecraft.text.TextColor;
import nl.devpieter.utilize.text.formatter.ITextFormatter;
import org.jetbrains.annotations.NotNull;

public class ColorFormatter implements ITextFormatter {

    private final TextColor color;

    public ColorFormatter(@NotNull TextColor color) {
        this.color = color;
    }

    public ColorFormatter(int rgb) {
        this.color = TextColor.fromRgb(rgb);
    }

    @Override
    public Style applyFormatting(Style parent) {
        return parent.withColor(color);
    }
}
