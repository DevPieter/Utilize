package nl.devpieter.utilize.setting.settings;

import nl.devpieter.utilize.setting.base.SettingBase;
import nl.devpieter.utilize.setting.interfaces.INumberSetting;

import java.lang.reflect.Type;

public class FloatSetting extends SettingBase<Float> implements INumberSetting<Float> {

    public FloatSetting(String identifier, Float defaultValue) {
        super(identifier, defaultValue);
    }

    public FloatSetting(String identifier, Float defaultValue, boolean allowNull) {
        super(identifier, defaultValue, allowNull);
    }

    @Override
    public void increment() {
        this.setValue(this.getValue() + 1.0F);
    }

    @Override
    public void increment(Float amount) {
        this.setValue(this.getValue() + amount);
    }

    @Override
    public void decrement() {
        this.setValue(this.getValue() - 1.0F);
    }

    @Override
    public void decrement(Float amount) {
        this.setValue(this.getValue() - amount);
    }

    @Override
    public Type getType() {
        return Float.class;
    }
}
