package cn.com.zzy;

import java.util.HashMap;

/**
 * java日常疑惑测试
 */
public class JavaRun {
    public static boolean flag = true;

    public static void main (String[] args){
        final JavaRun javaRun = new JavaRun();
//        javaRun.getRemainder();
//        javaRun.MapGetKeyIsNull();
//        System.out.println("try_finally_return结果是:" + javaRun.try_finally_return());
//        javaRun.try_catch_throw_exception(null);
//        javaRun.return_break();
//        javaRun.collectionsOrder();

/*        final User user = new User("张三", 0);
        new Thread(new Runnable() {
            @Override
            public void run() {
                javaRun.setInfo(user);
            }
        }).start();
        try {
            Thread.sleep(1);
            System.out.println("我们不一样 " + user.getAge());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/


    }


    /**
     * 1、return会终止函数（即方法）
     * 2、break会终止循环
     */
    public void return_break(){
        int count = 100;
        for (int i = 0; i < count; i++) {
            if (i == 3){
                break;//终止for循环（）不止for循环，所有循环都可以终止
            }
            if (i == 2) {
                return;//终止return_break()方法
            }
        }
    }

    /**
     * 1、try catch到的异常，不会引起程序崩溃，程序可继续向下执行。
     * 2、未try catch到的异常，程序直接崩溃。
     * 3、主动throw new Exception，程序崩溃；用于提示用户崩溃信息，用户可以将此信息通知程序员。
     *
     * @param s 请 传入null
     */
    public void try_catch_throw_exception(String s){
        try {
            Integer.parseInt(s);
        } catch (Exception e) {
            e.printStackTrace();//打印异常信息，程序继续向下执行
        }
        System.out.println("异常已被捕获 e.printStackTrace()");

        try {
            Integer.parseInt(s);
        } catch (Exception e) {
            //异常已被捕获，这里即使任何事情都不做，程序也可以继续往下执行
        }
        System.out.println("异常已被捕获");

        //这样程序也会直接崩溃，后面代码不会执行；崩溃信息显示java内部异常定义的信息
        //Integer.parseInt(s);

        if (null == s) {
            //主动抛出异常，程序终止，后面的代码不会执行
            throw new IllegalArgumentException("我是 IllegalArgumentException");
        }
        System.out.println("主动抛出异常，程序终止，此句不会打印");
    }

    public int try_finally_return(){
        int i = 1;
        try {
            // 在try里返回值会先存到一个临时变量中，finally里改变的是原始变量，
            // 改完之后再将临时变量的值return(返回)，也就是说在finally里改变返回值变量并不影响返回值本身。
            return i;
        } catch (Exception e) {
            return i;
        } finally {
            i++;
            // 可见如果finally和try里都有执行了return，try里的return的值会被废弃。
            //  return i;
        }
    }

    /**
     * 1、当map.get(key)时，map中没有相应的key时，是会报异常，还是返回null
     *      结果：返回null
     * 2、map中key是唯一的，如果再次插入已有的键值对，那么会覆盖以存在key的value值.
     */
    public void MapGetKeyIsNull(){
        HashMap<String, String> arrayMap = new HashMap<>();
        arrayMap.put("a", "a");
        arrayMap.put("b", "b");

        String result = arrayMap.get("c");
        if (null == result){
            System.out.println("结果是null");
        } else {
            System.out.println("结果是 " + result);
        }
    }

    /**
     * 取余数
     * 0 % 3 = 0
     * 1 % 3 = 1
     * 2 % 3 = 2
     * 3 % 3 = 0
     */
    public void getRemainder(){
        System.out.println("0 % 3 = " + (0 % 3));
        System.out.println("1 % 3 = " + (1 % 3));
        System.out.println("2 % 3 = " + (2 % 3));
        System.out.println("3 % 3 = " + (3 % 3));
    }

}
