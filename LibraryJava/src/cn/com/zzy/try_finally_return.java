package cn.com.zzy;

/**
 * Created by BCLZzy on 2017/10/12.
 * try finally return 执行过程分析
 */

public class try_finally_return {


    public int getValue(){
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
//            return i;
        }

    }

    public static void main(String args[]) {
        try_finally_return tfr = new try_finally_return();

        System.out.println("" + tfr.getValue());
    }

}
