package cn.com.zzy.multi.log4j;

import org.apache.log4j.Logger;

/**
 * Created by BCLZzy on 2017/8/11.
 */

public class LogUtil extends LogUtilBase {

    public static Logger getLogger(Class clz) {
        return getLogger(clz, "LibraryMulti");
    }

}
