package com.pepe.stpexecutor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    //线程池
    private ScheduledThreadPoolExecutor mThreadPool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getScheduledThreadPoolExecutor().scheduleAtFixedRate(task_time_runnable, 0, 400, TimeUnit.MILLISECONDS);
        getScheduledThreadPoolExecutor().schedule(task_time_runnable, 400, TimeUnit.MILLISECONDS);
    }

    public ScheduledThreadPoolExecutor getScheduledThreadPoolExecutor() {
        if (mThreadPool == null) {
            mThreadPool = new ScheduledThreadPoolExecutor(10);
        }
        return mThreadPool;
    }

    TimerTask task_time_runnable = new TimerTask() { // 界面时间刷新任务
        @Override
        public synchronized void run() {
//            Message msg = Message.obtain(mHandler, HANDLEMESS2);
//            mHandler.sendMessage(msg);
        }
    };
}
