package cn.com.zzy.colectionState;

import java.util.ArrayList;
import java.util.List;

import cn.com.zzy.bean.User;

/**
 * Created by BCLZzy on 2017/11/27.
 */

public class JavaRun {

    public static void main(String args[]) {

        List<User> list = new ArrayList<User>();
        for (int i = 0; i < 6; i++) {
            list.add(new User("" + i, i));
        }

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getName() + " - " + list.get(i).getAge());
        }

        A a = new A();
        a.setData(list);
        a.changeData();

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getName() + " - " + list.get(i).getAge());
        }

    }

}
