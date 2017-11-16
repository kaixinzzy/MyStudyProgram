package cn.com.zzy.ac;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import cn.com.zzy.QRUtil;
import cn.com.zzy.R;

public class ZxingActivity extends Activity {
    private static final String TAG = ZxingActivity.class.getSimpleName();
    private static final String QRString = "http://myznj.shmydrink.com/AutomatSys?device=Bcl03&SN=525865258652586525865258652586525865258";
    private ImageView iv1,iv2,iv3,iv4;
    private Bitmap bitmap1,bitmap2,bitmap3,bitmap4,bitmapBarCode;
    private int width = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zxing);
        initView();

/*        try {
            bitmap1 = QRUtils.makeColorQRImage(QRString, width);
//            bitmap2 = QRUtils.encodeToQRWidth(QRString,(int) width);
            bitmap2 = (new QRUtil()).getQRCode(QRString, width, width, null);


            if (null != iv1 && null != bitmap1){
                iv1.setImageBitmap(bitmap1);
                iv2.setImageBitmap(bitmap2);

                Bitmap bitmapRight = QRUtils.gainBitmap(this, R.drawable.weixin);
                bitmap3 = QRUtils.composeWatermark(bitmap2, bitmapRight);
                iv3.setImageBitmap(bitmap2);

                bitmap4 = QRUtils.encodeToQRWithLogo(QRString, width, bitmapRight);
                iv4.setImageBitmap(bitmap4);

                bitmapBarCode = BarCode.creatBarcode(this, QRString, 100, 50, false);
                iv1.setImageBitmap(bitmapBarCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        Resources resources = getResources();
        Bitmap icon = BitmapFactory.decodeResource(resources, R.drawable.weixin);

        QRUtil qrUtil = new QRUtil();
        qrUtil.setMagin(0);
//        qrUtil.setBackgroudColor(resources.getColor(R.color.black));
//        qrUtil.setQRColor(resources.getColor(R.color.blue));

        bitmap3 = qrUtil.getQRCode(QRString, width, width, null);
        iv3.setImageBitmap(bitmap3);

        qrUtil.setMagin(0);
        bitmap4 = qrUtil.getQRCode(QRString, width, width, icon);
        iv4.setImageBitmap(bitmap4);

    }

    private void initView(){
        iv1 = (ImageView) findViewById(R.id.iv1);
        iv2 = (ImageView) findViewById(R.id.iv2);
        iv3 = (ImageView) findViewById(R.id.iv3);
        iv4 = (ImageView) findViewById(R.id.iv4);

        width = (int) getResources().getDimension(R.dimen.width);
        getResources().getColor(R.color.white);
    }
}
