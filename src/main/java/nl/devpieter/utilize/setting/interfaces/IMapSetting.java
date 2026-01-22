package nl.devpieter.utilize.setting.interfaces;

import java.util.Map;

public interface IMapSetting<T, V> extends ISetting<Map<T, V>> {

    boolean containsKey(T key);

    boolean containsValue(V value);

    V get(T key);

    void put(T key, V value);

    void remove(T key);

    void clear();
}
