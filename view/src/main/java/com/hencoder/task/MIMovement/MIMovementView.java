package com.hencoder.task.MIMovement;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by wang on 2017/10/17.
 */

public class MIMovementView extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public MIMovementView(Context context) {
        super(context);
    }

    public MIMovementView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MIMovementView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }




}
