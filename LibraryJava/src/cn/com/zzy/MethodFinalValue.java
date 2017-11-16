package cn.com.zzy;

/**
 * Created by BCLZzy on 2017/9/28.
 * 方法传参为final类型
 *
 * 1、final修饰的类不可有子类，例如java.lang。Math类就是一个final类，它不可以有子类。
 * 2、final修饰的基本类型不能被改变。
 * 3、但对于引用类型的变量而言，它保存的仅仅是一个引用，final只保证这个引用所引用的地址不会改变，
 *      即一直引用同一个对象，但这个对象完全可以发生改变。
 *          final int[] iArr={6,8,9,10}
 *              Arrays.sort（iArr）;//对数组进行排序，合法
 *              iArr[2]=-8;//对数组元素赋值，合法
 *              iArr=null；对iArr重新赋值，非法
 *          final User user = new User();//由于是final的，所以必须初始化。
 *              user.setName("张");//修改对象属性，合法
 *              user = new User();//修改对象引用地址，非法
 */

public class MethodFinalValue {
    final User user = new User();

    public static void main(String args[]) {

        MethodFinalValue mfv = new MethodFinalValue();

        System.out.println("");

        System.out.println("");
    }

    public class Person{
        private final User mUser = new User();



    }

    class User{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
