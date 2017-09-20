package com.pepe.tool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by zhangdi on 2016/9/1.
 * 这是一个线程池的工具类，在用到线程的时候可以直接类名加方法名使用
 * android实现图片闪烁动画效果的两种实现方式(实用性高)_Android_脚本之家
 http://www.jb51.net/article/91871.htm
 */
public class ThreadPoolManager {
    /**
     * 线程执行器
     **/
    private static ExecutorService executorService = null;
    /**
     * 固定5个线程
     **/
    private static int nThreads = 5;
    /**
     * 单例
     **/
    private static ThreadPoolManager taskExecutorPool = null;

    /** 初始化线程池 **/
    static {
        taskExecutorPool = new ThreadPoolManager(nThreads * getNumCores());
    }

    /**
     * 构造函数
     **/
    private ThreadPoolManager(int threads) {
//executorService = Executors.newFixedThreadPool(threads);
        executorService = Executors.newScheduledThreadPool(threads);
    }

    /**
     * 取得单例
     *
     * @return
     */
    public static ThreadPoolManager getInstance() {
        return taskExecutorPool;
    }

    /**
     * 取得线程执行器
     *
     * @return
     */
    public ExecutorService getExecutorService() {
        return executorService;
    }

    /**
     * 取得周期性线程执行器
     *
     * @return
     */
    public ScheduledExecutorService getScheduledExcutorService() {
        return (ScheduledExecutorService) executorService;
    }

    /**
     * 获得手机cup个数
     *
     * @return
     */
    public static int getNumCores() {
        int threadCount = Runtime.getRuntime().availableProcessors();
        return threadCount;
    }
}