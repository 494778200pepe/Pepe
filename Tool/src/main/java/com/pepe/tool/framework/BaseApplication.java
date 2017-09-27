package com.pepe.tool.framework;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;

import com.bumptech.glide.Glide;
import com.bumptech.glide.MemoryCategory;
import com.zitech.framework.Session;

import java.util.concurrent.ConcurrentHashMap;

public abstract class BaseApplication extends Application {
    private static BaseApplication mInstance;
    private Session session;
    private ConcurrentHashMap<Object, Object> mQuickCache;
    private SharedPreferences persistentCache;
    private Handler mainThreadHandler;

    public static BaseApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Glide.get(this).setMemoryCategory(MemoryCategory.LOW);
        mInstance = this;
        session = new Session(this);
        mQuickCache = new ConcurrentHashMap<Object, Object>();
        persistentCache = getSharedPreferences("persistent_cache", MODE_PRIVATE);
        mainThreadHandler = new Handler(Looper.getMainLooper());

    }

    /**
     * 缓存
     *
     * @param key
     * @param value
     */
    public void quickCache(Object key, Object value) {
        mQuickCache.put(key, value);
    }

    public void persistentlyCache(String key, String value) {
        persistentCache.edit().putString(key, value).commit();
    }

    public String getPersistentCache(String key) {
        return persistentCache.getString(key, null);
    }

    /**
     * 弹出
     *
     * @param key
     * @return
     */
    public Object popCache(Object key) {
        return mQuickCache.remove(key);
    }

    /**
     * @param key
     * @return
     */
    public Object getCache(Object key) {
        return mQuickCache.get(key);
    }

    public Session getSession() {
        return session;
    }

    public void clearQuickCache() {
        mQuickCache.clear();
    }

    public void post(Runnable r) {
        mainThreadHandler.post(r);
    }

    public void postDelay(Runnable r, int delayMillis) {
        mainThreadHandler.postDelayed(r, delayMillis);
    }
}
