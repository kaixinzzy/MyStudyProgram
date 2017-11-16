package cn.com.zzy.DesignMode.Observer;

import cn.com.zzy.DesignMode.Observer.Impl.WeatherData;
import cn.com.zzy.DesignMode.Observer.Impl.Watcher;

/**
 * Created by BCLZzy on 2017/11/6.
 * 观察者模式
 * 用例：
 *      硬件设备将检测到的温度、湿度通知给程序，程序将接收到温度、湿度通知所有广告牌更新显示
 * 参考：
 *      http://www.jianshu.com/p/d55ee6e83d66
 */

public class JavaRun {

    public static void main(String args[]) {
        WeatherData weatherData = new WeatherData();
        //初始化广告牌
        new Watcher(weatherData).setmName("广告牌一");
        new Watcher(weatherData).setmName("广告牌二");
        new Watcher(weatherData).setmName("广告牌三");

        //更新数据 所有广告牌会自动更新显示温度、湿度
        weatherData.setmTemperature("10");
        weatherData.setmHumidity("111");
        weatherData.setmTemperature("12");
        weatherData.setmHumidity("115");

    }

}
