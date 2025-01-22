package nl.devpieter.utilize.http;

import org.jetbrains.annotations.Nullable;

public interface ResultConsumer<T> {

    void accept(@Nullable T result, @Nullable Exception exception);
}