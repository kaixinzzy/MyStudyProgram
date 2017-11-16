package cn.com.zzy.DesignMode.Builder;

/**
 * Created by BCLZzy on 2017/11/8.
 */

public interface Builder {
    //设置CPU
    void buildCPU(int core);
    //设置内存
    void buildRAM(int ram);
    //设置操作系统
    void buildOS(String os);

    //创建Computer
    Computer create();
}
