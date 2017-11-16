package cn.com.zzy.multi.log4j;

import org.apache.log4j.Logger;

/**
 * Created by BCLZzy on 2017/8/11.
 */

public class LogUtilBase {

    /**
     * 获取类的name
     * @return class name
     */
    public static String makeLogTag(Class cls) {
        return cls.getName();
    }

    /**
     *
     * @param clz
     * @param fileName Log文件名称
     * @return
     */
    public static Logger getLogger(Class clz, String fileName) {
        return Log4jMessage.configLog(clz, fileName);
    }

}
