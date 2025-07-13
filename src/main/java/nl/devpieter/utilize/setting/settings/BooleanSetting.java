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
        if (this.getValue() == null) throw new IllegalStateException("Cannot toggle a null value. Use setValue() instead.");
        this.setValue(!this.getValue());
    }

    @Override
    public void setTrue() {
        this.setValue(true);
    }

    @Override
    public void setFalse() {
        this.setValue(false);
    }
}
