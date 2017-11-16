package cn.com.zzy.DesignMode.Composition.Impl;

import cn.com.zzy.DesignMode.Composition.Interface.Component;

/**
 * Created by BCLZzy on 2017/11/10.
 */

public class FileImage implements Component {
    private String name;
    public FileImage(String mName){
        this.name = mName;
    }

    @Override
    public void add(Component component) {

    }

    @Override
    public void remove(Component component) {

    }

    @Override
    public Component getChild(int i) {
        return null;
    }

    @Override
    public void operation() {

    }
}
