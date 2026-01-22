package nl.devpieter.utilize.setting.interfaces;

import nl.devpieter.utilize.setting.KeyedSetting;

import java.lang.reflect.Type;

public interface ISetting<T> {

    Type getType();

    String getIdentifier();

    boolean shouldAllowNull();

    T getValue();

    T getDefault();

    void setValue(T value);

    default void reset() {
        setValue(getDefault());
    }

    default boolean isDefaultValueSet() {
        T value = getValue();
        T defaultValue = getDefault();

        if (value == null && defaultValue == null) return true;
        if (value == null || defaultValue == null) return false;

        return value.equals(defaultValue);
    }

    default KeyedSetting<T> asKeyedSetting() {
        return new KeyedSetting<>(getIdentifier(), getValue());
    }
}
