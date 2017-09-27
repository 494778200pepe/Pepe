package com.pepe.tool.framework.data.network.subscribe;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;

import rx.Subscriber;

/**
 * Created by hsj on 2016/7/28.
 */
public abstract class ZProgressSubscriber <T> extends Subscriber<T> {

    private Dialog mDialog;
    private Context context;

    public ZProgressSubscriber(Context context){
        this.context = context;
        mDialog = new ProgressDialog(context);
    }

    @Override
    public void onCompleted() {
        onZCompleted();
    }

    @Override
    public void onStart() {
        mDialog.show();
    }

    @Override
    public void onError(Throwable e) {
        onZError(e);
        mDialog.dismiss();
    }

    @Override
    public void onNext(T t) {
        onZNext(t);
        mDialog.dismiss();
    }

   public abstract void onZNext(T t);

    public  abstract void onZCompleted();

    public abstract void onZError(Throwable e);
}
