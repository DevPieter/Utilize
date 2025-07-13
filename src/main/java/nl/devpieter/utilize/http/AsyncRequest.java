package nl.devpieter.utilize.http;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public abstract class AsyncRequest<T> extends RequestHelper {

    private static final ExecutorService EXECUTOR_SERVICE = Executors.newCachedThreadPool();

    private final List<ResultConsumer<T>> callbacks = new ArrayList<>();
    private CompletableFuture<T> future;

    public AsyncRequest() {
        this(null);
    }

    public AsyncRequest(@Nullable ResultConsumer<T> requestCallback) {
        this.callbacks.add(requestCallback);
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

    protected abstract @Nullable T requestAsync() throws Exception;

    public void addCallback(@Nullable ResultConsumer<T> callback) {
        if (callback == null) return;
        this.callbacks.add(callback);
    }

    public void execute() {
        if (this.future != null) return;

        this.future = CompletableFuture.supplyAsync(() -> {
            try {
                T result = requestAsync();
                this.callCallbacks(result, null);
                return result;
            } catch (Exception e) {
                this.callCallbacks(null, e);
                throw new RuntimeException(e);
            }
        }, EXECUTOR_SERVICE);
    }

    public void cancel() {
        this.future.cancel(true);
    }

    public boolean isDone() {
        return this.future.isDone();
    }

    public T get() throws ExecutionException, InterruptedException {
        return this.future.get();
    }

    public CompletableFuture<T> getFuture() {
        return this.future;
    }

    private void callCallbacks(@Nullable T result, @Nullable Exception exception) {
        for (ResultConsumer<T> callback : this.callbacks) {
            if (callback != null) callback.accept(result, exception);
        }
    }
}