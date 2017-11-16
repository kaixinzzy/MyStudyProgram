package cn.com.zzy.DesignMode.Observer.Impl;

import cn.com.zzy.DesignMode.Observer.Interface.DisplayElement;
import cn.com.zzy.DesignMode.Observer.Interface.Observer;

/**
 * Created by BCLZzy on 2017/11/6.
 * 观察者实例  广告牌(用来显示温度、湿度)
 */

public class Watcher implements Observer, DisplayElement {
    WeatherData weatherData;//主题（被观察者）

    private String mTemperature = "";//温度
    private String mHumidity = "";   //湿度

    public Watcher(WeatherData weatherData) {
        this.weatherData = weatherData;
        this.weatherData.registerObserver(this);//将自己注册成观察者
    }

    @Override   //更新数据
    public void updata() {
        mTemperature = this.weatherData.getmTemperature();
        mHumidity = this.weatherData.getmHumidity();
        display();
    }

    @Override   //显示出来
    public void display() {
        System.out.println(mNumb + " 当前温度为：" + this.mTemperature + "℃ 湿度为：" + this.mHumidity);
    }


    private String mNumb = "";//广告牌编号
    public void setmName(String numb) {
        this.mNumb = numb;
    }

}
