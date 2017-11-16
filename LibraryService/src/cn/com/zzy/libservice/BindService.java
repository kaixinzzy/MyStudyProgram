package cn.com.zzy.libservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import cn.com.zzy.multi.MyLog;

/**
 * Created by BCLZzy on 2017/8/10.
 * 启动方式：
       Intent startIntent = new Intent(this, StartService.class);
       startService(startIntent);
 */

public class BindService extends Service {


    @Override   //onDestroy前，只会调用一次
    public void onCreate() {MyLog.Log("~~~", "");
        super.onCreate();
    }


    @Override   //每次startService都会被调用
    public int onStartCommand(Intent intent, int flags, int startId) {MyLog.Log("~~~", "");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {MyLog.Log("~~~", "");
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {MyLog.Log("~~~", "");
        return mBinder;
    }

    private MyBinder mBinder = new MyBinder();
    public class MyBinder extends Binder {

        public void startDownload() {
            MyLog.Log("~~~", "执行具体的下载任务");
        }

    }

}
