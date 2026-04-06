package nl.devpieter.utilize.text.helpers;

import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import nl.devpieter.utilize.internal.models.TextCacheKey;
import nl.devpieter.utilize.text.formatter.utils.TextFormatUtils;

import java.util.HashMap;

public class TextCacheHelper {

    private final HashMap<TextCacheKey, MutableText> cache = new HashMap<>();

    public void clear() {
        cache.clear();
    }

    public boolean contains(String key, Style style, Object... args) {
        return cache.containsKey(new TextCacheKey(key, style, args));
    }

    public MutableText getFormatted(String key, Style style, Object... args) {
        TextCacheKey cacheKey = new TextCacheKey(key, style, args);
        return cache.computeIfAbsent(cacheKey, k -> TextFormatUtils.format(k.key(), k.style(), k.args()));
    }
}
