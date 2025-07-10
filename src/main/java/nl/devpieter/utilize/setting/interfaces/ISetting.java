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
        this.setValue(this.getDefault());
    }

    default boolean isDefaultValueSet() {
        return !this.getValue().equals(this.getDefault());
    }

    default KeyedSetting<T> asKeyedSetting() {
        return new KeyedSetting<>(this.getIdentifier(), this.getValue());
    }
}
