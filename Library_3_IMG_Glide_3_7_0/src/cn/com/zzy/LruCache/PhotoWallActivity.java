package cn.com.zzy.LruCache;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import cn.com.zzy.LruCache.adapter.PhotoWallAdapter;
import cn.com.zzy.glide.R;

public class PhotoWallActivity extends AppCompatActivity {

    private GridView gridView;
    private PhotoWallAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_wall);

        gridView = (GridView) findViewById(R.id.photo_wall);

        String urlDouYu = "http://capi.douyucdn.cn/api/v1/live/1?offset=0&limit=100&client_sys=android";
        String[] urls = {};
        adapter = new PhotoWallAdapter(this, 0, urls, gridView, R.drawable.test);
        gridView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        adapter.cancelAllTasks();
    }

    private void getDataFromHttpGet(String url){
        HttpURLConnection conn = null;
        InputStream in = null;
        try {
            URL mUrl = new URL(url);
            conn = (HttpURLConnection)mUrl.openConnection();
            conn.setRequestMethod("GET");//HttpURLConnection默认就是用GET发送请求
            conn.setUseCaches(false);//禁用网络缓存
            in = new BufferedInputStream(conn.getInputStream());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                conn.disconnect();
            }

        }

    }

    /**
     * 从InputStream中读取数据，转换成byte数组，最后关闭InputStream
     * @param is 输入流
     * @return
     */
    private byte[] getBytesByInputStream(InputStream is) {
        byte[] bytes = null;
        BufferedInputStream bis = new BufferedInputStream(is);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(baos);
        byte[] buffer = new byte[1024 * 8];
        int length = 0;
        try {
            while ((length = bis.read(buffer)) > 0) {
                bos.write(buffer, 0, length);
            }
            bos.flush();
            bytes = baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return bytes;
    }



}
