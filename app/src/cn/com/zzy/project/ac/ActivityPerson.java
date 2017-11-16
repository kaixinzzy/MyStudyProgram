package cn.com.zzy.project.ac;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;

import cn.com.zzy.multi.MyLog;
import cn.com.zzy.project.R;

public class ActivityPerson extends Activity {
    private static final String TAG = "ActivityPerson";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);

        //倒计时长10秒  间隔时间1秒
        new CountDownTimer(50 * 1000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {//间隔调用
                MyLog.Log("~~~", "----------" + millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {// 倒计时完成
                MyLog.Log("~~~", "---------- 倒计时完成");
            }
        }.start();

        //倒计时长3秒  间隔时间1秒
        new CountDownTimer(6 * 1000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {//间隔调用
                MyLog.Log("~~~", "" + millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {// 倒计时完成
                MyLog.Log("~~~", "倒计时完成 finish当前Activity");
                finish();
            }
        }.start();
    }

    @Override
    protected void onDestroy() {
        MyLog.Log("~~~", "onDestroy");
        super.onDestroy();
        System.gc();
    }
}
