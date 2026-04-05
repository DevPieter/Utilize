package nl.devpieter.utilize.internal.models;

import net.minecraft.util.Identifier;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
public class UIdentifier {

    public static final char NAMESPACE_SEPARATOR = Identifier.NAMESPACE_SEPARATOR;

    public static final String DEFAULT_NAMESPACE = "utilize";
    public static final String VANILLA_NAMESPACE = Identifier.DEFAULT_NAMESPACE;

    private final String namespace;
    private final String path;

    private UIdentifier(String namespace, String path) {
        assert (Identifier.isNamespaceValid(namespace));
        assert (Identifier.isPathValid(path));

        this.namespace = namespace;
        this.path = path;
    }

    public Identifier toVanilla() {
        return Identifier.of(namespace, path);
    }

    public static UIdentifier toUtilize(Identifier identifier) {
        return new UIdentifier(identifier.getNamespace(), identifier.getPath());
    }

    public static UIdentifier ofValidated(String namespace, String path) {
        if (!Identifier.isNamespaceValid(namespace)) throw new IllegalArgumentException("Invalid namespace");
        if (!Identifier.isPathValid(path)) throw new IllegalArgumentException("Invalid path");

        return new UIdentifier(namespace, path);
    }

    public static UIdentifier of(String namespace, String path) {
        return ofValidated(namespace, path);
    }

    public static UIdentifier of(String namespacePath) {
        String[] parts = namespacePath.split(String.valueOf(NAMESPACE_SEPARATOR), 2);
        if (parts.length != 2) throw new IllegalArgumentException("Invalid identifier format");

        return ofValidated(parts[0], parts[1]);
    }

    public static UIdentifier ofVanilla(String path) {
        if (!Identifier.isPathValid(path)) throw new IllegalArgumentException("Invalid path");
        return new UIdentifier(VANILLA_NAMESPACE, path);
    }

    public static UIdentifier ofUtilize(String path) {
        if (!Identifier.isPathValid(path)) throw new IllegalArgumentException("Invalid path");
        return new UIdentifier(DEFAULT_NAMESPACE, path);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        UIdentifier other = (UIdentifier) obj;
        return namespace.equals(other.namespace) && path.equals(other.path);
    }

    @Override
    public int hashCode() {
        return namespace.hashCode() * 31 + path.hashCode();
    }

    @Override
    public String toString() {
        return namespace + NAMESPACE_SEPARATOR + path;
    }
}
