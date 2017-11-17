package cn.com.zzy;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import cn.com.zzy.glide.R;

/**
 * Glide
 * 权限：
 *  <uses-permission android:name="android.permission.INTERNET" />
 */
public class GlideActivity extends Activity {
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);
        imageView = (ImageView) findViewById(R.id.image_view);
    }

    private void loadImageGif(String gifUrl){
        Glide.with(this)
                //如果加载的是gif图片，Glide会自动识别，并显示gif图片
                .load(gifUrl)
                .placeholder(R.drawable.weixin)
                .error(R.drawable.baifubao)
                .into(imageView);

        Glide.with(this)
                .load(gifUrl)
                //关闭自动识别图片功能，执行加载图片类型为Bitmap
                //如果加载的图片是gif，会取该gif的第一帧图
                .asBitmap()
                .placeholder(R.drawable.weixin)
                .error(R.drawable.baifubao)
                .into(imageView);

        Glide.with(this)
                .load(gifUrl)
                //关闭自动识别图片功能，执行加载图片类型为Gif
                //如果加载的图片不是gif，会显示error中设置的图片
                .asGif()
                .placeholder(R.drawable.weixin)
                .error(R.drawable.baifubao)
                .into(imageView);

    }

    public void loadImage(View view) {
        String url = "http://cn.bing.com/az/hprichbg/rb/Dongdaemun_ZH-CN10736487148_1920x1080.jpg";

        Glide
                //可以接收Context、Activity或者Fragment类型的参数,【Glide加载图片的生命周期】
                //如果出入的是Activity、Fragment，传入的Activity、Fragment销毁时，图片加载也会停止。
                .with(this)
                //要加载的图片，支持的类型有：File、resource 、Uri对象、二进制流
                .load(url)
                //占位图，支持resource、Drawable
                .placeholder(R.drawable.weixin)
                //异常占位图，支持resource、Drawable
                .error(R.drawable.baifubao)
                //指定加载图片的大小
                //其实Glide会加载与imageView一样大小图片，平时我们不用在这里设置大小的。
                .override(100, 100)
                //禁止缓存功能
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                //图片的载体
                .into(imageView);
    }

}
