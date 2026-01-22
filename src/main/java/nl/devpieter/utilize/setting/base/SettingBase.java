package nl.devpieter.utilize.setting.base;

import nl.devpieter.utilize.setting.interfaces.ISetting;
import org.jetbrains.annotations.NotNull;

public abstract class SettingBase<T> implements ISetting<T> {

    private final String identifier;
    private final T defaultValue;

    private final boolean allowNull;

    private T value;

    public SettingBase(@NotNull String identifier, T defaultValue) {
        this(identifier, defaultValue, false);
    }

    public SettingBase(@NotNull String identifier, T defaultValue, boolean allowNull) {
        this.identifier = identifier;
        this.defaultValue = defaultValue;
        this.value = defaultValue;
        this.allowNull = allowNull;
    }

    @Override
    public String getIdentifier() {
        return identifier;
    }

    @Override
    public boolean shouldAllowNull() {
        return allowNull;
    }

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public T getDefault() {
        return defaultValue;
    }

    @Override
    public void setValue(T value) {
        if (!allowNull && value == null) {
            throw new IllegalArgumentException("Null value not allowed for setting: " + identifier);
        }

        this.value = value;
    }
}
