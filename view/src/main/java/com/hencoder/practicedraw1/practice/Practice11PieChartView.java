package com.hencoder.practicedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Jucongyuan/PracticeDraw1: 《HenCoder Android 开发进阶：UI 1-1 绘制基础》 的练习项目
 * https://github.com/Jucongyuan/PracticeDraw1
 */

public class Practice11PieChartView extends View {

    public Practice11PieChartView(Context context) {
        super(context);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        String[] names = {"Lollipop", "Marshmallow", "Froyo", "Gingerbread", "Ice Cream Sandwich", "Jelly Bean", "Kitkat"};
        int[] values = {120, 60, 1, 6, 5, 48, 120};
//        int len = names.length - 1;
//        int[] values = {118, 59, 1, 6, 5, 47, 119};
        int[] colors = {Color.RED, Color.YELLOW, Color.DKGRAY, Color.MAGENTA, Color.GRAY, Color.GREEN, Color.BLUE};
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        int radius = 200;
        RectF rect = new RectF(70, 70, 70 + radius * 2, 70 + radius * 2);
        float originAngle = -180;
        float space = 2f;
        float startAngle, sweepAngle;
        startAngle = originAngle;
        boolean tag = false;
        boolean translateTag = false;
        int offset = 20;
        int lineOffset = 20;
        int value = 0;

        for (int i = 0; i < values.length; i++) {
            if (tag) {
                startAngle = startAngle + space;
            }
            sweepAngle = values[i] - space;
            if (sweepAngle < 1) {
                sweepAngle = values[i];
                tag = true;
            } else {
                tag = false;
            }
            int centerX = 70 + radius;
            int centerY = 70 + radius;

            if (i == 0) {
                value += values[i];
                float translateX = getTranslateX(offset, 0, value);
                float translateY = getTranslateY(offset, 0, value);
//                Log.d("pepe", "sweepAngle / 2 = " +value / 2);
//                Log.d("pepe", "Math.cos(sweepAngle / 2 = " + Math.cos(Math.PI / (180 / (value / 2))));
//                Log.d("pepe", "Math.sin(sweepAngle / 2 = " + Math.sin(Math.PI / (180 / (value / 2))));
                Log.d("pepe", "translateX = " + translateX);
                Log.d("pepe", "translateY = " + translateY);
                canvas.translate(translateX, translateY);
                translateTag = true;


                float translateX1 = getTranslateX(radius, 0, value);
                float translateY1 = getTranslateY(radius, 0, value);

                float translateX2 = getTranslateX(radius + lineOffset, 0, value);
                float translateY2 = getTranslateY(radius + lineOffset, 0, value);
                paint.setColor(Color.LTGRAY);
                canvas.drawLine(centerX + translateX1, centerY + translateY1, centerX + translateX2, centerY + translateY2, paint);
            } else {
                translateTag = false;

                float translateX1 = getTranslateX(radius, value, values[i]);
                float translateY1 = getTranslateY(radius, value, values[i]);
                Log.d("pepe", "translateX1 = " + translateX1);
                Log.d("pepe", "translateY1 = " + translateY1);

                float translateX2 = getTranslateX(radius + lineOffset, value, values[i]);
                float translateY2 = getTranslateY(radius + lineOffset, value, values[i]);
                Log.d("pepe", "translateX2 = " + translateX2);
                Log.d("pepe", "translateY2 = " + translateY2);
                paint.setColor(Color.LTGRAY);
                canvas.drawLine(centerX + translateX1, centerY + translateY1, centerX + translateX2, centerY + translateY2, paint);
                value += values[i];
            }

            paint.setColor(colors[i]);
            canvas.drawArc(rect, startAngle, sweepAngle, true, paint);
            startAngle = startAngle + values[i];
            if (translateTag) {
                canvas.restore();
            }
        }
//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画饼图
    }

    private float getTranslateX(int radius, int startAngle, int sweetAngle) {
        Log.d("pepe", "===> getTranslateX   startAngle = " + startAngle + "    sweetAngle = " + sweetAngle);
        int angle = startAngle + sweetAngle / 2;
        if (angle < 90) {
            //-
            if (angle > 2) {
                return -(float) (radius * Math.cos(Math.PI / (180 / angle)));
            } else {
                return 0;
            }
        } else if (angle == 90) {
            return 0;
        } else if (angle < 180) {
            //+
            angle = angle % 90;
            if (angle > 2) {
                return (float) (radius * Math.sin(Math.PI / (180 / angle)));
            } else {
                return 0;
            }
        } else if (angle == 180) {
            return radius;
        } else if (angle < 270) {
            //+
            angle = angle % 90;
            if (angle > 2) {
                return (float) (radius * Math.cos(Math.PI / (180 / angle)));
            } else {
                return 0;
            }
        } else if (angle == 270) {
            return 0;
        } else if (angle < 360) {
            //-
            angle = angle % 90;
            if (angle > 2) {
                return -(float) (radius * Math.sin(Math.PI / (180 / angle)));
            } else {
                return 0;
            }
        } else if (angle == 360) {
            return 0 - radius;
        }
        return 0;
    }

    private float getTranslateY(int radius, int startAngle, int sweetAngle) {
        Log.d("pepe", "===> getTranslateY   startAngle = " + startAngle + "    sweetAngle = " + sweetAngle);
        int angle = startAngle + sweetAngle / 2;
        if (angle < 90) {
            //-
            if (angle > 2) {
                return -(float) (radius * Math.sin(Math.PI / (180 / angle)));
            } else {
                return 0;
            }
        } else if (angle == 90) {
            return -radius;
        } else if (angle < 180) {
            //-
            angle = angle % 90;
            if (angle > 2) {
                return -(float) (radius * Math.cos(Math.PI / (180 / angle)));
            } else {
                return 0;
            }
        } else if (angle == 180) {
            return 0;
        } else if (angle < 270) {
            //+
            angle = angle % 90;
            if (angle > 2) {
                return (float) (radius * Math.sin(Math.PI / (180 / angle)));
            } else {
                return 0;
            }
        } else if (angle == 270) {
            return radius;
        } else if (angle < 360) {
            //+
            angle = angle % 90;
            if (angle > 2) {
                return (float) (radius * Math.cos(Math.PI / (180 / angle)));
            } else {
                return 0;
            }
        } else if (angle == 360) {
            return 0;
        }
        return 0;
    }


}
