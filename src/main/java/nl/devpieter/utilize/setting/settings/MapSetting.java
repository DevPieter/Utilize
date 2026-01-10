package nl.devpieter.utilize.setting.settings;

import com.google.common.reflect.TypeToken;
import nl.devpieter.utilize.setting.base.SettingBase;
import nl.devpieter.utilize.setting.interfaces.IMapSetting;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Type;
import java.util.Map;

public class MapSetting<T, V> extends SettingBase<Map<T, V>> implements IMapSetting<T, V> {

    public MapSetting(@NotNull String identifier, Map<T, V> defaultValue) {
        super(identifier, defaultValue);
    }

    public MapSetting(@NotNull String identifier, Map<T, V> defaultValue, boolean allowNull) {
        super(identifier, defaultValue, allowNull);
    }

    @Override
    public boolean containsKey(T key) {
        if (this.getValue() == null) return false;
        return this.getValue().containsKey(key);
    }

    @Override
    public boolean containsValue(V value) {
        if (this.getValue() == null) return false;
        return this.getValue().containsValue(value);
    }

    @Override
    public V get(T key) {
        if (this.getValue() == null) return null;
        return this.getValue().get(key);
    }

    @Override
    public void put(T key, V value) {
        if (this.getValue() == null) throw new IllegalStateException("Map value is null, cannot put element.");
        this.getValue().put(key, value);
    }

    @Override
    public void remove(T key) {
        if (this.getValue() == null) throw new IllegalStateException("Map value is null, cannot remove element.");
        this.getValue().remove(key);
    }

    @Override
    public void clear() {
        if (this.getValue() == null) throw new IllegalStateException("Map value is null, cannot clear elements.");
        this.getValue().clear();
    }

    @Override
    public Type getType() {
        return new TypeToken<Map<T, V>>() {
        }.getType();
    }
}
