package nl.devpieter.utilize.text.formatter;

import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import nl.devpieter.utilize.internal.utils.ColorUtils;
import nl.devpieter.utilize.text.formatter.formats.HexColorFormatter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextFormatUtils {

    private static final TextFormatRegistry REGISTRY = TextFormatRegistry.getInstance();

    private TextFormatUtils() {
    }

    public static MutableText format(String key, Style style, Object... args) {
        String raw = Text.translatable(key, args).getString();

        Pattern pattern = REGISTRY.getPattern();
        MutableText result = Text.empty();

        int lastIndex = 0;
        Matcher matcher = pattern.matcher(raw);

        while (matcher.find()) {
            if (matcher.start() < lastIndex) continue;

            if (matcher.start() > lastIndex) {
                result.append(Text.literal(raw.substring(lastIndex, matcher.start())).setStyle(style));
            }

            String tag = matcher.group(1);
            String content = matcher.group(2);

            ITextFormatter formatter = REGISTRY.getFormatter(tag);

            if (formatter == null
                    && tag.startsWith("#")
                    && ColorUtils.isValidHexColor(tag)
            ) formatter = new HexColorFormatter(tag);

            if (formatter != null) result.append(formatter.format(content, style));
            else result.append(Text.literal(matcher.group(0)).setStyle(style));

            lastIndex = matcher.end();
        }

        if (lastIndex < raw.length()) result.append(Text.literal(raw.substring(lastIndex)).setStyle(style));
        return result;
    }
}
