package nl.devpieter.utilize.text.formatter;

import nl.devpieter.utilize.models.UIdentifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TextFormatRegistry {

    private static final TextFormatRegistry INSTANCE = new TextFormatRegistry();

    private final HashMap<UIdentifier, ITextFormatter> formatters = new HashMap<>();

    private Pattern pattern;
    private boolean isDirty = true;

    private TextFormatRegistry() {
    }

    public static TextFormatRegistry getInstance() {
        return INSTANCE;
    }

    public void register(@NotNull String namespace, @NotNull String path, @NotNull ITextFormatter formatter) {
        register(UIdentifier.of(namespace, path), formatter);
    }

    public void register(@NotNull String namespacePath, @NotNull ITextFormatter formatter) {
        register(UIdentifier.of(namespacePath), formatter);
    }

    public void register(@NotNull UIdentifier identifier, @NotNull ITextFormatter formatter) {
        formatters.put(identifier, formatter);
        isDirty = true;
    }

    public @Nullable ITextFormatter getFormatter(@Nullable UIdentifier identifier) {
        if (identifier == null) return null;
        return formatters.get(identifier);
    }

    public @Nullable ITextFormatter getFormatter(String namespacePath) {
        try {
            return getFormatter(UIdentifier.of(namespacePath));
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public Map<UIdentifier, ITextFormatter> getFormatters() {
        return new HashMap<>(formatters);
    }

    public Pattern getPattern() {
        if (!isDirty && pattern != null) return pattern;

        String tagAlternatives = formatters.keySet().stream().map(identifier -> Pattern.quote(identifier.toString())).collect(Collectors.joining("|"));
        pattern = Pattern.compile("<(#[0-9a-fA-F]{3}|#[0-9a-fA-F]{6}|" + tagAlternatives + ")>(.*?)</\\1>", Pattern.DOTALL);

        isDirty = false;
        return pattern;
    }
}
