package nl.devpieter.utilize.setting.settings;

import nl.devpieter.utilize.setting.base.SettingBase;
import nl.devpieter.utilize.setting.interfaces.INumberSetting;

import java.lang.reflect.Type;

public class IntSetting extends SettingBase<Integer> implements INumberSetting<Integer> {

    public IntSetting(String identifier, Integer defaultValue) {
        super(identifier, defaultValue);
    }

    public IntSetting(String identifier, Integer defaultValue, boolean allowNull) {
        super(identifier, defaultValue, allowNull);
    }

    @Override
    public void increment() {
        this.setValue(this.getValue() + 1);
    }

    @Override
    public void increment(Integer amount) {
        this.setValue(this.getValue() + amount);
    }

    @Override
    public void decrement() {
        this.setValue(this.getValue() - 1);
    }

    @Override
    public void decrement(Integer amount) {
        this.setValue(this.getValue() - amount);
    }

    @Override
    public Type getType() {
        return Integer.class;
    }
}
