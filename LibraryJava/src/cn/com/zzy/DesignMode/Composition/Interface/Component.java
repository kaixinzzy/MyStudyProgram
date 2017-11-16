package cn.com.zzy.DesignMode.Composition.Interface;

/**
 * Created by BCLZzy on 2017/11/10.
 */

public interface Component {
    void add(Component component);      //添加成员
    void remove(Component component);   //删除成员
    Component getChild(int i);          //获取成员

    void operation();                   //业务逻辑
}
