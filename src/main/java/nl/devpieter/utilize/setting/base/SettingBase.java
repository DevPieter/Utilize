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
        return this.identifier;
    }

    @Override
    public boolean shouldAllowNull() {
        return this.allowNull;
    }

    @Override
    public T getValue() {
        return this.value;
    }

    @Override
    public T getDefault() {
        return this.defaultValue;
    }

    @Override
    public void setValue(T value) {
        this.value = value;
    }
}
