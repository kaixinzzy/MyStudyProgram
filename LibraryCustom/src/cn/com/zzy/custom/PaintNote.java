package cn.com.zzy.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.view.View;

/**
 * Created by BCLZzy on 2017/10/19.
 * Paint类介绍  参考：http://www.cnblogs.com/aibuli/p/efef9d774df97c553a8a0c0c3495ba35.html
 *
 * Paint即画笔，在绘图过程中起到了极其重要的作用，画笔主要保存了颜色，
 * 样式等绘制信息，指定了如何绘制文本和图形，
 *
 * 画笔对象有很多设置方法，大体上可以分为两类，:
 *      一类与图形绘制相关
 *      一类与文本绘制相关。
 *
 * 1.图形绘制  --------------------------------------------------------------------------------------
 * 颜色
 * setARGB(int a,int r,int g,int b);设置绘制的颜色，a代表透明度，r，g，b代表颜色值。
 * setAlpha(int a);                 设置绘制图形的透明度。
 * setColor(int color);设置绘制的颜色，使用颜色值来表示，该颜色值包括透明度和RGB颜色。
 *
 * 抗锯齿 抖动 渐变 阴影 点画线
 * setAntiAlias(boolean aa);设置是否使用抗锯齿功能，会消耗较大资源，绘制图形速度会变慢。
 * setDither(boolean dither);设定是否使用图像抖动处理，会使绘制出来的图片颜色更加平滑和饱满，图像更加清晰
 * setShader(Shader shader);设置图像效果，使用Shader可以绘制出各种渐变效果
 * setShadowLayer(float radius ,float dx,float dy,int color);
 * 在图形下面设置阴影层，产生阴影效果，radius为阴影的角度，dx和dy为阴影在x轴和y轴上的距离，color为阴影的颜色
 * setPathEffect(PathEffect effect);设置绘制路径的效果，如点画线等
 *
 * 空心
 * setStyle(Paint.Style style);设置画笔的样式，为FILL，FILL_OR_STROKE，或STROKE    Style.FILL: 实心   STROKE:空心   FILL_OR_STROKE:同时实心与空心
 * setStrokeWidth(float width);当画笔样式为STROKE或FILL_OR_STROKE时，设置笔刷的粗细度
 * setStrokeCap(Paint.Cap cap);当画笔样式为STROKE或FILL_OR_STROKE时，设置笔刷的图形样式，如圆形样式Cap.ROUND,或方形样式Cap.SQUARE
 *
 * 滤镜 渐变
 * setMaskFilter(MaskFilter maskfilter);设置MaskFilter，可以用不同的MaskFilter实现滤镜的效果，如滤化，立体等
 * setColorFilter(ColorFilter colorfilter);设置颜色过滤器，可以在绘制颜色时实现不用颜色的变换效果
 * setFilterBitmap(boolean filter);
 * 如果该项设置为true，则图像在动画进行中会滤掉对Bitmap图像的优化操作，加快显示
 * 速度，本设置项依赖于dither和xfermode的设置
 *
 * 组合
 * setSrokeJoin(Paint.Join join);设置绘制时各图形的结合方式，如平滑效果等
 * setXfermode(Xfermode xfermode);设置图形重叠时的处理方式，如合并，取交集或并集，经常用来制作橡皮的擦除效果
 *
 *
 * 2.文本绘制  --------------------------------------------------------------------------------------
 *
 * setTextAlign(Paint.Align align);         文字的对齐方向
 * setTextSize(float textSize);             文字的字号大小
 * setTypeface(Typeface typeface);          字体风格，包括粗体，斜体以及衬线体，非衬线体等
 * setFakeBoldText(boolean fakeBoldText);   文字模拟粗体，小文本显示效果较差
 * setTextSkewX(float skewX);               文字斜体文字，skewX为倾斜弧度
 * setUnderlineText(boolean underlineText); 文字下划线
 * setStrikeThruText(boolean strikeThruText);文字删除线
 *
 * setSubpixelText(boolean subpixelText);
 * 设置该项为true，将有助于文本在LCD屏幕上的显示效果
 *
 * setTextScaleX(float scaleX);
 * 设置绘制文字x轴的缩放比例，可以实现文字的拉伸的效果
 */

public class PaintNote extends View {
    Paint paint = new Paint();

    public PaintNote(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {//重载onDraw方法
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);      //画布颜色

        paint.setColor(Color.RED);          //画笔颜色
        paint.setStyle(Paint.Style.STROKE); //画笔为空心
        paint.setStrokeWidth(3);            //空心笔刷的粗细度
        paint.setAntiAlias(true);           //抗锯齿

        //canvas.draw
        canvas.drawLine(50, 50, 450, 50, paint);            //画布上，绘制直线
        canvas.drawRect(10, 90, 70, 150, paint);            //绘制正方形
        canvas.drawRect(10, 170, 70, 200, paint);           //绘制长方形
        canvas.drawCircle(40, 40, 30, paint);               //绘制圆形
        canvas.drawOval(new RectF(10, 220, 70, 250), paint);//绘制椭圆形

        Path pathSJX = new Path();//三角形
        pathSJX.moveTo(10, 330);//绘画基点
        pathSJX.lineTo(70, 330);
        pathSJX.lineTo(40, 270);
        pathSJX.close();//把开始的点和最后的点连接在一起，构成一个封闭图形
        canvas.drawPath(pathSJX, paint);//绘制三角形

        Path pathTixing = new Path();//梯形
        pathTixing.moveTo(10, 410);//绘画基点
        pathTixing.lineTo(70, 410);
        pathTixing.lineTo(55, 350);
        pathTixing.lineTo(25, 350);
        pathTixing.close();//把开始的点和最后的点连接在一起，构成一个封闭图形
        canvas.drawPath(pathTixing, paint);//绘制梯形




    }
}
