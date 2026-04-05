package nl.devpieter.utilize.text.formatter.formats;

import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import nl.devpieter.utilize.text.formatter.ITextFormatter;

public class StyleFormatter implements ITextFormatter {

    private final Style style;

    public StyleFormatter(Style style) {
        this.style = style;
    }

    public StyleFormatter(TextColor color) {
        this(Style.EMPTY.withColor(color));
    }

    public StyleFormatter(int rgbColor) {
        this(Style.EMPTY.withColor(rgbColor));
    }

    @Override
    public MutableText format(String content, Style baseStyle) {
        return Text.literal(content).setStyle(style);
    }
}
