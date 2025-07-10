package nl.devpieter.utilize.setting.interfaces;

import java.util.List;

public interface IListSetting<T> extends ISetting<List<T>> {

    boolean contains(T value);

    void add(T value);

    void remove(T value);

    void removeAt(int index);

    void removeFirst();

    void removeLast();

    void clear();
}
