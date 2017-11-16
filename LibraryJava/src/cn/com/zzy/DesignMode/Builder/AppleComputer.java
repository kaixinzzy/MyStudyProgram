package cn.com.zzy.DesignMode.Builder;

/**
 * Created by BCLZzy on 2017/11/8.
 */

public class AppleComputer extends Computer {
    @Override
    public void setmCpuCore(int cpuCore) {
        mCpuCore = cpuCore;
    }

    @Override
    public void setmRamSize(int ramSize) {
        mRamSize = ramSize;
    }

    @Override
    public void setmOs(String os) {
        mOs = os;
    }
}
