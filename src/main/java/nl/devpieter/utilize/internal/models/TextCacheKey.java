package nl.devpieter.utilize.internal.models;

import net.minecraft.text.Style;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

@ApiStatus.Internal
public record TextCacheKey(String key, Style style, Object[] args) {

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        TextCacheKey other = (TextCacheKey) obj;
        if (!key.equals(other.key)) return false;
        if (!style.equals(other.style)) return false;
        if (args.length != other.args.length) return false;

        for (int i = 0; i < args.length; i++) {
            if (!args[i].equals(other.args[i])) return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = key.hashCode();

        result = 31 * result + style.hashCode();
        for (Object arg : args) result = 31 * result + arg.hashCode();

        return result;
    }

    @Override
    public @NotNull String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CacheKey{key='").append(key).append('\'');
        sb.append(", style=").append(style);
        sb.append(", args=[");

        for (int i = 0; i < args.length; i++) {
            sb.append(args[i]);
            if (i < args.length - 1) sb.append(", ");
        }

        sb.append("]}");
        return sb.toString();
    }
}
