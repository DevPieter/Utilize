package nl.devpieter.utilize.client.setting.interfaces;

import java.lang.reflect.Type;

public interface IStringSetting extends ISetting<String> {

    @Override
    default Type getType() {
        return String.class;
    }
}
