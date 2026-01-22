package nl.devpieter.utilize.http;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public abstract class AsyncRequest<T> extends RequestHelper {

    private static final ExecutorService EXECUTOR_SERVICE = Executors.newCachedThreadPool();

    private final List<ResultConsumer<T>> callbacks = new ArrayList<>();
    private CompletableFuture<T> future;

    protected abstract @Nullable T requestAsync() throws Exception;

    public AsyncRequest() {
        this(null);
    }

    public AsyncRequest(@Nullable ResultConsumer<T> requestCallback) {
        if (requestCallback == null) return;
        callbacks.add(requestCallback);
    }

    public static void shutdown() {
        EXECUTOR_SERVICE.shutdown();

        try {
            if (EXECUTOR_SERVICE.awaitTermination(5, TimeUnit.SECONDS)) return;
            EXECUTOR_SERVICE.shutdownNow();
        } catch (InterruptedException e) {
            EXECUTOR_SERVICE.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    public void addCallback(@Nullable ResultConsumer<T> callback) {
        if (callback == null) return;
        callbacks.add(callback);
    }

    public void execute() {
        if (future != null) return;

        future = CompletableFuture.supplyAsync(() -> {
            try {
                T result = requestAsync();
                callCallbacks(result, null);
                return result;
            } catch (Exception e) {
                callCallbacks(null, e);
                throw new RuntimeException(e);
            }
        }, EXECUTOR_SERVICE);
    }

    public void cancel() {
        future.cancel(true);
    }

    public boolean isDone() {
        return future.isDone();
    }

    public T get() throws ExecutionException, InterruptedException {
        return future.get();
    }

    public CompletableFuture<T> getFuture() {
        return future;
    }

    private void callCallbacks(@Nullable T result, @Nullable Exception exception) {
        for (ResultConsumer<T> callback : callbacks) {
            if (callback != null) callback.accept(result, exception);
        }
    }
}