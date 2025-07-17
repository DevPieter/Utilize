package nl.devpieter.utilize.http;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class AsyncRequest<T> extends RequestHelper {

    private static final ExecutorService EXECUTOR_SERVICE = Executors.newCachedThreadPool();

    private final List<ResultConsumer<T>> callbacks = new ArrayList<>();
    private CompletableFuture<T> future;

    public AsyncRequest() {
        this(null);
    }

    public AsyncRequest(@Nullable ResultConsumer<T> requestCallback) {
        if (requestCallback == null) return;
        this.callbacks.add(requestCallback);
    }

    /**
     * Shuts down the executor service used for asynchronous requests.
     */
    public static void shutdown() {
        EXECUTOR_SERVICE.shutdown();
    }

    protected abstract @Nullable T requestAsync() throws Exception;

    /**
     * Adds a callback to the list of callbacks.
     *
     * @param callback the callback to be added, can be null
     */
    public void addCallback(@Nullable ResultConsumer<T> callback) {
        if (callback == null) return;
        this.callbacks.add(callback);
    }

    /**
     * Executes the asynchronous request.
     * If the request is already in progress, this method does nothing.
     */
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

    /**
     * Cancels the asynchronous request if it is in progress.
     */
    public void cancel() {
        this.future.cancel(true);
    }

    /**
     * Checks if the asynchronous request is done.
     *
     * @return true if the request is done, false otherwise
     */
    public boolean isDone() {
        return this.future.isDone();
    }

    /**
     * Gets the result of the asynchronous request.
     *
     * @return the result of the request
     * @throws ExecutionException   if the computation threw an exception
     * @throws InterruptedException if the current thread was interrupted while waiting
     */
    public T get() throws ExecutionException, InterruptedException {
        return this.future.get();
    }

    /**
     * Gets the CompletableFuture representing the asynchronous request.
     *
     * @return the CompletableFuture representing the request
     */
    public CompletableFuture<T> getFuture() {
        return future;
    }

    private void callCallbacks(@Nullable T result, @Nullable Exception exception) {
        for (ResultConsumer<T> callback : this.callbacks) {
            if (callback != null) callback.accept(result, exception);
        }
    }
}