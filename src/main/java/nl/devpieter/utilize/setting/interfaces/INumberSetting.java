package nl.devpieter.utilize.setting.interfaces;

public interface INumberSetting<T extends Number> extends ISetting<T> {

    void increment();

    void increment(T amount);

    void decrement();

    void decrement(T amount);
}
