package cn.com.zzy.libservice.util;

import org.apache.log4j.Logger;

import cn.com.zzy.multi.log4j.LogUtilBase;

/**
 * Created by BCLZzy on 2017/8/11.
 */

public class LogUtil extends LogUtilBase {

    public static Logger getLogger(Class clz) {
        return getLogger(clz, "LibraryService");
    }

}
