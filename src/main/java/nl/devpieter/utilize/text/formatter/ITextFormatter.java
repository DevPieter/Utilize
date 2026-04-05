package nl.devpieter.utilize.text.formatter;

import net.minecraft.text.MutableText;
import net.minecraft.text.Style;

public interface ITextFormatter {

    MutableText format(String content, Style baseStyle);
}
