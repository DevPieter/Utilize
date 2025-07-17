package nl.devpieter.utilize.setting.settings;

import nl.devpieter.utilize.setting.base.SettingBase;
import nl.devpieter.utilize.setting.interfaces.INumberSetting;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Type;

public class FloatSetting extends SettingBase<Float> implements INumberSetting<Float> {

    public FloatSetting(@NotNull String identifier, Float defaultValue) {
        super(identifier, defaultValue);
    }

    public FloatSetting(@NotNull String identifier, Float defaultValue, boolean allowNull) {
        super(identifier, defaultValue, allowNull);
    }

    @Override
    public void increment() {
        if (this.getValue() == null) throw new IllegalStateException("Cannot increment a null value. Use setValue() instead.");
        this.setValue(this.getValue() + 1.0F);
    }

    @Override
    public void increment(@Nullable Float amount) {
        if (amount == null && !this.shouldAllowNull()) throw new IllegalArgumentException("Amount cannot be null");

        if (amount == null) this.setValue(0.0F);
        else if (this.getValue() == null) this.setValue(amount);
        else this.setValue(this.getValue() + amount);
    }

    @Override
    public void decrement() {
        if (this.getValue() == null) throw new IllegalStateException("Cannot decrement a null value. Use setValue() instead.");
        this.setValue(this.getValue() - 1.0F);
    }

    @Override
    public void decrement(@Nullable Float amount) {
        if (amount == null && !this.shouldAllowNull()) throw new IllegalArgumentException("Amount cannot be null");

        if (amount == null) this.setValue(0.0F);
        else if (this.getValue() == null) this.setValue(-amount);
        else this.setValue(this.getValue() - amount);
    }

    @Override
    public Type getType() {
        return Float.class;
    }
}
