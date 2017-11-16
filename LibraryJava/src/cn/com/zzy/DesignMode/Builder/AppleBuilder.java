package cn.com.zzy.DesignMode.Builder;

/**
 * Created by BCLZzy on 2017/11/8.
 */

public class AppleBuilder implements Builder {
    private Computer mAppleComputer = new AppleComputer();

    @Override
    public void buildCPU(int core) {
        mAppleComputer.setmCpuCore(core);
    }

    @Override
    public void buildRAM(int ram) {
        mAppleComputer.setmRamSize(ram);
    }

    @Override
    public void buildOS(String os) {
        mAppleComputer.setmOs(os);
    }

    @Override
    public Computer create() {
        return mAppleComputer;
    }
}
