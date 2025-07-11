package nl.devpieter.utilize.setting.interfaces;

import java.lang.reflect.Type;

public interface IStringSetting extends ISetting<String> {

    @Override
    default Type getType() {
        return String.class;
    }
}
