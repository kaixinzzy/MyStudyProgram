package cn.com.zzy.libservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import cn.com.zzy.aidl.IMyInterface;
import cn.com.zzy.aidl.IMyListener;
import cn.com.zzy.aidl.SerialMessage;
import cn.com.zzy.multi.MyLog;

/**
 * Created by BCLZzy on 2017/8/10.
 * 跨进成访问AIDL，适用于并发操作。
 * 1、启动方式：
 * Intent startIntent = new Intent(this, StartService.class);
 * startService(startIntent);
 * 2、配置AIDL服务的AndroidManifest.xml：
 * <service android:name=".BindServiceAIDL" android:process="com.zzy.aidl"/>
 */

public class BindServiceAIDL extends Service {

    private CopyOnWriteArrayList<SerialMessage> serialMessages = new CopyOnWriteArrayList<>();
    private RemoteCallbackList<IMyListener> callbackList = new RemoteCallbackList<>();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        MyLog.Log("~~~", "");
        return mBinder;
    }

    private IBinder mBinder = new IMyInterface.Stub() {

        @Override
        public void add(SerialMessage serialMessage) throws RemoteException {
            serialMessages.add(serialMessage);
        }

        @Override
        public List<SerialMessage> get(int i) throws RemoteException {
            return serialMessages;
        }

        @Override
        public void registerListener(IMyListener listener) throws RemoteException {
            callbackList.register(listener);
        }

        @Override
        public void unregisterListener(IMyListener listener) throws RemoteException {
            callbackList.unregister(listener);
        }
    };

    //开启线程，模拟售货机每5秒发送一条消息给Client端------------------------------------------------------------------------------------

    private AtomicBoolean mIsServiceDestoryed = new AtomicBoolean(false);

    @Override
    public void onCreate() {
        super.onCreate();
        new Thread(new runnable()).start();
    }

    private void onNewSerialMessage(SerialMessage serialMessage) {
        final int count = callbackList.beginBroadcast();
        for (int i = 0; i < count; i++) {
            IMyListener listener = callbackList.getBroadcastItem(i);
            if (listener != null) {
                try {
                    listener.newSerialMessageNotify(serialMessage);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
        callbackList.finishBroadcast();
    }

    private class runnable implements Runnable {
        private int index = 0;

        @Override
        public void run() {
            while (!mIsServiceDestoryed.get()) {
                try {
                    Thread.sleep(5*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                SerialMessage serialMessage = new SerialMessage();
                serialMessage.setFlag(index++);
                onNewSerialMessage(serialMessage);
            }
        }
    }


}
