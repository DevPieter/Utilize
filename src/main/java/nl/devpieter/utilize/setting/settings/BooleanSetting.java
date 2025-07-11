package nl.devpieter.utilize.setting.settings;

import nl.devpieter.utilize.setting.base.SettingBase;
import nl.devpieter.utilize.setting.interfaces.IBooleanSetting;

public class BooleanSetting extends SettingBase<Boolean> implements IBooleanSetting {

    public BooleanSetting(String identifier, Boolean defaultValue) {
        super(identifier, defaultValue);
    }

    public BooleanSetting(String identifier, Boolean defaultValue, boolean allowNull) {
        super(identifier, defaultValue, allowNull);
    }

    @Override
    public void toggle() {
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
