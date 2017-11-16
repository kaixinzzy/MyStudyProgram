package cn.com.zzy.project;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

import cn.com.zzy.multi.CrashHandler;

/**
 * Created by BCLZzy on 2017/8/11.
 * 自定义Application需要在AndroidManifest.xml中配置
 * 应用初始化设置管理
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initLeakCanary();//初始化Activity内存泄漏检测工具LeakCanary

        CrashHandler.getInstance().init(this);//异常捕获
    }

    //初始化Activity内存泄漏检测工具LeakCanary
    private void initLeakCanary(){
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }
}
