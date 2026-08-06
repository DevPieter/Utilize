package nl.devpieter.utilize.client.setting.interfaces;

import com.google.common.reflect.TypeToken;
import nl.devpieter.utilize.client.setting.models.PolymorphicValue;

import java.lang.reflect.Type;

public interface IPolymorphicSetting<T> extends ISetting<PolymorphicValue<T>> {

    @Override
    default Type getType() {
        return new TypeToken<PolymorphicValue<T>>() {
        }.getType();
    }
}
