##### AIDL介绍
> 1. AIDL (Android Interface Definition Language) 是一种IDL 语言，用于生成可以在Android设备上两个进程之间进行进程间通信(interprocess communication, IPC)的代码。如果在一个进程中（例如Activity）要调用另一个进程中（例如Service）对象的操作，就可以使用AIDL生成可序列化的参数。
> 2. AIDL IPC机制是面向接口的，像COM或Corba一样，但是更加轻量级。它是使用代理类在客户端和实现端传递数据。
> 3. 在使用AIDL前，必须要理解如何绑定service——bindService

##### 使用场景
> 跨进程通讯 && 并发操作

##### 传递类型
> 1. 基本数据类型 (byte short int long float double boolean char)
> 2. String 和 CharSequence
> 3. List：只支持ArrayList,包裹的元素必须是AIDL支持的类型
> 4. Map：HashMap,同样包裹的元素必须是AIDL支持的类型
> 5. Parcelable：所有实现Parcelable接口的对象
> 6. AIDL:所有的AIDL接口本身也可以在AIDL文件使用

##### 注意事项
> * 自定义的Parcelable对象和AIDL对象，不管它们与当前的AIDL文件是否位于同一个包，都必须显式import进来
> * AIDL的包结构在服务端和客户端要保持一致，否则运行出错，原因是：客户端需要反序列化服务端中和AIDL接口相关的所有类，如果类的完整路径不一致，无法成功反序列化，程序无法正常运行。
> * 与传统接口不同的是：aidl接口只支持方法，不支持声明静态变量
> * 关于AIDL的文件最好放在同一个包下。

##### 自定义类型（implements Parcelable）

##### client端绑定服务端，获取AIDL接口IBinder对象。
###### 1.相关Parcelable的Java对象与AIDL文件
Book.java
```
package ph.cheng.com.polyhall.aidl;
import android.os.Parcel;
import android.os.Parcelable;
public class Book implements Parcelable {
      private int bookId;
      private String bookName;
      public int getBookId() {
            return bookId;
      }
      public void setBookId(int bookId) {
            this.bookId = bookId;
      }
       public String getBookName() {
            return bookName;
      }
      public void setBookName(String bookName) {
            this.bookName = bookName;
      }
      public Book(int bookId, String bookName) {
            this.bookId = bookId;
            this.bookName = bookName;
      }
      @Override
      public int describeContents() {
            return 0;
      }
      @Override
      public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.bookId);
            dest.writeString(this.bookName);
      }
      public Book() {
      }
      protected Book(Parcel in) {
            this.bookId = in.readInt();
            this.bookName = in.readString();
      }
      public static final Creator<Book> CREATOR = new Creator<Book>()      {
            @Override
            public Book createFromParcel(Parcel source) {
                  return new Book(source);
            }
            @Override
            public Book[] newArray(int size) {
                  return new Book[size];
            }
      };
}
```
Book.aidl
```
package ph.cheng.com.polyhall.aidl;
parcelable Book;
```



##### Service调用Client,用回调接口。





