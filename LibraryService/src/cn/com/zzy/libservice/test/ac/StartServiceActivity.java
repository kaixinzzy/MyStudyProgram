package cn.com.zzy.libservice.test.ac;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;

import cn.com.zzy.aidl.IMyInterface;
import cn.com.zzy.libservice.BindService;
import cn.com.zzy.libservice.BindServiceAIDL;
import cn.com.zzy.libservice.R;
import cn.com.zzy.libservice.StartService;
import cn.com.zzy.multi.MyActivityManager;
import cn.com.zzy.multi.MyLog;

public class StartServiceActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_service);
        MyActivityManager.getInstance().addActivity(this);

    }
    /****************************************** StartService *************************************************/
    //启动服务
    public void onClickStartService(View view) {
        Intent startIntent = new Intent(getApplicationContext(), StartService.class);
        getApplicationContext().startService(startIntent);
    }
    //停止服务
    public void onClickStopService(View view) {
        Intent stopIntent = new Intent(getApplicationContext(), StartService.class);
        getApplicationContext().stopService(stopIntent);
    }
    /****************************************** BindService *************************************************/
    private BindService.MyBinder myBinder;//数据传递桥梁
    private ServiceConnection connection = new ServiceConnection() {

        @Override   //绑定失败
        public void onServiceDisconnected(ComponentName name) {MyLog.Log("~~~", "bindService 连接失败");
        }

        @Override   //绑定成功
        public void onServiceConnected(ComponentName name, IBinder service) {MyLog.Log("~~~", "bindService 连接成功");
            myBinder = (BindService.MyBinder) service;
            myBinder.startDownload();
        }
    };
    //绑定服务
    public void onClickBindService(View view) {
        Intent bindIntent = new Intent(getApplicationContext(), BindService.class);
        getApplicationContext().bindService(bindIntent, connection, BIND_AUTO_CREATE);
    }
    //解除绑定服务
    public void onClickUnbindService(View view) {
        try{
            getApplicationContext().unbindService(connection);
        } catch (Exception e) {
            MyLog.Log("~~~", "bindService 当前非处于未绑定状态，请勿执行解绑操作");
        }
    }
    /****************************************** BindService AIDL *************************************************/
    private IMyInterface myBinderAIDL;//数据传递桥梁
    private ServiceConnection connectionAIDL = new ServiceConnection() {

        @Override   //绑定失败
        public void onServiceDisconnected(ComponentName name) {MyLog.Log("~~~", "bindServiceAIDL 连接失败");
        }

        @Override   //绑定成功
        public void onServiceConnected(ComponentName name, IBinder service) {MyLog.Log("~~~", "bindServiceAIDL 连接成功");
            myBinderAIDL = IMyInterface.Stub.asInterface(service);
            try {
                String s = myBinderAIDL.getInfor("I'm Activity");
                MyLog.Log("~~~", "Service 返回给 Activity 的字符串是：" + s);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    };
    //绑定服务
    public void onClickBindServiceAIDL(View view) {
        Intent bindIntent = new Intent(getApplicationContext(), BindServiceAIDL.class);
        getApplicationContext().bindService(bindIntent, connectionAIDL, BIND_AUTO_CREATE);
    }
    //解除绑定服务
    public void onClickUnbindServiceAIDL(View view) {
        try{
            getApplicationContext().unbindService(connectionAIDL);
        } catch (Exception e) {
            MyLog.Log("~~~", "bindServiceAIDL 当前非处于未绑定状态，请勿执行解绑操作");
        }
    }
}
