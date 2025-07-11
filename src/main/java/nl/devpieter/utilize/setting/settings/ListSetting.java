package nl.devpieter.utilize.setting.settings;

import com.google.common.reflect.TypeToken;
import nl.devpieter.utilize.setting.base.SettingBase;
import nl.devpieter.utilize.setting.interfaces.IListSetting;

import java.lang.reflect.Type;
import java.util.List;

public class ListSetting<T> extends SettingBase<List<T>> implements IListSetting<T> {

    public ListSetting(String identifier, List<T> defaultValue) {
        super(identifier, defaultValue);
    }

    public ListSetting(String identifier, List<T> defaultValue, boolean allowNull) {
        super(identifier, defaultValue, allowNull);
    }

    @Override
    public boolean contains(T value) {
        return this.getValue().contains(value);
    }

    @Override
    public void add(T value) {
        this.getValue().add(value);
    }

    @Override
    public void remove(T value) {
        this.getValue().remove(value);
    }

    @Override
    public void removeAt(int index) {
        this.getValue().remove(index);
    }

    @Override
    public void removeFirst() {
        this.getValue().removeFirst();
    }

    @Override
    public void removeLast() {
        this.getValue().removeLast();
    }

    @Override
    public void clear() {
        this.getValue().clear();
    }

    @Override
    public Type getType() {
        return new TypeToken<List<T>>() {
        }.getType();
    }
}
