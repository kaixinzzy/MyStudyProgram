package cn.com.zzy.colectionState;

import java.util.List;

import cn.com.zzy.bean.User;

/**
 * Created by BCLZzy on 2017/11/27.
 */

public class A {
    private List<User> list;


    public void setData(List<User> list){
        this.list = list;
    }

    public void changeData(){
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setAge(i+1);
        }
    }

}
