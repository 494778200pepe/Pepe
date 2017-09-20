package com.pepe.myplayview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.hopechart.musicplayer.util.MusicPlayView;

/**
 * Created by wang on 2017/9/11.
 */

public class DemoSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    // 旋转专辑页面
    private MusicPlayView mPlayView;
    private Context mContext;
    private String mId;
    private String mAlbumArt;

    public DemoSurfaceView(Context context) {
        super(context);
    }

    public DemoSurfaceView(Context context, AttributeSet set) {
        super(context, set);
    }

    public void init(Context context, String id, String albumArt) {
        SurfaceHolder holder = getHolder();
        holder.addCallback(this); //设置Surface生命周期回调
        mPlayView = new MusicPlayView(context);
        mContext = context;
        mId = id;
        mAlbumArt = albumArt;

    }

    public void pause() {
        mPlayView.pause();
    }

    public void play() {
        mPlayView.play();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mPlayView.prepare(mContext, mId, mAlbumArt);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
    }

}