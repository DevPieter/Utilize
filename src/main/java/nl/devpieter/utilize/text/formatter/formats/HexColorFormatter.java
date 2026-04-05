package nl.devpieter.utilize.text.formatter.formats;

import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import nl.devpieter.utilize.text.formatter.ITextFormatter;
import nl.devpieter.utilize.internal.utils.ColorUtils;
import org.jetbrains.annotations.NotNull;

public class HexColorFormatter implements ITextFormatter {

    private final TextColor color;

    public HexColorFormatter(@NotNull String hex) {
        this.color = TextColor.fromRgb(ColorUtils.parseHexColor(hex));
    }

    @Override
    public MutableText format(String content, Style baseStyle) {
        return Text.literal(content).setStyle(baseStyle.withColor(color));
    }
}
