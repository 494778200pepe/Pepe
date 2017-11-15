package com.pepe.view.canvas;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.Shader;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.pepe.view.R;

public class CanvasView extends View {

    private Paint mPaint;// 画笔
    private DrawMode drawMode = DrawMode.UNKNOWN;// 绘制模式
    private float density = getResources().getDisplayMetrics().density;

    private int canvasWidth;// 画板的宽度
    private int canvasHeight;// 画板的高度

    private Bitmap bitmap;
    public static String[] CONTENTS = {"null", "坐标系", "argb", "point", "points", "line", "圆", "椭圆", "圆弧", "矩形", "path", "text", "图片", "钟表", "旋转", "缩放", "错切", "保存恢复", "区域"};

    private static enum DrawMode {
        UNKNOWN(0), AXIS(1), ARGB(2), POINT(3), POINT2(4), LINE(5), CIRCLE(6), OVAL(
                7), ARC(8), RECT(9), PATH(10), TEXT(11), BITMAP(12), DEMO1(13), ROTATE(
                14), SCALE(15), SKEW(16), SAVEANDRESTORE(17), REGION(18);

        DrawMode(int value) {
        }

        public static DrawMode valueOf(int value) {
            switch (value) {
                case 0:
                    return UNKNOWN;
                case 1:
                    return AXIS;
                case 2:
                    return ARGB;
                case 3:
                    return POINT;
                case 4:
                    return POINT2;
                case 5:
                    return LINE;
                case 6:
                    return CIRCLE;
                case 7:
                    return OVAL;
                case 8:
                    return ARC;
                case 9:
                    return RECT;
                case 10:
                    return PATH;
                case 11:
                    return TEXT;
                case 12:
                    return BITMAP;
                case 13:
                    return DEMO1;
                case 14:
                    return ROTATE;
                case 15:
                    return SCALE;
                case 16:
                    return SKEW;
                case 17:
                    return SAVEANDRESTORE;
                case 18:
                    return REGION;
                default:
                    return UNKNOWN;

            }
        }

    }

    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        init(null, 0);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 获取画板的宽度和高度
        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();

        switch (drawMode) {
            case AXIS:
                drawAxis(canvas);
                break;
            case ARGB:
                drawArgb(canvas);
                break;
            case POINT:
                drawPoint(canvas);
                break;
            case POINT2:
                drawPoint2(canvas);
                break;
            case LINE:
                drawLine(canvas);
                break;
            case CIRCLE:
                drawCircle(canvas);
                break;
            case OVAL:
                drawCircle(canvas);
                break;
            case ARC:
                drawArc(canvas);
                break;
            case RECT:
                drawRect(canvas);
                break;
            case PATH:
                drawPath(canvas);
                break;
            case TEXT:
                drawText(canvas);
                break;
            case BITMAP:
                drawBitmap(canvas);
                break;
            case DEMO1:
                drawDemo1(canvas);
                break;
            case ROTATE:
                drawRotate(canvas);
                break;
            case SCALE:
                drawScale(canvas);
                break;
            case SKEW:
                drawSkew(canvas);
                break;
            case SAVEANDRESTORE:
                drawSaveAndRestore(canvas);
                break;
            case REGION:
                drawRegion(canvas);
                break;
            default:
                break;
        }

    }

    // 绘制坐标系
    private void drawAxis(Canvas canvas) {
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(6 * density);

        // 用绿色画X轴，用蓝色画Y轴

        // 第一次绘制坐标轴
        mPaint.setColor(Color.GREEN);
        canvas.drawLine(0, 0, canvasWidth, 0, mPaint);// 绘制X轴
        mPaint.setColor(Color.BLUE);
        canvas.drawLine(0, 0, 0, canvasHeight, mPaint);// 绘制Y轴

        // 对坐标进行平移，第二次绘制坐标轴
        canvas.translate(canvasWidth / 4, canvasHeight / 4);// 向左向右各平移屏幕的1/4
        mPaint.setColor(Color.GREEN);
        canvas.drawLine(0, 0, canvasWidth, 0, mPaint);// 绘制X轴
        mPaint.setColor(Color.BLUE);
        canvas.drawLine(0, 0, 0, canvasHeight, mPaint);// 绘制Y轴

        // 再次平移坐标轴，然后旋转，第三次绘制坐标轴
        canvas.translate(canvasWidth / 4, canvasHeight / 4);// 向左向右各平移屏幕的1/4
        canvas.rotate(45);// 顺时针旋转45°角
        mPaint.setColor(Color.GREEN);
        canvas.drawLine(0, 0, canvasWidth, 0, mPaint);// 绘制X轴
        mPaint.setColor(Color.BLUE);
        canvas.drawLine(0, 0, 0, canvasHeight, mPaint);// 绘制Y轴
    }

    private void drawArgb(Canvas canvas) {
        canvas.drawARGB(255, 139, 197, 186);
        // 第一个参数，透明度0-255
        // 第二、三、四个参数，分别是三原色R(Red)、G(Green)、B(Blue)
        // 都是16进制的0-255
    }

    private void drawPoint(Canvas canvas) {
        mPaint.setColor(0xff8bc5ba);// 设置颜色
        mPaint.setStrokeWidth(80 * density);// 设置线宽，如果不设置线宽，无法绘制点
        int x = canvasWidth / 2;// 水平居中
        int deltaY = canvasHeight / 3;// 将屏幕分为三份
        int y = deltaY / 2;// 第一份的中间
        // 绘制Cap为BUTT的点
        mPaint.setStrokeCap(Paint.Cap.BUTT);
        canvas.drawPoint(x, y, mPaint);
        // 绘制Cap为ROUND的点
        canvas.translate(0, deltaY);// 将画板往下移动一份
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawPoint(x, y, mPaint);
        mPaint.setColor(Color.RED);// 设置颜色
        mPaint.setStrokeWidth(10 * density);
        canvas.drawPoint(x, y, mPaint);
        // 绘制Cap为SQUARE的点
        canvas.translate(0, deltaY);// 将画板往下移动一份
        mPaint.setStrokeCap(Paint.Cap.SQUARE);
        mPaint.setStrokeWidth(80 * density);
        canvas.drawPoint(x, y, mPaint);

        // 注意：
        // 1 如果的单独画一个点，那么ROUND和SQUARE相对于BUTT，是没有任何多余的长度的
        // 2 如果用画点 来模仿画矩形或者画圆，是否可以行？当然是可行的
        // 3 如果线宽比较大，那么x，y表示的点是图形的左上角还是中心点？当然是中心点啦
    }

    private void drawPoint2(Canvas canvas) {
        mPaint.setColor(Color.RED);// 设置颜色
        mPaint.setStrokeWidth(30 * density);// 设置线宽，如果不设置线宽，无法绘制点
        int deltaX = canvasWidth / 4;// 水平分成4份
        int x = deltaX / 2;// 水平第一份的中间
        int deltaY = canvasHeight / 4;// 将垂直分为4份
        int y = deltaY / 2;// 垂直第一份的中间

        mPaint.setStrokeCap(Paint.Cap.BUTT);
        float[] pts1 = new float[]{x, y, deltaX + x, y, 2 * deltaX + x, y};// 三个点
        float[] pts2 = new float[]{x, y, deltaX + x, y, 2 * deltaX + x, y,
                3 * deltaX + x};// 三个点加一个x坐标
        canvas.drawPoints(pts1, mPaint);
        //
        // canvas.drawPoints(pts2, mPaint);//pts2的个数是奇数，会报错

        canvas.translate(0, deltaY);// 将画板往下移动一份
        canvas.drawPoints(pts1, 0, 4, mPaint);// count=1
        // offset:从pts的第几个元素开始取值，第一个为0，第二个为1，第三个为2
        // count:要获得数组的元素个数，必为偶数

        // 注意：
        // pts:多个像素点的坐标，元素个数必须是偶数，两个一组为一个像素点的横纵坐标

    }

    private void drawLine(Canvas canvas) {
        // 看完drawPoint2，这个就很简单了
        mPaint.setColor(Color.RED);// 设置颜色
        mPaint.setStrokeWidth(20 * density);// 设置线宽，如果不设置线宽，无法绘制点
        int deltaX = canvasWidth / 4;// 水平分成4份
        int x = deltaX / 2;// 水平第一份的中间
        int deltaY = canvasHeight / 4;// 将垂直分为4份
        int y = deltaY / 2;// 垂直第一份的中间

        mPaint.setStyle(Paint.Style.FILL);

        // 绘制Cap为BUTT的点
        mPaint.setStrokeCap(Paint.Cap.BUTT);
        canvas.drawLine(x, y, deltaX + x, y, mPaint);
        // 绘制Cap为ROUND的点
        canvas.translate(0, deltaY);// 将画板往下移动一份
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawLine(x, y, deltaX + x, y, mPaint);
        // 绘制Cap为SQUARE的点
        canvas.translate(0, deltaY);// 将画板往下移动一份
        mPaint.setStrokeCap(Paint.Cap.SQUARE);
        canvas.drawLine(x, y, deltaX + x, y, mPaint);

        // 画一个折线
        mPaint.setStrokeWidth(2 * density);
        canvas.translate(0, deltaY);// 将画板往下移动一份
        // 这里的pts要求是4的倍数
        float[] pts = new float[]{x, y, deltaX + x, y + 30, deltaX + x,
                y + 30, 2 * deltaX + x, y - 30};
        canvas.drawLines(pts, mPaint);

        // 注意：
        // pts要求是4的倍数
    }

    private void drawCircle(Canvas canvas) {
        // 圆是圆心加半径，椭圆就是一个外包矩形，然后设置画笔的style
        mPaint.setColor(0xff8bc5ba);// 设置颜色
        mPaint.setStrokeWidth(5 * density);// 设置线宽，如果不设置线宽，无法绘制点
        int x = canvasWidth / 2;// 水平居中
        int deltaY = canvasHeight / 2;// 将屏幕分为三份
        int y = deltaY / 2;// 第一份的中间
        // 画圆，设置style是Full
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(x, y, y - 10, mPaint);// 第一个是圆心的x和y坐标，第三个是半径

        canvas.translate(0, deltaY);// 将画板往下移动一份
        // 画椭圆
        RectF oval = new RectF(10, 10, canvasWidth - 10, deltaY - 10);// 左上右下
        canvas.drawOval(oval, mPaint);
    }

    private void drawArc(Canvas canvas) {
        // 这个也很简单，四个参数
        // 第一个参数为外包矩形，第二个参数为起始角度，从中心水平向右为0°，顺时针旋转，第三个参数是旋转多少度，第四个参数是是否连接中心
        int canvasWidth = canvas.getWidth();
        int canvasHeight = canvas.getHeight();
        int count = 5;
        float ovalHeight = canvasHeight / (count + 1);
        float left = 10 * density;
        float top = 0;
        float right = canvasWidth - left;
        float bottom = ovalHeight;
        RectF rectF = new RectF(left, top, right, bottom);

        mPaint.setStrokeWidth(2 * density);// 设置线宽
        mPaint.setColor(0xff8bc5ba);// 设置颜色
        mPaint.setStyle(Paint.Style.FILL);// 默认设置画笔为填充模式

        // 绘制用drawArc绘制完整的椭圆
        canvas.translate(0, ovalHeight / count);
        canvas.drawArc(rectF, 0, 360, true, mPaint);

        // 绘制椭圆的四分之一,起点是钟表的3点位置，从3点绘制到6点的位置
        canvas.translate(0, (ovalHeight + ovalHeight / count));
        canvas.drawArc(rectF, 0, 90, true, mPaint);

        // 绘制椭圆的四分之一,将useCenter设置为false
        canvas.translate(0, (ovalHeight + ovalHeight / count));
        canvas.drawArc(rectF, 0, 90, false, mPaint);

        // 绘制椭圆的四分之一，只绘制轮廓线
        mPaint.setStyle(Paint.Style.STROKE);// 设置画笔为线条模式
        canvas.translate(0, (ovalHeight + ovalHeight / count));
        canvas.drawArc(rectF, 0, 90, true, mPaint);

        // 绘制带有轮廓线的椭圆的四分之一
        // 1. 先绘制椭圆的填充部分
        mPaint.setStyle(Paint.Style.FILL);// 设置画笔为填充模式
        canvas.translate(0, (ovalHeight + ovalHeight / count));
        canvas.drawArc(rectF, 0, 90, true, mPaint);
        // 2. 再绘制椭圆的轮廓线部分
        mPaint.setStyle(Paint.Style.STROKE);// 设置画笔为线条模式
        mPaint.setColor(0xff0000ff);// 设置轮廓线条为蓝色
        canvas.drawArc(rectF, 0, 90, true, mPaint);
    }

    private void drawRect(Canvas canvas) {
        // 画矩形也很简单，就是四个边界参数，说白了就是左上角和右下角的坐标，都是一回事
        mPaint.setColor(0xff8bc5ba);// 设置颜色
        mPaint.setStrokeWidth(5 * density);// 设置线宽，如果不设置线宽，无法绘制点
        mPaint.setStyle(Paint.Style.STROKE);
        int x = canvasWidth / 2;// 水平居中
        int deltaY = canvasHeight / 2;// 将屏幕分为2份
        int y = deltaY / 2;// 第一份的中间

        // 直接用四个边界参数画矩形
        canvas.drawRect(50, 50, canvasWidth - 50, deltaY - 50, mPaint);

        canvas.translate(0, deltaY);// 将画板往下移动一份
        // 画圆角矩形,第二三个参数，分别是圆角椭圆的x和y
        canvas.drawRoundRect(new RectF(50, 50, canvasWidth - 50, deltaY - 50),
                15, 15, mPaint);
    }

    private void drawPath(Canvas canvas) {
        int deltaX = canvasWidth / 4;
        int deltaY = (int) (deltaX * 0.75);

        mPaint.setColor(0xff8bc5ba);// 设置画笔颜色
        mPaint.setStrokeWidth(4);// 设置线宽

		/*--------------------------用Path画填充面-----------------------------*/
        mPaint.setStyle(Paint.Style.FILL);// 设置画笔为填充模式
        Path path = new Path();
        // 向Path中加入Arc
        RectF arcRecF = new RectF(0, 0, deltaX, deltaY);
        path.addArc(arcRecF, 0, 135);
        // 向Path中加入Oval
        RectF ovalRecF = new RectF(deltaX, 0, deltaX * 2, deltaY);
        path.addOval(ovalRecF, Path.Direction.CCW);
        // 向Path中添加Circle
        path.addCircle((float) (deltaX * 2.5), deltaY / 2, deltaY / 2,
                Path.Direction.CCW);
        // 向Path中添加Rect
        RectF rectF = new RectF(deltaX * 3, 0, deltaX * 4, deltaY);
        path.addRect(rectF, Path.Direction.CCW);
        canvas.drawPath(path, mPaint);

		/*--------------------------用Path画线--------------------------------*/
        mPaint.setStyle(Paint.Style.STROKE);// 设置画笔为线条模式
        canvas.translate(0, deltaY * 2);
        Path path2 = path;
        canvas.drawPath(path2, mPaint);

		/*-----------------使用lineTo、arcTo、quadTo、cubicTo画线--------------*/
        mPaint.setStyle(Paint.Style.STROKE);// 设置画笔为线条模式
        canvas.translate(0, deltaY * 2);
        Path path3 = new Path();
        // 用pointList记录不同的path的各处的连接点
        List<Point> pointList = new ArrayList<Point>();
        // 1. 第一部分，绘制线段
        path3.moveTo(0, 0);
        path3.lineTo(deltaX / 2, 0);// 绘制线段
        pointList.add(new Point(0, 0));
        pointList.add(new Point(deltaX / 2, 0));
        // 2. 第二部分，绘制椭圆右上角的四分之一的弧线
        RectF arcRecF1 = new RectF(0, 0, deltaX, deltaY);
        path3.arcTo(arcRecF1, 270, 90);// 绘制圆弧
        pointList.add(new Point(deltaX, deltaY / 2));
        // 3. 第三部分，绘制椭圆左下角的四分之一的弧线
        // 注意，我们此处调用了path的moveTo方法，将画笔的移动到我们下一处要绘制arc的起点上
        path3.moveTo(deltaX * 1.5f, deltaY);
        RectF arcRecF2 = new RectF(deltaX, 0, deltaX * 2, deltaY);
        path3.arcTo(arcRecF2, 90, 90);// 绘制圆弧
        pointList.add(new Point((int) (deltaX * 1.5), deltaY));
        // 4. 第四部分，绘制二阶贝塞尔曲线
        // 二阶贝塞尔曲线的起点就是当前画笔的位置，然后需要添加一个控制点，以及一个终点
        // 再次通过调用path的moveTo方法，移动画笔
        path3.moveTo(deltaX * 1.5f, deltaY);
        // 绘制二阶贝塞尔曲线
        path3.quadTo(deltaX * 2, 0, deltaX * 2.5f, deltaY / 2);
        pointList.add(new Point((int) (deltaX * 2.5), deltaY / 2));
        // 5. 第五部分，绘制三阶贝塞尔曲线，三阶贝塞尔曲线的起点也是当前画笔的位置
        // 其需要两个控制点，即比二阶贝赛尔曲线多一个控制点，最后也需要一个终点
        // 再次通过调用path的moveTo方法，移动画笔
        path3.moveTo(deltaX * 2.5f, deltaY / 2);
        // 绘制三阶贝塞尔曲线
        path3.cubicTo(deltaX * 3, 0, deltaX * 3.5f, 0, deltaX * 4, deltaY);
        pointList.add(new Point(deltaX * 4, deltaY));

        // Path准备就绪后，真正将Path绘制到Canvas上
        canvas.drawPath(path3, mPaint);

        // 最后绘制Path的连接点，方便我们大家对比观察
        mPaint.setStrokeWidth(10);// 将点的strokeWidth要设置的比画path时要大
        mPaint.setStrokeCap(Paint.Cap.ROUND);// 将点设置为圆点状
        mPaint.setColor(0xff0000ff);// 设置圆点为蓝色
        for (Point p : pointList) {
            // 遍历pointList，绘制连接点
            canvas.drawPoint(p.x, p.y, mPaint);
        }
    }

    private void drawText(Canvas canvas) {
        int halfCanvasWidth = canvasWidth / 2;
        float translateY = textHeight;
        mPaint.setStyle(Paint.Style.FILL);// 绘制文字是用点绘制的，一定要设置style为FILL
        mPaint.setStrokeWidth(4);
        mPaint.setTextSize(50);
        // 绘制正常文本
        canvas.save();
        canvas.translate(0, translateY);
        canvas.drawText("正常绘制文本", 0, 0, mPaint);
        canvas.restore();
        translateY += textHeight * 2;

        // 绘制绿色文本
        mPaint.setColor(0xff00ff00);// 设置字体为绿色
        canvas.save();
        canvas.translate(0, translateY);// 将画笔向下移动
        canvas.drawText("绘制绿色文本", 0, 0, mPaint);
        canvas.restore();
        mPaint.setColor(0xff000000);// 重新设置为黑色
        translateY += textHeight * 2;

        // 设置左对齐
        mPaint.setTextAlign(Paint.Align.LEFT);// 设置左对齐
        canvas.save();
        canvas.translate(halfCanvasWidth, translateY);
        canvas.drawText("左对齐文本", 0, 0, mPaint);
        canvas.restore();
        translateY += textHeight * 2;

        // 设置居中对齐
        mPaint.setTextAlign(Paint.Align.CENTER);// 设置居中对齐
        canvas.save();
        canvas.translate(halfCanvasWidth, translateY);
        canvas.drawText("居中对齐文本", 0, 0, mPaint);
        canvas.restore();
        translateY += textHeight * 2;

        // 设置右对齐
        mPaint.setTextAlign(Paint.Align.RIGHT);// 设置右对齐
        canvas.save();
        canvas.translate(halfCanvasWidth, translateY);
        canvas.drawText("右对齐文本", 0, 0, mPaint);
        canvas.restore();
        mPaint.setTextAlign(Paint.Align.LEFT);// 重新设置为左对齐
        translateY += textHeight * 2;

        // 设置下划线
        mPaint.setUnderlineText(true);// 设置具有下划线
        canvas.save();
        canvas.translate(0, translateY);
        canvas.drawText("下划线文本", 0, 0, mPaint);
        canvas.restore();
        mPaint.setUnderlineText(false);// 重新设置为没有下划线
        translateY += textHeight * 2;

        // 绘制加粗文字
        mPaint.setFakeBoldText(true);// 将画笔设置为粗体
        canvas.save();
        canvas.translate(0, translateY);
        canvas.drawText("粗体文本", 0, 0, mPaint);
        canvas.restore();
        mPaint.setFakeBoldText(false);// 重新将画笔设置为非粗体状态
        translateY += textHeight * 2;

        // 文本绕绘制起点顺时针旋转
        canvas.save();
        canvas.translate(0, translateY);
        canvas.rotate(20);
        canvas.drawText("文本绕绘制起点旋转20度", 0, 0, mPaint);
        canvas.restore();
        translateY += 200;

        // 根据path绘制文字
        canvas.save();
        canvas.translate(0, translateY);
        Path path = new Path();
        path.moveTo(50, 50);
        path.lineTo(100, 100);
        path.lineTo(200, 200);
        path.lineTo(300, 50);
        // path.close();//也可以不封闭
        canvas.drawTextOnPath("文字跟着path走，超过长度就停", path, 50, 50, mPaint); // 绘制文字
        canvas.restore();
    }

    private void drawBitmap(Canvas canvas) {
        // 如果bitmap不存在，那么就不执行下面的绘制代码
        if (bitmap == null) {
            return;
        }

        Rect src = new Rect();// 图片的显示部分
        Rect dst = new Rect();// 屏幕位置及尺寸，供图片显示的区域
        // src 这个是表示绘画图片的大小
        src.left = 0; // 0,0
        src.top = 0;
        src.right = 100;
        src.bottom = 100;
        // 下面的 dst 是表示 绘画这个图片的位置
        dst.left = 0;
        dst.top = 0;
        dst.right = 200;
        dst.bottom = 200;
        canvas.drawBitmap(bitmap, src, dst, mPaint);// 这个方法 第一个参数是图片原来的大小，第二个参数是
        // 绘画该图片需显示多少。也就是说你想绘画该图片的某一些地方，而不是全部图片，第三个参数表示该图片绘画的位置

        canvas.translate(0, 210);// 往下移动110
        src.left = 0; // 0,0
        src.top = 0;
        src.right = 200;
        src.bottom = 200;
        // 扩大图片的显示部分
        canvas.drawBitmap(bitmap, src, dst, mPaint);

        canvas.translate(0, 210);// 往下移动110
        // 扩大供图片显示的区域
        dst.left = 100;
        dst.top = 100;
        dst.right = 400;
        dst.bottom = 400;
        canvas.drawBitmap(bitmap, src, dst, mPaint);

    }

    private void drawDemo1(Canvas canvas) {
        mPaint.setAntiAlias(true);

        mPaint.setStyle(Style.STROKE);

        int halfCanvasWidth = canvasWidth / 2;
        canvas.translate(halfCanvasWidth, 200 * density); // 将位置移动画纸的坐标点:150,150
        canvas.drawCircle(0, 0, 100 * density, mPaint); // 画圆圈

        // 使用path绘制路径文字
        canvas.save();
        canvas.translate(-75 * density, -75 * density);

        // 获取文字的宽度
        Rect stRect = new Rect();
        String st = "http://www.android777.com";
        mPaint.getTextBounds(st, 0, st.length(), stRect);
        // 环形文字所占的角度
        int degree = (int) (360 * stRect.width() / (Math.PI * 2 * 75 * density));

        Path path = new Path();
        path.addArc(new RectF(0, 0, 150 * density, 150 * density), -90 - degree
                / 2, 270);
        Paint citePaint = new Paint(mPaint);
        citePaint.setTextSize(14 * density);
        citePaint.setStrokeWidth(1);

        canvas.drawTextOnPath(st, path, 28, 0, citePaint);
        canvas.restore();

        // 画刻度
        Paint tmpPaint = new Paint(mPaint); // 小刻度画笔对象
        tmpPaint.setStrokeWidth(1 * density);
        float y = 100 * density;
        int count = 60; // 总刻度数

        for (int i = 0; i < count; i++) {
            if (i % 5 == 0) {
                canvas.drawLine(0f, y, 0, y + 12f * density, mPaint);
                if ((i / 5 + 1) > 9) {
                    canvas.drawText(String.valueOf(i / 5 + 1), -8f * density, y
                            + 25f * density, tmpPaint);
                } else {
                    canvas.drawText(String.valueOf(i / 5 + 1), -4f * density, y
                            + 25f * density, tmpPaint);
                }

            } else {
                canvas.drawLine(0f, y, 0f, y + 5f * density, tmpPaint);
            }
            canvas.rotate(360 / count, 0f, 0f);
            // 旋转画纸
        }

        // 绘制指针
        tmpPaint.setColor(Color.GRAY);
        tmpPaint.setStrokeWidth(4);
        canvas.drawCircle(0, 0, 7 * density, tmpPaint);
        tmpPaint.setStyle(Style.FILL);
        tmpPaint.setColor(Color.YELLOW);
        canvas.drawCircle(0, 0, 5 * density, tmpPaint);
        canvas.drawLine(0, 10 * density, 0, -65 * density, mPaint);

    }

    private void drawRotate(Canvas canvas) {
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(20);
        mPaint.setStrokeCap(Cap.ROUND);
        canvas.drawPoint(200, 200, mPaint);

        Rect rect = new Rect(50, 0, 150, 50);
        canvas.translate(200, 200);
        mPaint.setStrokeWidth(1);
        for (int i = 0; i < 36; i++) {
            if (i == 0) {
                mPaint.setStyle(Style.FILL);
            } else {
                canvas.rotate(10);
                mPaint.setStyle(Style.STROKE);
            }
            canvas.drawRect(rect, mPaint);
        }

        canvas.restore();
        canvas.translate(200, 400);
        mPaint.setStrokeWidth(20);
        mPaint.setStrokeCap(Cap.ROUND);
        canvas.drawPoint(200, 200, mPaint);
        mPaint.setStrokeWidth(1);
        for (int i = 0; i < 36; i++) {
            if (i == 0) {
                mPaint.setStyle(Style.FILL);
            } else {
                canvas.rotate(10, 200, 200);
                mPaint.setStyle(Style.STROKE);
            }
            canvas.drawRect(rect, mPaint);
        }

    }

    private void drawScale(Canvas canvas) {
        canvas.drawBitmap(bitmap, 0, 0, mPaint);
        canvas.scale(0.8f, 0.8f);
        canvas.drawBitmap(bitmap, 0, 0, mPaint);
        canvas.scale(0.8f, 0.8f);
        canvas.drawBitmap(bitmap, 0, 0, mPaint);
    }

    private void drawSkew(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        mPaint.setColor(Color.BLUE);
        canvas.drawRect(new Rect(0, 0, 400, 400), mPaint);

        // x 方向上倾斜45 度
        canvas.skew(1, 0);
        mPaint.setColor(Color.RED);
        canvas.drawRect(new Rect(0, 0, 400, 400), mPaint);

        // x 方向上倾斜-45 度
        canvas.skew(-1, 0);

        canvas.translate(0, 500);

        mPaint.setColor(Color.BLUE);
        canvas.drawRect(new Rect(0, 0, 400, 400), mPaint);

        // y 方向上倾斜45 度
        canvas.skew(0, 1);
        mPaint.setColor(Color.RED);
        canvas.drawRect(new Rect(0, 0, 400, 400), mPaint);
    }

    private void drawSaveAndRestore(Canvas canvas) {
        Bitmap bmp = BitmapFactory.decodeResource(getResources(),
                R.drawable.batman);
        Log.d("pepe", "画图之前：" + canvas.getSaveCount());

        canvas.save();

        canvas.translate(300, 300);
        canvas.drawBitmap(bmp, 0, 0, mPaint);
        Log.d("pepe", "画第1张图：" + canvas.getSaveCount());
        canvas.save();

        canvas.rotate(45);
        canvas.drawBitmap(bmp, 0, 0, mPaint);
        Log.d("pepe", "画第2张图：" + canvas.getSaveCount());
        canvas.save();

        canvas.rotate(45);
        canvas.drawBitmap(bmp, 0, 0, mPaint);
        Log.d("pepe", "画第3张图：" + canvas.getSaveCount());
        canvas.save();

        canvas.translate(0, 200);
        canvas.drawBitmap(bmp, 0, 0, mPaint);
        Log.d("pepe", "画第4张图：" + canvas.getSaveCount());

        canvas.restore();
        Log.d("pepe", "restore第4张图减1：" + canvas.getSaveCount());
        canvas.restore();
        Log.d("pepe", "restore第3张图减1：" + canvas.getSaveCount());
        canvas.translate(0, 200);
        canvas.drawBitmap(bmp, 0, 0, mPaint);
        Log.d("pepe", "画第5张图没save没restore不变：" + canvas.getSaveCount());
    }

    private void drawRegion(Canvas canvas) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(6);
        mPaint.setTextSize(10 * density);
        Path path = new Path();
        path.addCircle(150,150,100, Path.Direction.CCW);
        Rect rect1 = new Rect(10, 10, 110, 110);
        Rect rect2 = new Rect(50, 50, 150, 150);
        Rect rect3 = new Rect(0, 0, 300, 300);

        canvas.save();
        mPaint.setColor(Color.RED);
//        mPaint.setStyle(Style.STROKE);
//        canvas.drawRect(rect1,mPaint);
//        canvas.drawRect(rect2,mPaint);
        mPaint.setStyle(Style.FILL);
        canvas.drawText("默认Region.Op.UNION---并集", 0, 10 * density, mPaint);
        canvas.translate(0, 10 * density);
        canvas.clipRect(rect1); // 第一个
        canvas.clipRect(rect2, Region.Op.UNION); // 第二个
//        canvas.clipPath(path, Region.Op.INTERSECT); // 第三个,交集
        mPaint.setColor(Color.CYAN);
        canvas.drawRect(rect3, mPaint);
        canvas.restore();

        canvas.translate(10 * density + 300, 0);

        canvas.save();
        mPaint.setColor(Color.RED);
        canvas.drawText("Region.Op.DIFFERENCE---差集，即A-B", 0, 10 * density, mPaint);
        canvas.translate(0, 10 * density);
        canvas.clipRect(10, 10, 110, 110); // 第一个
        canvas.clipRect(50, 50, 150, 150, Region.Op.DIFFERENCE); // 第二个
//        canvas.clipPath(path, Region.Op.INTERSECT); // 第三个,交集
        mPaint.setColor(Color.CYAN);
        canvas.drawRect(0, 0, 200, 200, mPaint);
        canvas.restore();

        canvas.translate(-10 * density - 300, 10 * density + 200);

        canvas.save();
        mPaint.setColor(Color.RED);
        canvas.drawText("Region.Op.INTERSECT---交集", 0, 10 * density, mPaint);
        canvas.translate(0, 10 * density);
        canvas.clipRect(10, 10, 110, 110); // 第一个
        canvas.clipRect(50, 50, 150, 150, Region.Op.INTERSECT); // 第二个
//        canvas.clipPath(path, Region.Op.INTERSECT); // 第三个,交集
        mPaint.setColor(Color.CYAN);
        canvas.drawRect(0, 0, 200, 200, mPaint);
        canvas.restore();

        canvas.translate(10 * density + 300, 0);

        canvas.save();
        mPaint.setColor(Color.RED);
        canvas.drawText("Region.Op.XOR---补集", 0, 10 * density, mPaint);
        canvas.translate(0, 10 * density);
        canvas.clipRect(10, 10, 110, 110); // 第一个
        canvas.clipRect(50, 50, 150, 150, Region.Op.XOR); // 第二个
        mPaint.setColor(Color.CYAN);
        canvas.drawRect(0, 0, 200, 200, mPaint);
        canvas.restore();

        canvas.translate(-10 * density -300, 10 * density + 200);

        canvas.save();
        mPaint.setColor(Color.RED);
        canvas.drawText("Region.Op.REVERSE_DIFFERENCE，B和A的差集，即B-A", 0, 10 * density, mPaint);
        canvas.translate(0, 10 * density);
        canvas.clipRect(10, 10, 110, 110); // 第一个
        canvas.clipRect(50, 50, 150, 150, Region.Op.REVERSE_DIFFERENCE); // 第二个
        mPaint.setColor(Color.CYAN);
        canvas.drawRect(0, 0, 200, 200, mPaint);
        canvas.restore();

        canvas.translate(0, 10 * density + 200);

        canvas.save();
        mPaint.setColor(Color.RED);
        canvas.drawText("Region.Op.REPLACE，B全部显示，并覆盖和A的交集", 0, 10 * density, mPaint);
        canvas.translate(0, 10 * density);
        canvas.clipRect(10, 10, 110, 110); // 第一个
        canvas.clipRect(50, 50, 150, 150, Region.Op.REPLACE); // 第二个
        mPaint.setColor(Color.CYAN);
        canvas.drawRect(0, 0, 200, 200, mPaint);
        canvas.restore();
    }

    public void setBitmap(Bitmap bm) {
        releaseBitmap();
        bitmap = bm;
    }

    private void releaseBitmap() {
        if (bitmap != null && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        bitmap = null;
    }

    public void destroy() {
        releaseBitmap();
    }

    public void setDrawMode(int value) {
        this.drawMode = DrawMode.valueOf(value);
        postInvalidate();
    }

    public int getDrawmode() {
        return drawMode.ordinal();
    }

    private float fontSize = getResources().getDimensionPixelSize(
            R.dimen.default_font_size);
    private float textHeight;

    private void init(AttributeSet attrs, int defStyle) {
        // 初始化TextPaint
        mPaint = new TextPaint();

        // paint的默认字体大小
        Log.d("pepe", "默认字体大小: " + mPaint.getTextSize() + "px");

        // paint的默认颜色
        Log.d("pepe", "默认颜色: " + Integer.toString(mPaint.getColor(), 16));

        // paint的默认style是FILL，即填充模式
        Log.d("pepe", "默认style: " + mPaint.getStyle().toString());

        // paint的默认cap是
        Log.d("pepe", "默认cap: " + mPaint.getStrokeCap().toString());

        // paint默认的strokeWidth
        Log.d("pepe", "默认strokeWidth: " + mPaint.getStrokeWidth() + "");

        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);// 设置为抗锯齿
        mPaint.setTextSize(fontSize);// 设置字体大小

        // 初始化textHeight
        textHeight = fontSize;
        // Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        // textHeight = Math.abs(fontMetrics.top) + fontMetrics.bottom;
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.batman);
    }


}
