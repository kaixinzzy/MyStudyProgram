package cn.com.zzy.DesignMode.Builder;

/**
 * Created by BCLZzy on 2017/11/8.
 */

public abstract class Computer {
    protected int mCpuCore = 1;
    protected int mRamSize = 0;
    protected String mOs = "Dos";

    public abstract void setmCpuCore(int cpuCore);
    public abstract void setmRamSize(int ramSize);
    public abstract void setmOs(String os);

    public String toString(){
        return "Computer Configuration is CPU内核=" + mCpuCore + " Ram=" + mRamSize + " Os=" + mOs;
    }

}
