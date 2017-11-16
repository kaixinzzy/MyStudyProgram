package cn.com.zzy.multi.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static android.graphics.BitmapFactory.decodeFile;

/**
 * Created by BCLZzy on 2017/10/18.
 * 图片处理工具
 */

public class BitmapUtil {

    /**
     * 缩放图片【通过filePath】  将图片压缩尽量小，比例不限制
     * @param filePath 图片路径
     * @param width    显示图片View的宽度
     * @param height   显示图片View的高度
     * @return
     */
    public static Drawable getFitSampleDrawable(String filePath, int width, int height, Resources r){
        BitmapFactory.Options options = new BitmapFactory.Options();
        //将BitmapFactory.Options的inJustDecodeBounds设为true并加载图片，此时只是解析图片原始宽高，并不会真正加载，所以这个操作是轻量级的。
        options.inJustDecodeBounds = true;
        decodeFile(filePath, options);
        final int iHeight = options.outHeight;
        final int iWidth = options.outWidth;

        final float heightRatio = (float) height / (float) iHeight;
        final float widthRatio  = (float) width / (float) iWidth;

        final float ratio = heightRatio > widthRatio ? heightRatio : widthRatio;

        final int newHeight = (int)Math.floor((float)height/ratio);
        final int newWidth  = (int)Math.floor((float)width/ratio);

        options.inSampleSize = 1;
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeFile(filePath, options);

        //Drawable d = Drawable.createFromPath(filePath);
        //Drawable d = new BitmapDrawable(filePath);
        Drawable d = new BitmapDrawable(r, bitmap);
        d.setBounds(0,0,newWidth,newHeight);

        return d;
    }

    /**
     * 缩放图片【通过filePath】  压缩比例收到限制，是一般是2的指数，即1、2、4、8、16……
     * @param filePath 图片路径
     * @param width    显示图片View的宽度
     * @param height   显示图片View的高度
     * @return
     */
    public static Bitmap getFitSampleBitmap(String filePath, int width, int height){
        BitmapFactory.Options options = new BitmapFactory.Options();
        //将BitmapFactory.Options的inJustDecodeBounds设为true并加载图片，此时只是解析图片原始宽高，并不会真正加载，所以这个操作是轻量级的。
        options.inJustDecodeBounds = true;
        decodeFile(filePath, options);
        //从BitmapFactory.Options中取出图片原始宽高，结合目标view大小计算采样率
        options.inSampleSize = getFitInSampleSize(width, height, options);
        //将BitmapFactory.Options的inJustDecodeBounds设为false并真正加载图片
        options.inJustDecodeBounds = false;
        return decodeFile(filePath, options);
    }

    /**
     * 缩放图片【通过Resources】
     * @param resources 图片资源
     * @param resId     资源id
     * @param width     显示图片View的宽度
     * @param height    显示图片View的高度
     * @return
     */
    public static Bitmap getFitSampleBitmap(Resources resources, int resId, int width, int height) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resources, resId, options);
        options.inSampleSize = getFitInSampleSize(width, height, options);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(resources, resId, options);
    }

    /**
     * 缩放图片【通过inputStream】
     * 当前做法是：将inputStream的字节流读取到一个文件里，然后通过处理file的方式来进行处理即可。
     *
     * 原来考虑用BitmapFactory.decodeStream【已经舍弃】
     * 但是decodeStream跟上面的BitmapFactory.decodeFile、BitmapFactory.decodeResource不同，
     * 因对stream是一种有顺序的字符流，对其decode一次后，其顺序就会发生变化，再次进行第二次decode的时候就不能解码成功了，
     * 这也是为什么当我们对inputStream decode两次的时候会得到一个null值的bitmap的原因。
     *
     * @param inputStream   字节流
     * @param catchFilePath 缓存到本地的路径
     * @param width         显示图片View的宽度
     * @param height        显示图片View的高度
     * @return
     * @throws Exception
     */
    public static Bitmap getFitSampleBitmap(InputStream inputStream, String catchFilePath, int width, int height) throws Exception {
        return getFitSampleBitmap(catchStreamToFile(catchFilePath, inputStream), width, height);
    }

    /**
     * 将inputStream中字节流保存至文件
     * @param catchFile
     * @param inStream
     * @return
     * @throws Exception
     */
    public static String catchStreamToFile(String catchFile,InputStream inStream) throws Exception {
        File tempFile=new File(catchFile);
        try {
            if (tempFile.exists()) {
                tempFile.delete();
            }
            tempFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileOutputStream fileOutputStream=new FileOutputStream(tempFile);
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            fileOutputStream.write(buffer, 0, len);
        }
        inStream.close();
        fileOutputStream.close();
        return catchFile;
    }

    /**
     * 从BitmapFactory.Options中取出图片原始宽高，结合目标view大小计算采样率
     * 采样率为1时即原始大小，为2时，宽高均为原来的1/2，像素数和占用内存数均为原来的1/4.
     * 采样率一般是2的指数，即1、2、4、8、16……
     * @param width
     * @param height
     * @param options
     * @return
     */
    public static int getFitInSampleSize(int width, int height, BitmapFactory.Options options) {
        int inSampleSize = 1;
        if (options.outWidth > width || options.outHeight > height){
            int widthRatio = Math.round((float) options.outWidth / (float) width);
            int heightRatio = Math.round((float) options.outHeight / (float) height);
            inSampleSize = Math.min(widthRatio, heightRatio);
        }

        return inSampleSize;
    }

    public static Bitmap drawable2Bitmap(Drawable drawable){
        if (drawable instanceof BitmapDrawable){
            //BitmapDrawable是Drawable的子类，可以直接获取Bitmap
            return ((BitmapDrawable)drawable).getBitmap();
        } else if (drawable instanceof NinePatchDrawable){
            // 取 drawable 的长宽
            int width = drawable.getIntrinsicWidth();
            int height = drawable.getIntrinsicHeight();
            // 取 drawable 的颜色格式
            Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565;
            // 建立对应 bitmap
            Bitmap bitmap = Bitmap.createBitmap(width, height, config);
            // 建立对应 bitmap 的画布
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, width, height);
            // 把 drawable 内容画到画布中
            drawable.draw(canvas);
            return bitmap;
        } else {
            return null;
        }
    }



































}
