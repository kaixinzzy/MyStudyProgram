// IMyListener.aidl
package cn.com.zzy.aidl;

import cn.com.zzy.aidl.SerialMessage;

/**
 * CallBack回调接口
 * 服务端通过注册回调接口，可以调用Client端实现IMyListener的newSerialMessageNotify方法
 */
interface IMyListener {

    //售货机消息通知
    void newSerialMessageNotify(in SerialMessage serialMessage);

}
