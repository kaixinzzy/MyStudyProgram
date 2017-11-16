package cn.com.zzy.DesignMode.Builder;

/**
 * Created by BCLZzy on 2017/11/8.
 */

public class Director {
    Builder mBuilder = null;

    public Director(Builder builder){
        mBuilder = builder;
    }

    public void construct(int cpu, int ram, String os){
        mBuilder.buildCPU(cpu);
        mBuilder.buildRAM(ram);
        mBuilder.buildOS(os);
    }

}
