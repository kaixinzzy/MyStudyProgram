package cn.com.zzy.libservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import cn.com.zzy.multi.MyLog;

/**
 * Created by BCLZzy on 2017/8/10.
 * 启动方式：
       Intent startIntent = new Intent(this, StartService.class);
       startService(startIntent);
 */

public class StartService extends Service {

    /*
     onDestroy前，只会调用一次
    */
    @Override
    public void onCreate() {
        super.onCreate();
        MyLog.Log("~~~", "");
    }

    /*
      每次startService都会被调用
    */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        MyLog.Log("~~~", "");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MyLog.Log("~~~", "");
    }

    @Override
    public IBinder onBind(Intent intent) {
        MyLog.Log("~~~", "");
        return null;
    }
}
