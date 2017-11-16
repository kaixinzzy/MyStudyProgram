package cn.com.zzy;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import cn.com.zzy.glide.R;
import cn.com.zzy.util.GlideApp;

/**
 * 注意：
 *      1、在listView或者RecyclerView中使用Glide框架，内存剧增或者爆内存溢出（OutOfMemoryError）
 *          原因：在ImageView中scaleType使用了fitxy属性
 *          解决：将fitXY改动成 fitCenter 或 centerCrop
 */
public class GlideActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);


    }

    /**
     * 取消显示操作
     * 当Activity或Fragment实例销毁时，Glide会自动取消加载并回收资源
     * 回收资源时机由传入的Context决定，
     * 比如传入Fragment的Context，当该Fragment销毁时，会回收资源
     */
    private void clearImageView(ImageView imageView){
        //取消加载图片 or 清空已加载到ImageView上的图片
        Glide.with(this).clear(imageView);
    }

    /**
     * 加载本地图片，这里是mipmap文件夹下的资源
     * @param res R.drawable.ic_launcher
     */
    private void loadLocalImage(ImageView imageView, int res) {
        RequestBuilder<Drawable> drawableRequestBuilder = GlideApp.with(this).load(res);
        drawableRequestBuilder.into(imageView);
    }

    /**
     * 从远程网路上加载图片
     */
    private void loadRemoteImage(ImageView imageView, String url, int resPlaceHoder, int resError, int resFallBack) {
        GlideApp.with(this).asBitmap()      //指定Bitmap类型的RequestBuilder
                .load(url)                  //根据地址加载网络图片
                .placeholder(resPlaceHoder) //占位图片
                .error(resError)            //异常图片
                .fallback(resFallBack)      //当url为空时，回调显示的图片
                .into(imageView);
    }

    /**
     * 预先将网络资源加载到本地
     */
    private void startPreload(String url) {
        GlideApp.with(this).asBitmap()
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .preload();
    }

    /**
     * 预先下载原始图片资源后，本地加载
     */
    private void loadPreloadImage(ImageView imageView, String url) {
        GlideApp.with(this).asBitmap()
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }



}
