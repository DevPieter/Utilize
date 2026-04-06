package nl.devpieter.utilize.text.formatter.formatters.format;

import net.minecraft.text.Style;
import nl.devpieter.utilize.text.formatter.ITextFormatter;

public class ObfuscatedFormatter implements ITextFormatter {

    @Override
    public Style applyFormatting(Style parent) {
        return parent.withObfuscated(true);
    }
}
