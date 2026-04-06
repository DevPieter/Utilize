package nl.devpieter.utilize.text.formatter.formatters.format;

import net.minecraft.text.Style;
import nl.devpieter.utilize.text.formatter.ITextFormatter;

public class BoldFormatter implements ITextFormatter {

    @Override
    public Style applyFormatting(Style parent) {
        return parent.withBold(true);
    }
}
