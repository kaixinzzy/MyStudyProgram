package cn.com.zzy.libservice.test.ac;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;

import cn.com.zzy.aidl.IMyInterface;
import cn.com.zzy.aidl.IMyListener;
import cn.com.zzy.aidl.SerialMessage;
import cn.com.zzy.libservice.BindServiceAIDL;
import cn.com.zzy.libservice.R;
import cn.com.zzy.multi.MyLog;

public class BindServiceAIDLActivity extends Activity {
    private static final String TAG = BindServiceAIDLActivity.class.getSimpleName();

    private IMyInterface myBinderAIDL;//数据传递桥梁
    private MyHandler myHandler;

    private static class MyHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    SerialMessage serialMessage = (SerialMessage) msg.obj;
                    MyLog.Log("~~~", "Client getNotifyMessage " + serialMessage.getFlag());
                    break;
            }
        }
    }

    private IMyListener listener = new IMyListener.Stub(){
        //此方法会被Service端调用(回调方法)
        @Override
        public void newSerialMessageNotify(SerialMessage serialMessage) throws RemoteException {
            MyLog.Log("~~~", "listener Thread id ");
            Message message = Message.obtain();
            message.what = 0;
            message.obj = serialMessage;
            myHandler.handleMessage(message);
        }
    };

    private ServiceConnection conn = new ServiceConnection() {

        @Override   //意外断开连接
        public void onServiceDisconnected(ComponentName name) {
            MyLog.Log("~~~", "bindServiceAIDL 连接失败");
            myBinderAIDL = null;
            onBindService();
        }

        @Override   //连接成功
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyLog.Log("~~~", "bindServiceAIDL 连接成功");
            myBinderAIDL = IMyInterface.Stub.asInterface(service);
            registerListener();//注册回调接口

        }
    };

    //注册回调接口 and 销毁回调接口------------------------------------------------------------------------------------------------------

    private void registerListener() {
        try {
            myBinderAIDL.registerListener(listener);//设置回调
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void unregisterListener() {
        if (myBinderAIDL != null && myBinderAIDL.asBinder().isBinderAlive()) {
            try {
                myBinderAIDL.unregisterListener(listener);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    //绑定服务 and 解除绑定服务----------------------------------------------------------------------------------------------------------

    private void onBindService() {
        Intent bindIntent = new Intent(getApplicationContext(), BindServiceAIDL.class);
        getApplicationContext().bindService(bindIntent, conn, BIND_AUTO_CREATE);
    }

    private void onUnBindeSsercie(){
        try{
            getApplicationContext().unbindService(conn);
        } catch (Exception e) {
            MyLog.Log("~~~", "当前服务非处于未绑定状态，请勿执行解绑操作");
        }
    }

    // Activity 生命周期----------------------------------------------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_service_aidl);

        myHandler = new MyHandler();

        onBindService();//绑定服务

        if (myBinderAIDL != null) {
            try {
                myBinderAIDL.get(0);
            } catch (RemoteException e) {
                //调用binder接口，需要随时捕获异常，因为可能与服务的连接中断。
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onDestroy() {
        unregisterListener();//销毁回调接口
        onUnBindeSsercie();//解除绑定服务
        super.onDestroy();
    }
}
