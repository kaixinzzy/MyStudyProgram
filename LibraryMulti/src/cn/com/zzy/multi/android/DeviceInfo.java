package cn.com.zzy.multi.android;

import android.content.Context;
import android.os.Build;
import android.os.Environment;

/**
 * Created by BCLZzy on 2017/9/18.
 * 功能：设备信息
 */

public class DeviceInfo {

    public Context mContext;

    //系统版本号 如：19（代表Android4.4） Build.VERSION_CODES.KITKAT值为19
    public static final int CURRENT_VERSION = android.os.Build.VERSION.SDK_INT;
    public static final String SYS_APP_PATH = ((android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) ? "/system/app" : "/system/priv-app");

    /************************************************************************************
     公用SD卡  /mnt/sdcard/
     ************************************************************************************/
    public static final String SDCARD_PATH_Environment = Environment.getExternalStorageDirectory().getPath();//SD卡路径, 映翰通高通版不识别获取的路径
    public static final String SDCARD_PATH = System.getenv("EXTERNAL_STORAGE");//SD卡路径（读取环境变量，获取SD卡路径）

    /************************************************************************************
     App专属文件
     特点：会在APK安装的时候创建，在APK卸载的时候删除
     内部储存卡：/data/data/< package name >/files
     外部储存卡：/mnt/sdcard/Android/data/< package name >/files
     由于外部存储卡可能会被用户卸下，所以重要的文件需要存储到内部存储卡（比如：shared preference文件，数据库文件）
     由于内部存储卡通常很小，大文件需要存储到外部存储卡（比如：特殊格式的电子书）
     ************************************************************************************/
    //获取目录 /data/data/< package name >/files
    public static String getInternalStorageDir(Context context){
        return  context.getApplicationContext().getFilesDir().getPath();
    }
    /**
     功能：获取目录 /mnt/sdcard/Android/data/< package name >/files
     需要权限： 不然会返回null
     <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
     <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
     */
    public static String getExternalStorageDir(Context context){
        return context.getApplicationContext().getExternalFilesDir(null).getPath();
    }


    //判断手机是否安装SD卡
//    if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
//        //SD卡已装入
//    }



}
