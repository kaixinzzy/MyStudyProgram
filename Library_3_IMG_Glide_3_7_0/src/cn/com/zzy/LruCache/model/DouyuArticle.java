package cn.com.zzy.LruCache.model;

import java.util.List;

/**
 * Created by BCLZzy on 2017/11/20.
 * 斗鱼文章列表
 */

public class DouyuArticle {
    private int error;
    private List<Data> data;
    public void setError(int error) {
        this.error = error;
    }
    public int getError() {
        return error;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }
    public List<Data> getData() {
        return data;
    }
}
