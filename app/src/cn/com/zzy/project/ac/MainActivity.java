package cn.com.zzy.project.ac;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import cn.com.zzy.multi.MyLog;
import cn.com.zzy.project.R;

public class MainActivity extends Activity {
    private LinearLayout main_ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //MyActivityManager.getInstance().addActivity(this);

        /*Intent intent = new Intent(this, ActivityPerson.class);
        startActivity(intent);*/

        main_ll = (LinearLayout) findViewById(R.id.main_ll);

        initDrawable();

        /*new Thread(new Runnable() {
            @Override
            public void run() {
                WSTest wsTest = new WSTest();
                wsTest.getRemoteInfo("13661101012", 5);
            }
        }).start();*/

/*        Test test = new Test();
        test.Encode();
        test.Decode();*/
    }

    private void initDrawable(){
        ImageView iv = (ImageView) findViewById(R.id.iv);

        String filePath = "/sdcard/ScreenSelect/100057.jpg";
        int width = (int)getResources().getDimension(R.dimen.iv_w);
        int height = (int)getResources().getDimension(R.dimen.iv_h);

//        iv.setImageDrawable(BitmapUtil.getFitSampleDrawable(filePath, width, height, getResources()));
//        iv.setImageBitmap(BitmapUtil.getFitSampleBitmap(filePath, width, height));

        //ContextCompat.getDrawable(this, R.drawable.weixin);
        Drawable drawable = new BitmapDrawable(getResources(), filePath);
        drawable.setBounds(500, 500, 500, 500);

        iv.setImageDrawable(drawable);

    }

    @Override //界面显示出来
    protected void onResume() {
        super.onResume();

        MyLog.Log("~~~", "height = " + main_ll.getHeight());//获取到高度值为0，由于还没有初始化完成
        //将一个runnable添加到Layout队列中：runnable对象中的方法会在View的measure、layout等事件后触发
        main_ll.post(new Runnable() {
            @Override
            public void run() {
                MyLog.Log("~~~", "height = " + main_ll.getHeight());//获取到高度
            }
        });

    }
}
