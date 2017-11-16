package cn.com.zzy.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by BCLZzy on 2017/10/20.
 * ContentProvider
 */

public class MyContentProvider extends ContentProvider {
    private static final String TAG = MyContentProvider.class.getSimpleName();

    private final UriMatcher mUriMatcher;//用于匹配Uri

    private static final int USER = 1;//user表

    private static final String AUTHORITY = "cn.com.zzy.contentprovider"; //外部调用时指定ContentProvider的标识

    public MyContentProvider(){
        mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        mUriMatcher.addURI(AUTHORITY, "user", USER);//为Uri做配对

    }

    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override   //返回数据库的游标, 处理数据.
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override   //返回删除数据的数量, 大于0即删除成功.
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override  //返回修改数据的数量
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
