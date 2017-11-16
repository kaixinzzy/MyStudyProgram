package cn.com.zzy.FastJson;

import com.alibaba.fastjson.JSON;

import cn.com.zzy.FastJson.bean.Group;
import cn.com.zzy.FastJson.bean.User;

/**
 * Created by BCLZzy on 2017/9/19.
 * alibaba fastjson
 */

public class Test {

    public void Encode(){
        User guestUser = new User();
        guestUser.setId(2L);
        guestUser.setName("guest");

        User rootUser = new User();
        rootUser.setId(3L);
        rootUser.setName("root");

        Group group = new Group();
        group.setId(0L);
        group.setName("admin");
        group.addUser(guestUser);
        group.addUser(rootUser);

        String jsonString = JSON.toJSONString(group);
        //MyLog.Log("~~~", "jsonString " + jsonString);
    }

    public void Decode(){
        String jsonString = "{\"id\":0,\"name\":\"admin\",\"users\":[{\"id\":2,\"name\":\"guest\"},{\"id\":3,\"name\":\"root\"}]}";
        Group group = JSON.parseObject(jsonString, Group.class);
        //MyLog.Log("~~~", "group.getName " + group.getName());
    }

}
