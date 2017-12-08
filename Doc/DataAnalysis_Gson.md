
>基于Gson2.4
官方地址：http://json.org/json-zh.html
参考:    http://www.jianshu.com/p/e740196225a4

##### 一、Gson的基本用法
---
```
Gson gson = new Gson();
//Json解析成Bean
int i = gson.fromJson("100", int.class);              //100
double d = gson.fromJson("\"99.99\"", double.class);  //99.99
boolean b = gson.fromJson("true", boolean.class);     // true
String str = gson.fromJson("String", String.class);   // String

//Bean生成Json
String jsonNumber = gson.toJson(100);       // 100
String jsonBoolean = gson.toJson(false);    // false
String jsonString = gson.toJson("String"); //"String"
```
##### 二、属性重命名 @SerializedName 注解的使用
---
```
public class User {
    //将json中的email、email_address、emailAddress解析的时候，都对应emailAddress这个属性
    @SerializedName(value = "emailAddress", alternate = {"email", "email_address"})
    public String emailAddress;
    //省略其它
    ...
}
```
##### 三、Gson中使用泛型
---
项目中服务器返回结果通常带有：code：状态码（成功、失败等）
```
public class Result<T> {
    public int code;
    public String message;
    public T data;
}

//不再重复定义Result类
Type userType = new TypeToken<Result<User>>(){}.getType();
Result<User> userResult = gson.fromJson(json,userType);
User user = userResult.data;

Type userListType = new TypeToken<Result<List<User>>>(){}.getType();
Result<List<User>> userListResult = gson.fromJson(json,userListType);
List<User> users = userListResult.data;
```

