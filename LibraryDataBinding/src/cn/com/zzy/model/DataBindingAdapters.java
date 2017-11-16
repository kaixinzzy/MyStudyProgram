package cn.com.zzy.model;

import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.ref.WeakReference;

import cn.com.zzy.util.GlideApp;

/**
 * Created by BCLZzy on 2017/11/6.
 * 参考：http://blog.csdn.net/qiang_xi/article/details/75379321
 */

public class DataBindingAdapters {
    /**
     * 自定义ImageView不存在的属性
     *      requireAll=false代表我们在使用时，可以只使用其中一个属性，也可以三个属性都使用；
     *      如果requireAll=true，代表我们定义的这三个属性都是必须要使用的，不然就会报错。
     * @param imageView     显示图片的View
     * @param url           图片网络路径
     * @param placeHolder   图片占位Drawable
     * @param error         图片加载出错Drawable
     */
    @BindingAdapter(value = {"imageUrl", "placeHolder", "error"}, requireAll = false)
    public static void loadImage(ImageView imageView, String url, Drawable placeHolder, int error) {
        GlideApp.with(imageView.getContext())
                .load(url)
                .placeholder(placeHolder)
                .error(error)
                .into(imageView);
    }

    @BindingAdapter(value = {"imageUrl", "placeHolder", "error"}, requireAll = false)
    public static void loadImage(ImageView imageView, String url, int placeHolder, int error) {
        GlideApp.with(imageView.getContext())
                .load(url)
                .placeholder(placeHolder)
                .into(imageView);
    }

    @BindingAdapter({"delay"})
    public static void setVisibility(TextView textView, int delaySeconds) {
        if (delaySeconds < 0) {
            textView.setVisibility(View.GONE);
        } else if (delaySeconds == 0) {
            textView.setVisibility(View.VISIBLE);
        } else {
            textView.setVisibility(View.GONE);
            DelayShowTask dst = new DelayShowTask(textView);
            dst.execute(delaySeconds * 1000);
        }
    }

    @BindingAdapter({"delay"})
    public static void setVisibility(ImageView imageView, int delaySeconds) {
        if (delaySeconds < 0) {
            imageView.setVisibility(View.GONE);
        } else if (delaySeconds == 0) {
            imageView.setVisibility(View.VISIBLE);
        } else {
            imageView.setVisibility(View.GONE);
            DelayShowTask dst = new DelayShowTask(imageView);
            dst.execute(delaySeconds * 1000);
        }
    }

    @BindingAdapter({"delay"})
    public static void setVisibility(Button btn, int delaySeconds) {
        if (delaySeconds < 0) {
            btn.setVisibility(View.GONE);
        } else if (delaySeconds == 0) {
            btn.setVisibility(View.VISIBLE);
        } else {
            DelayShowTask dst = new DelayShowTask(btn);
            dst.execute(delaySeconds * 1000);
        }
    }

    private static class DelayShowTask extends AsyncTask<Integer, Integer, Void> {

        private final WeakReference<View> mView;

        DelayShowTask(View v) {
            mView = new WeakReference<View>(v);
        }

        @Override
        protected Void doInBackground(Integer... params) {
            try {
                Thread.sleep(params[0]);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            View view = mView.get();
            if (null != view) {
                view.setVisibility(View.VISIBLE);
            }
        }
    }

    @BindingAdapter("android:src")
    public static void setImageUri(ImageView view, String imageUri) {
        if (imageUri == null) {
            view.setImageURI(null);
        } else {
            view.setImageURI(Uri.parse(imageUri));
        }
    }

    @BindingAdapter("android:src")
    public static void setImageUri(ImageView view, Uri imageUri) {
        view.setImageURI(imageUri);
    }

    @BindingAdapter("android:src")
    public static void setImageDrawable(ImageView view, Drawable drawable) {
        view.setImageDrawable(drawable);
    }

    @BindingAdapter("android:src")
    public static void setImageResource(ImageView imageView, int resource) {
        imageView.setImageResource(resource);
    }

    @BindingAdapter("android:src")
    public static void loadImage(ImageView iv, Bitmap bitmap) {
        iv.setImageBitmap(bitmap);
    }

    @BindingAdapter("android:layout_width")
    public static void setLayoutWidth(View view, float width) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = (int) width;
        view.setLayoutParams(layoutParams);
    }

    @BindingAdapter("android:layout_height")
    public static void setLayoutHeight(View view, float height) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (int) height;
        view.setLayoutParams(layoutParams);
    }

    @BindingAdapter("android:layout_marginLeft")
    public static void setLeftMargin(View view, float leftMargin) {
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        layoutParams.setMargins(Math.round(leftMargin), layoutParams.topMargin,
                layoutParams.rightMargin, layoutParams.bottomMargin);
        view.setLayoutParams(layoutParams);
    }

    @BindingAdapter("android:layout_marginStart")
    public static void setStartMargin(View view, float startMargin) {
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        layoutParams.setMargins(Math.round(startMargin), layoutParams.topMargin,
                layoutParams.rightMargin, layoutParams.bottomMargin);
        view.setLayoutParams(layoutParams);
    }

    @BindingAdapter("android:layout_marginTop")
    public static void setTopMargin(View view, float topMargin) {
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        layoutParams.setMargins(layoutParams.leftMargin, Math.round(topMargin),
                layoutParams.rightMargin, layoutParams.bottomMargin);
        view.setLayoutParams(layoutParams);
    }

    @BindingAdapter("android:layout_marginRight")
    public static void setRightMargin(View view, float rightMargin) {
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        layoutParams.setMargins(layoutParams.leftMargin, layoutParams.topMargin,
                Math.round(rightMargin), layoutParams.bottomMargin);
        view.setLayoutParams(layoutParams);
    }

    @BindingAdapter("android:layout_marginEnd")
    public static void setEndMargin(View view, float endMargin) {
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        layoutParams.setMargins(layoutParams.leftMargin, layoutParams.topMargin,
                Math.round(endMargin), layoutParams.bottomMargin);
        view.setLayoutParams(layoutParams);
    }

    @BindingAdapter("android:layout_marginBottom")
    public static void setBottomMargin(View view, float bottomMargin) {
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        layoutParams.setMargins(layoutParams.leftMargin, layoutParams.topMargin,
                layoutParams.rightMargin, Math.round(bottomMargin));
        view.setLayoutParams(layoutParams);
    }

    @BindingAdapter("android:background")
    public static void setBackground(View view, Drawable drawable) {
        view.setBackground(drawable);
    }

    @BindingAdapter("android:background")
    public static void setBackground(View view, int drawableId) {
        view.setBackgroundResource(drawableId);
    }

    @BindingAdapter("android:drawableBottom")
    public static void setBottomDrawable(TextView tv, Drawable drawable) {
        Drawable[] drawables = tv.getCompoundDrawables();
        if (null != drawable && drawable.getBounds().isEmpty()) {
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight
                    ());
        }
        drawables[3] = drawable;
        tv.setCompoundDrawables(drawables[0], drawables[1], drawables[2],
                drawables[3]);
    }



}
