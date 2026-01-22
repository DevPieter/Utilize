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
        if (getValue() == null) return false;
        return getValue().contains(value);
    }

    @Override
    public void add(T value) {
        if (getValue() == null) throw new IllegalStateException("List value is null, cannot add element.");
        getValue().add(value);
    }

    @Override
    public void remove(T value) {
        if (getValue() == null) throw new IllegalStateException("List value is null, cannot remove element.");
        getValue().remove(value);
    }

    @Override
    public void removeAt(int index) {
        if (getValue() == null) throw new IllegalStateException("List value is null, cannot remove element at index.");
        if (index < 0 || index >= getValue().size()) throw new IndexOutOfBoundsException("Index out of bounds for list.");

        getValue().remove(index);
    }

    @Override
    public void removeFirst() {
        if (getValue() == null) throw new IllegalStateException("List value is null, cannot remove first element.");
        if (getValue().isEmpty()) throw new IllegalStateException("List is empty, cannot remove first element.");

        getValue().removeFirst();
    }

    @Override
    public void removeLast() {
        if (getValue() == null) throw new IllegalStateException("List value is null, cannot remove last element.");
        if (getValue().isEmpty()) throw new IllegalStateException("List is empty, cannot remove last element.");

        getValue().removeLast();
    }

    @Override
    public void clear() {
        if (getValue() == null) throw new IllegalStateException("List value is null, cannot clear.");
        getValue().clear();
    }

    @Override
    public Type getType() {
        return new TypeToken<List<T>>() {
        }.getType();
    }
}
