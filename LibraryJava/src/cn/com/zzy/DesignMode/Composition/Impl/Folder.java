package cn.com.zzy.DesignMode.Composition.Impl;

import java.util.ArrayList;

import cn.com.zzy.DesignMode.Composition.Interface.Component;

/**
 * Created by BCLZzy on 2017/11/10.
 */

public class Folder implements Component {
    private ArrayList<Component> list = new ArrayList<Component>();

    @Override
    public void add(Component component) {
        list.add(component);
    }

    @Override
    public void remove(Component component) {
        list.remove(component);
    }

    @Override
    public Component getChild(int i) {
        return list.get(i);
    }

    @Override
    public void operation() {
        //容器构件具体业务方法的实现
        for (Component component : list) {
            component.operation();
        }

    }
}
