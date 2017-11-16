package cn.com.zzy.DesignMode.Builder;

/**
 * Created by BCLZzy on 2017/11/8.
 */

public class JavaRun {
    public static void main(String args[]) {
        Builder builder = new AppleBuilder();
        Director director = new Director(builder);
        director.construct(4, 2, "Mac OS X 10.9.1");

        System.out.println(builder.create().toString());
    }
}
