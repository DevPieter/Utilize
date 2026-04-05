package nl.devpieter.utilize.internal.models;

import net.minecraft.text.Style;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Objects;

@ApiStatus.Internal
public record TextCacheKey(String key, Style style, Object[] args) {

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof TextCacheKey(String key1, Style style1, Object[] args1))) return false;
        return Objects.equals(key, key1) && Objects.equals(style, style1) && Objects.deepEquals(args, args1);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, style, Arrays.hashCode(args));
    }

    @Override
    public @NotNull String toString() {
        return "TextCacheKey{" +
                "key='" + key + '\'' +
                ", style=" + style +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
