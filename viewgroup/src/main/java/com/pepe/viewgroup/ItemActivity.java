package com.pepe.viewgroup;

/**
 * Created by wang on 2017/7/11.
 */

public class ItemActivity {
    public final Class<?> activityClass;
    public final String title;

    public ItemActivity(Class<?> activityClass, String title) {
        this.activityClass = activityClass;
        this.title = title;
    }

    @Override
    public String toString() {
        return this.title;
    }
}