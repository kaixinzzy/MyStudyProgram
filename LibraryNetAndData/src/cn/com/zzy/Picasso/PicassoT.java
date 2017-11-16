package cn.com.zzy.Picasso;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by BCLZzy on 2017/10/30.
 */

public class PicassoT {
    private Context mContext;

    public PicassoT(Context context){
        mContext = context.getApplicationContext();
    }

    public void init(ImageView imageView, String filePath, int resId){

        Picasso.with()
                .load(filePath)
                .into(imageView);

        Picasso.with()
                .load(filePath)
                .resize(50, 50)     //重新设置大小
                .centerCrop()       //图片显示风格
                .into(imageView);

        Picasso.with()
                .load(filePath)
                .placeholder(resId) //占位图片
                .error(resId)       //加载图片出错
                .into(imageView);

    }

}
