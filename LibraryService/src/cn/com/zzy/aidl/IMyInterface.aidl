// IMyInterface.aidl
package cn.com.zzy.aidl;

import cn.com.zzy.aidl.SerialMessage;
import cn.com.zzy.aidl.IMyListener;

/**
 * IBinder
 * Client绑定服务后，会得到服务端的IBinder；
 * Client通过IBinder，来调用服务的方法。
 */
interface IMyInterface {

    void add(in SerialMessage serialMessage);//增加一条消息
    List<SerialMessage> get(int i);//获取所有消息

    void registerListener(IMyListener listener);//注册回调接口
    void unregisterListener(IMyListener listener);//注销回调接口

}
