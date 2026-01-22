package nl.devpieter.utilize.setting.settings;

import nl.devpieter.utilize.setting.base.SettingBase;
import nl.devpieter.utilize.setting.interfaces.IBooleanSetting;
import org.jetbrains.annotations.NotNull;

public class BooleanSetting extends SettingBase<Boolean> implements IBooleanSetting {

    public BooleanSetting(@NotNull String identifier, Boolean defaultValue) {
        super(identifier, defaultValue);
    }

    public BooleanSetting(@NotNull String identifier, Boolean defaultValue, boolean allowNull) {
        super(identifier, defaultValue, allowNull);
    }

    @Override
    public void toggle() {
        if (getValue() == null) throw new IllegalStateException("Cannot toggle a null value. Use setValue() instead.");
        setValue(!getValue());
    }

    @Override
    public void setTrue() {
        setValue(true);
    }

    @Override
    public void setFalse() {
        setValue(false);
    }
}
