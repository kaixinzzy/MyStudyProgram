package cn.com.zzy.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by BCLZzy on 2017/10/20.
 * SQLite数据库工具类
 *      数据库创建
 *      数据库升级
 */

public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String TAG = MySQLiteOpenHelper.class.getSimpleName();

    private static final String DB_NAME = "myTest.db";//数据库名
    public static final String TABLE_NAME = "Orders"; //版本号


    public static final String USER_TABLE_NAME = "user";//表名

    /**
     *
     * @param context   上下文
     * @param name      数据库名
     * @param factory   允许我们在查询数据的时候返回一个自定义的Cursor,一般都是传入null
     * @param version   当前数据库的版本号【1、必须大于0，否则报错  2、系统会根据这个版本号，来判断是否进行升级or降级操作】
     */
    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /**
     * 创建数据库
     * 1、在第一次打开数据库的时候才会走
     * 2、在清除数据之后再次运行-->打开数据库，这个方法会走
     * 3、没有清除数据，不会走这个方法
     * 4、数据库升级的时候这个方法不会走【除非手动调用】
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + USER_TABLE_NAME + "(_id INTEGER PRIMARY KEY, name TEXT, sex INT)");
    }

    /**
     * 数据库升级
     * 1、第一次创建数据库的时候，这个方法不会走
     * 2、清除数据后再次运行(相当于第一次创建)这个方法不会走
     * 3、数据库已经存在，而且版本升高的时候，这个方法才会调用
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //删除表
        db.execSQL("drop table if exists user");
        //创新创建数据库
        onCreate(db);
    }

    /**
     * 数据库降级
     * 1、只有新版本比旧版本低的时候才会执行
     * 2、如果不执行降级操作，会抛出异常
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }
}
