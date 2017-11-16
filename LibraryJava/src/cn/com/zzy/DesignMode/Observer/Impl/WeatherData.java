package cn.com.zzy.DesignMode.Observer.Impl;

import java.util.ArrayList;
import java.util.List;

import cn.com.zzy.DesignMode.Observer.Interface.Subject;
import cn.com.zzy.DesignMode.Observer.Interface.Observer;

/**
 * Created by BCLZzy on 2017/11/6.
 * 被观察者实例
 *      唯一性【多个观察者，观察同一个被观察者】
 *      当被观察者发生变化，通知所有观察者。
 */

public class WeatherData implements Subject {
    private List<Observer> list = new ArrayList<Observer>();
    private String mTemperature = "";//温度
    private String mHumidity = "";   //湿度

    @Override   //注册观察者
    public void registerObserver(Observer observer) {
        list.add(observer);
    }

    @Override   //移除观察者
    public void removeObserver(Observer observer) {
        list.remove(observer);
    }

    @Override   //通知观察者
    public void notifyObserver() {
        for (Observer observer : list) {
            observer.updata();
        }
    }


    public String getmTemperature(){
        return this.mTemperature;
    }

    public void setmTemperature(String mTemperature) {
        this.mTemperature = mTemperature;
        notifyObserver();//通知观察者
    }

    public String getmHumidity(){
        return this.mHumidity;
    }

    public void setmHumidity(String mHumidity) {
        this.mHumidity = mHumidity;
        notifyObserver();//通知观察者
    }
}
