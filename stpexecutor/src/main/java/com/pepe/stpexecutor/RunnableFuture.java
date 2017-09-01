package com.pepe.stpexecutor;

/**
 * Created by wang on 2017/8/29.
 */

public interface RunnableFuture<V> extends Runnable, Future<V> {
    /**
     * Sets this Future to the result of its computation
     * unless it has been cancelled.
     */
    void run();
}
