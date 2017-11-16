package cn.com.zzy.libservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import cn.com.zzy.aidl.IMyInterface;
import cn.com.zzy.multi.MyLog;

/**
 * Created by BCLZzy on 2017/8/10.
 * 跨进成访问AIDL
 * 启动方式：
       Intent startIntent = new Intent(this, StartService.class);
       startService(startIntent);
 */

public class BindServiceAIDL extends Service {


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

    private IBinder mBinder = new IMyInterface.Stub() {
        @Override
        public String getInfor(String s) throws RemoteException {
            MyLog.Log("~~~", "Activity 传递给 Service的字符串是：" + s);
            return "I'm Service";
        }
    };

}
