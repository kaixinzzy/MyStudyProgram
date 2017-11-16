package cn.com.zzy.multi;

import android.util.Log;

/**
 * LogJumpToSource
 * Created by BCLZzy on 2017/6/22.
 */

public class MyLog {

    public static void Log(String tag, String msg) {

        StackTraceElement[] stackTraceElement = Thread.currentThread()
                .getStackTrace();
        int currentIndex = -1;
        for (int i = 0; i < stackTraceElement.length; i++) {
            if (stackTraceElement[i].getMethodName().compareTo("Log") == 0)
            {
                currentIndex = i + 1;
                break;
            }
        }

        if (currentIndex != -1) {
            String fullClassName = stackTraceElement[currentIndex].getClassName();
            String className = fullClassName.substring(fullClassName
                    .lastIndexOf(".") + 1);
            String methodName = stackTraceElement[currentIndex].getMethodName();
            String lineNumber = String
                    .valueOf(stackTraceElement[currentIndex].getLineNumber());

            Log.d(tag, fullClassName + "." + methodName + "("
                    + className + ".java:" + lineNumber + ") " + msg);
        } else {
            Log.d(tag, "MyLog打印异常");
        }
    }

}
