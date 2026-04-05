package nl.devpieter.utilize.models;

import net.minecraft.util.Identifier;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

@ApiStatus.Internal
public class UIdentifier {

    public static final char NAMESPACE_SEPARATOR = Identifier.NAMESPACE_SEPARATOR;

    public static final String DEFAULT_NAMESPACE = "utilize";
    public static final String VANILLA_NAMESPACE = Identifier.DEFAULT_NAMESPACE;

    private final @Nullable String namespace;
    private final @NotNull String path;

    private UIdentifier(@Nullable String namespace, @NotNull String path) {
        assert namespace == null || Identifier.isNamespaceValid(namespace);
        assert Identifier.isPathValid(path);

        this.namespace = namespace;
        this.path = path;
    }

    public Identifier toVanilla() {
        if (namespace == null) return Identifier.of(DEFAULT_NAMESPACE, path);
        return Identifier.of(namespace, path);
    }

    public static UIdentifier toUtilize(Identifier identifier) {
        return new UIdentifier(identifier.getNamespace(), identifier.getPath());
    }

    public static UIdentifier ofValidated(@Nullable String namespace, @NotNull String path) {
        if (namespace != null && !Identifier.isNamespaceValid(namespace)) throw new IllegalArgumentException("Invalid namespace");
        if (!Identifier.isPathValid(path)) throw new IllegalArgumentException("Invalid path");

        return new UIdentifier(namespace, path);
    }

    public static UIdentifier of(@Nullable String namespace, @NotNull String path) {
        return ofValidated(namespace, path);
    }

    public static UIdentifier of(String namespacePath) {
        String[] parts = namespacePath.split(String.valueOf(NAMESPACE_SEPARATOR), 2);

        if (parts.length == 1) return ofNoNamespace(parts[0]);
        else return of(parts[0], parts[1]);
    }

    public static UIdentifier ofVanilla(String path) {
        if (!Identifier.isPathValid(path)) throw new IllegalArgumentException("Invalid path");
        return new UIdentifier(VANILLA_NAMESPACE, path);
    }

    public static UIdentifier ofUtilize(String path) {
        if (!Identifier.isPathValid(path)) throw new IllegalArgumentException("Invalid path");
        return new UIdentifier(DEFAULT_NAMESPACE, path);
    }

    public static UIdentifier ofNoNamespace(String path) {
        if (!Identifier.isPathValid(path)) throw new IllegalArgumentException("Invalid path");
        return new UIdentifier(null, path);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof UIdentifier that)) return false;
        return Objects.equals(namespace, that.namespace) && Objects.equals(path, that.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(namespace, path);
    }

    @Override
    public String toString() {
        return (namespace != null ? namespace + NAMESPACE_SEPARATOR : "") + path;
    }
}
