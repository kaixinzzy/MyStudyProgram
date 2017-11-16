package cn.com.zzy.DesignMode.Observer.Interface;

/**
 * Created by BCLZzy on 2017/11/6.
 * 主题（发布者、被观察者）
 */

public interface Subject {

    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObserver();

}
