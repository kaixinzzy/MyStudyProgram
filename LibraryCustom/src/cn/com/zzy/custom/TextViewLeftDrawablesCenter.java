package cn.com.zzy.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.TextView;

import cn.com.zzy.multi.MyLog;

/**
 * Created by BCLZzy on 2017/8/10.
 * 使TextView中leftDrawable图片与文字一起居中显示
 */

public class TextViewLeftDrawablesCenter extends TextView {
    private static final String TAG = "TextViewLeftDrawablesCenter";
    private Drawable[] drawables;
    private float textWidth;
    private float bodyWidth;

    public TextViewLeftDrawablesCenter(Context context) {
        super(context);
        MyLog.Log("~~~", "Create 1");
        init();
    }

    public TextViewLeftDrawablesCenter(Context context, AttributeSet attrs) {
        super(context, attrs);
        MyLog.Log("~~~", "Create 2");
        init();

    }

    public TextViewLeftDrawablesCenter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        MyLog.Log("~~~", "Create 3");
        init();
    }

    private void init(){
        drawables = getCompoundDrawables();
    }

    public void setText(String text){
        if(text.equals(getText().toString()))
            return;
        super.setText(text);
        requestLayout();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        MyLog.Log("~~~", "");
        textWidth = getPaint().measureText(getText().toString());//测量文本宽度
        Drawable drawableLeft = drawables[0];//左侧图标
        int totalWidth = getWidth();
        if (null != drawableLeft) {
            int drawableWidth = drawableLeft.getIntrinsicWidth();
            int drawablePadding = getCompoundDrawablePadding();
            bodyWidth = textWidth + drawableWidth + drawablePadding;
            //setPadding((int)(totalWidth - bodyWidth),0,0,0);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        MyLog.Log("~~~", "");
        int width = getWidth();
        canvas.translate((width - bodyWidth)/2, 0);
        super.onDraw(canvas);
    }
}
