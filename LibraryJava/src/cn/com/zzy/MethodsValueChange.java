package cn.com.zzy;

/**
 * Created by BCLZzy on 2017/9/12.
 * 值A 做为参数传递给方法方法A，当方法A中的值A改变时，原本的值A是否跟着改变

 http://www.cnblogs.com/linkstar/p/5951141.html  经典讲解
 那么方法中什么时候会改变原来的值，什么时候不会呢？
 1、只要是基本类型，传递的是值，这个值是复制了一份出来的，所以怎么都不会改变。
 2、引用类型，传递的是地址，如果这个地址变了，那么原来的值肯定不变。
 3、引用类型，传递的是地址，如果地址没变，而改变了地址对应的对象的属性，那么久会改变原来的值。

 我的理解：
 当值A是 基本类型 时，原本的值A不会跟随方法中值A的改变而改变。
 当值A是 引用类型 时，原本的值A会跟随方法中值A的改变而改变。

 基本类型有：byte short int long float double char boolean
 引用类型有三种： 类class  接口interface  数组array
 类class包括自定义类、Object、String、 Date、Integer、Long、Boolean、Byte、Character、Double、Float、Short

 网上解释：
 想理解传值调用传引用调用应该理解 栈内存和堆内存，
 基本类型 和 指针（java中叫引用名称） 存在占内存中，引用名词的具体值存放在堆内存中，，，所有的方法，调用的时候都会拷贝，
 你说那个基本类型是拷贝，引用类型是指向，不太确切，，，其实 当方法调用时候，方法的参数照样会在占内存中开辟一块新的区域，
 同时把要传递的基本类型，或者应用类型名称，复制（注意，统统是复制，而不存在有的复制，有的指向这一说）。。。结果是，
 普通类型（存放在占内存中的）复制之后的值改变了，但与原来的已无关（这点跟你的理解一样），，
 但是引用类型，把复制后的引用名词（虽然是复制，但是依然和原来的指向同一块堆内存区域），
 找到堆内存的区域，改变他（这和你的理解结果是一样的，内存形态有所改变）

 */

public class MethodsValueChange {

    private String[] mPortAdr = {"/dev/ttymxc0", "/dev/ttymxc1", "/dev/ttymxc2",
            "/dev/ttymxc3", "/dev/ttymxc4"};
    private String s = "String abc";
    private StringBuffer sb = new StringBuffer("sb abc");

    public MethodsValueChange(){
        System.out.println(s);
        System.out.println(sb);
        changeArray(mPortAdr, s, sb);
        System.out.println(s);
        System.out.println(sb);

        for (int i = 0; i < mPortAdr.length; i++) {
            System.out.println(i + " " + mPortAdr[i]);
        }

        A a = new A();                        System.out.println(a.Name + " " + a.age);
        a.Name = "change after"; a.age = 20;  System.out.println(a.Name + " " + a.age);


    }

    public void changeArray(String[] portAdr, String s, StringBuffer mSB){
        portAdr[1] = "/dev/ttyO3";
        portAdr[3] = "/dev/ttyO2";
        portAdr[4] = "/dev/ttyO4";

        s = "String d";//传递的是地址，地址变了
        mSB.append(" d");//传递的是地址，地址没有变，而改变了地址对应的对象的属性
    }

    class A {
        public int age = 10;
        public String Name = "change before";
    }

}
