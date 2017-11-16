package cn.com.zzy.Glide;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by BCLZzy on 2017/10/31.
 * 请参考：http://blog.csdn.net/hexingen/article/details/72579529
 */

public class GlideT {
    private Context mContext;

    public GlideT(Context context){
        mContext = context.getApplicationContext();
    }

    public void init(String url, ImageView imageView){
        //加载一张图片
        Glide.with(mContext)
                .load(url)
                .into(imageView);

        //取消加载图片（不太需要，当Activity或Fragment实例销毁时，Glide会自动取消加载并回收资源）
        Glide.with(mContext).clear(imageView);
    }

}
