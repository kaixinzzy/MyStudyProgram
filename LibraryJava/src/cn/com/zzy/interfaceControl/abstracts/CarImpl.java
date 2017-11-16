package cn.com.zzy.interfaceControl.abstracts;

import cn.com.zzy.interfaceControl.interfaces.AutomatCar;

/**
 * Created by BCLZzy on 2017/9/25.
 */

public abstract class CarImpl implements AutomatCar {
    private String mLogo;

    public abstract void CarImpl();

    public void setLogo(String logo){
        this.mLogo = logo;
    }

    public String getLogo(){
        return mLogo;
    }

}
