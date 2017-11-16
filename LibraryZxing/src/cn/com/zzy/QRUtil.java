package cn.com.zzy;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.util.Log;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.util.Hashtable;

/**
 * Created by BCLZzy on 2017/8/21.
 * 二维码工具类 -- 由BCL项目代码修改而来
 */

public class QRUtil {
    private static final String TAG = QRUtil.class.getSimpleName();

    // 图片宽度的一般
    private int IMAGE_HALFWIDTH = 32;
    private int width=0;
    private int height=0;

    private Bitmap icon;//二维码中心的图标
    private String url;//需要生成二维码的字符串

    private int FOREGROUND_COLOR = Color.BLACK;//二维码的颜色    默认黑色
    private int mBackgroudColor = Color.WHITE;//二维码的背景色  默认白色
    private int mMagin = 1;//二维码离边缘的距离

    /**
     * 设置二维码离边缘的距离
     * 注：
     *     关于二维码边距，为什么我们设置成0，还是显示白边。
     *     当我们设置margin=0时，内容区为29*29而我们需要的大小是200 * 200显然zxing对图片的尺寸进行了缩放，
     *     那么其缩放倍数为多少呢？没错就是一个int型的数据，打印后发现multiple=6，
     *     也就是zxing会将29*29的图片放大6倍变成174*174没错比200少了26的像素，这26的像素就是二维码的白边了........
     *     所以设置margin=0后没有效果。。。
     *     更多可参考http://blog.csdn.net/niuzhucedenglu/article/details/49739365
     * @param magin
     */
    public void setMagin(int magin){
        this.mMagin = magin;
    }

    /**
     * 设置背景颜色
     * @param backgroudColor
     */
    public void setBackgroudColor(int backgroudColor){
        this.mBackgroudColor = backgroudColor;
    }

    /**
     * 二维码的颜色
     * @param mQRColor
     */
    public void setQRColor(int mQRColor){
        this.FOREGROUND_COLOR = mQRColor;
    }


    /**
     * 生成二维码
     * @param url    生成二维码的字符串
     * @param width  宽度
     * @param height 高度
     * @param icon   中心图标，没有写null
     * @return Bitmap
     */
    public Bitmap getQRCode(String url, int width, int height, Bitmap icon) {
        this.width=width;
        this.height=height;
        this.icon=icon;
        this.url=url;
        Bitmap bitmap=null;
        BitMatrix matrix = getBitMatrix(url);
        if(null == matrix){
            return null;
        }
        try {
            if(icon != null){
                bitmap = CreatBitmap(matrix, icon);
            }else{
                bitmap = bitMatrix2Bitmap(matrix);
            }
        } catch (WriterException e) {
            Log.i(TAG, e.getMessage());
        }
        return bitmap;
    }

    //生成无图标二维码
    private Bitmap bitMatrix2Bitmap(BitMatrix matrix) {
        int w = matrix.getWidth();
        int h = matrix.getHeight();
        int[] rawData = new int[w * h];
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                int color = mBackgroudColor;
                if (matrix.get(i, j)) {
                    color = FOREGROUND_COLOR;
                }
                rawData[i + (j * w)] = color;
            }
        }

        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.RGB_565);//RGB_565
        bitmap.setPixels(rawData, 0, w, 0, 0, w, h);
        return bitmap;
    }

    //取得二维码矩阵
    private BitMatrix getBitMatrix(String content){
        try {
            Hashtable<EncodeHintType, Object> hints=new Hashtable<EncodeHintType, Object>();
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);//二维码容错率，分四个等级：H、L 、M、 Q
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            hints.put(EncodeHintType.MARGIN, mMagin);
            hints.put(EncodeHintType.MIN_SIZE, width);
            hints.put(EncodeHintType.MAX_SIZE, width);
            MultiFormatWriter writer = new MultiFormatWriter();
            // 生成二维矩阵,编码时指定大小,不要生成了图片以后再进行缩放,这样会模糊导致识别失败
            BitMatrix matrix = writer.encode(content,
                    BarcodeFormat.QR_CODE, width, height, hints);
            return matrix;
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }

    //生成带图标二维码
    private Bitmap CreatBitmap(BitMatrix matrix, Bitmap icon) throws WriterException{
        icon=zoomBitmap(icon, IMAGE_HALFWIDTH);
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        // 二维矩阵转为一维像素数组,也就是一直横着排了
        int halfW = width / 2;
        int halfH = height / 2;
        int[] pixels = new int[width * height];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (x > halfW - IMAGE_HALFWIDTH && x < halfW + IMAGE_HALFWIDTH
                        && y > halfH - IMAGE_HALFWIDTH
                        && y < halfH + IMAGE_HALFWIDTH) {
                    int iconPixel = icon.getPixel(x - halfW
                            + IMAGE_HALFWIDTH, y - halfH + IMAGE_HALFWIDTH);
                    if (((iconPixel & 0xff000000) >> 24) == 0) {
                        //若像素为透明,则设置为白色
                        pixels[y * width + x] = mBackgroudColor;
                    } else {
                        pixels[y * width + x] = iconPixel;
                    }
                } else {
                    if (matrix.get(x, y)) {
                        pixels[y * width + x] = FOREGROUND_COLOR;
                    } else { // 无信息设置像素点为白色
                        pixels[y * width + x] = mBackgroudColor;
                    }
                }
            }
        }
        Bitmap bitmap_icon = Bitmap.createBitmap(width, height,
                Bitmap.Config.ARGB_8888);
        // 通过像素数组生成bitmap
        Log.i(TAG,width+","+height);
        bitmap_icon.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap_icon;
    }

    //图片缩放
    private Bitmap zoomBitmap(Bitmap icon,int h){
        // 缩放图片
        Matrix m = new Matrix();
        float sx = (float) 2 * h / icon.getWidth();
        float sy = (float) 2 * h / icon.getHeight();
        //使icon缩放为二维码的三分之一
        /*float sx = (float) this.width/(3*icon.getWidth());
        float sy = (float) this.height/(3*icon.getHeight());*/
        m.setScale(sx, sy);
        // 重新构造一个2h*2h的图片
        return Bitmap.createBitmap(icon, 0, 0,icon.getWidth(), icon.getHeight(), m, false);
    }

    /*private Bitmap generateQRCode(String content, int width, int height) {
        try {
            QRCodeWriter writer = new QRCodeWriter();
            BitMatrix matrix = writer.encode(content, BarcodeFormat.QR_CODE,
                    width, height);
            return bitMatrix2Bitmap(matrix);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }*/

}
