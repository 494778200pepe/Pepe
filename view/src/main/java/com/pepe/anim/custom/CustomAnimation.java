package com.pepe.anim.custom;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.Transformation;

/**
 * Created by Administrator on 2016/6/2.
 * 3D动画效果
 */
public class CustomAnimation extends Animation {

    private int mCenterWidth;
    private int mCenterHeight;
    private float mRotateY = 30;
    private Camera mCamera = new Camera();

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        // 设置默认时长
        setDuration(2000);
        // 动画结束后保留状态
        setFillAfter(true);
        // 设置默认插值器
        setInterpolator(new BounceInterpolator());
        mCenterWidth = width / 2;
        mCenterHeight = height / 2;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        Matrix matrix = t.getMatrix();
        mCamera.save();
        // 使用Camera设置旋转的角度
        mCamera.rotateY(interpolatedTime * mRotateY);
        // 将旋转变换作用到matrix上
        mCamera.getMatrix(matrix);
        mCamera.restore();
        // 通过pre方法设置矩阵作用前的偏移量来改变旋转中心
        matrix.preTranslate(mCenterWidth, mCenterHeight);
        matrix.postTranslate(-mCenterWidth, -mCenterHeight);
    }

    /**
     * 设置旋转角度
     * @param rotateY 旋转角度
     */
    public void setRotateY(float rotateY) {
        mRotateY = rotateY;
    }

}
