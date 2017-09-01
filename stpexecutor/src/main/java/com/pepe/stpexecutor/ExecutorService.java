package com.pepe.stpexecutor;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by wang on 2017/8/29.
 */

public interface ExecutorService {

    /**
     * 有序关闭之前提交的任务，同时不再接受新任务
     * 此方法不等待当前的任务执行完
     * 如果已经关闭，再执行没有额外的效果
     */
    void shutdown();

    /**
     * 停止所有任务，返回等待执行的任务列表
     * 不会等在正在执行的任务中断，如果需要等待，可以调用 awaitTermination 方法
     * 无法保证停止所有任务（如果有些任务不响应中断）
     * 比如：典型的实现，可以通过interrupt中断，但如果响应interrupt失败的话，就可能永远没法中断了
     *
     * @return 等待执行的任务列表
     */
    List<Runnable> shutdownNow();

    /**
     * 是否被终止了
     *
     * @return 是否被终止了
     */
    boolean isShutdown();

    /**
     * 如果所有任务完成关闭，返回true
     * 除非调用了shutdown()或者shutdownNow(),isTerminated()不会为true
     *
     * @return
     */
    boolean isTerminated();

    /**
     * 阻塞，直到三种情况
     * 1、所有任务执行完成
     * 2、超时
     * 3、当前线程被中断
     *
     * @param timeout 超时的最大等待时间
     * @param unit    超时参数的时间单位
     * @return 如果没超时，返回true，如果超时，返回false
     * @throws InterruptedException 如果等待时中断
     */
    boolean awaitTermination(long timeout, TimeUnit unit)
            throws InterruptedException;

    /**
     * 提交一个有返回值的任务去执行
     * 如果你想立即阻塞去得到任务的结果，可以使用格式：
     * result = exec.submit(aCallable).get()
     *
     * @param task
     * @param <T>
     * @return a Future表示任务的待定结果，结果可以通过Future的get()获得
     */
    <T> Future<T> submit(Callable<T> task);


    /**
     * 如果成功执行完成，将返回result
     *
     * @param task
     * @param result
     * @param <T>
     * @return 一个代表任务的期望Future
     */
    <T> Future<T> submit(Runnable task, T result);

    /**
     * 执行完成后，Future的get()将是null
     *
     * @param task
     * @return
     */
    Future<?> submit(Runnable task);


    /**
     * 该方法的结果是不确定的，如果执行过程中，tasks被修改
     * 任务的状态和结果保存在返回的list里面
     * 一个完成的任务可以是正常完成，也有可能是抛出异常
     * 完成后，所有的Future的isDone()为true
     *
     * @param tasks
     * @param <T>
     * @return
     * @throws InterruptedException
     */
    <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks)
            throws InterruptedException;

    /**
     * 跟上面的差不多，加了一个超时的判断
     * 如果超时，正常返回，所有的Future的isDone()为true
     *
     * @param tasks
     * @param timeout
     * @param unit
     * @param <T>
     * @return
     * @throws InterruptedException
     */
    <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks,
                                  long timeout, TimeUnit unit)
            throws InterruptedException;

    /**
     * 执行tasks里面的任务，直到其中一个完成，且无异常。
     * 有一个完成了，或者出现异常，其他的全部取消。
     * @param tasks
     * @param <T>
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     */
    <T> T invokeAny(Collection<? extends Callable<T>> tasks)
            throws InterruptedException, ExecutionException;

    /**
     * 加了一个超时的判断，如果超时，那么抛出异常
     * @param tasks
     * @param timeout
     * @param unit
     * @param <T>
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws TimeoutException
     */
    <T> T invokeAny(Collection<? extends Callable<T>> tasks,
                    long timeout, TimeUnit unit)
            throws InterruptedException, ExecutionException, TimeoutException;
}
