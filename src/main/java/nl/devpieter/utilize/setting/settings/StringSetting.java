package nl.devpieter.utilize.setting.settings;

import nl.devpieter.utilize.setting.base.SettingBase;
import nl.devpieter.utilize.setting.interfaces.IStringSetting;

public class StringSetting extends SettingBase<String> implements IStringSetting {

    public StringSetting(String identifier, String defaultValue) {
        super(identifier, defaultValue);
    }

    public StringSetting(String identifier, String defaultValue, boolean allowNull) {
        super(identifier, defaultValue, allowNull);
    }
}
