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
        if (this.getValue() == null) return false;
        return this.getValue().contains(value);
    }

    @Override
    public void add(T value) {
        if (this.getValue() == null) throw new IllegalStateException("List value is null, cannot add element.");
        this.getValue().add(value);
    }

    @Override
    public void remove(T value) {
        if (this.getValue() == null) throw new IllegalStateException("List value is null, cannot remove element.");
        this.getValue().remove(value);
    }

    @Override
    public void removeAt(int index) {
        if (this.getValue() == null) throw new IllegalStateException("List value is null, cannot remove element at index.");
        if (index < 0 || index >= this.getValue().size()) throw new IndexOutOfBoundsException("Index out of bounds for list.");

        this.getValue().remove(index);
    }

    @Override
    public void removeFirst() {
        if (this.getValue() == null) throw new IllegalStateException("List value is null, cannot remove first element.");
        if (this.getValue().isEmpty()) throw new IllegalStateException("List is empty, cannot remove first element.");

        this.getValue().removeFirst();
    }

    @Override
    public void removeLast() {
        if (this.getValue() == null) throw new IllegalStateException("List value is null, cannot remove last element.");
        if (this.getValue().isEmpty()) throw new IllegalStateException("List is empty, cannot remove last element.");

        this.getValue().removeLast();
    }

    @Override
    public void clear() {
        if (this.getValue() == null) throw new IllegalStateException("List value is null, cannot clear.");
        this.getValue().clear();
    }

    @Override
    public Type getType() {
        return new TypeToken<List<T>>() {
        }.getType();
    }
}
