package nl.devpieter.utilize.setting.interfaces;

import com.google.common.reflect.TypeToken;
import nl.devpieter.utilize.models.PolymorphicValue;

import java.lang.reflect.Type;

public interface IPolymorphicSetting<T> extends ISetting<PolymorphicValue<T>> {

    @Override
    default Type getType() {
        return new TypeToken<PolymorphicValue<T>>() {
        }.getType();
    }
}
