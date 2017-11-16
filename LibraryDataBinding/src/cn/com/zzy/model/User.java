package cn.com.zzy.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import cn.com.zzy.BR;

public class User extends BaseObservable {
    private String firstName;
    private String lastName;
    private Drawable iconDrawable;
    private Bitmap iconBitmap;
    private String iconUrl;
    private int iconPlaceHolder;
    private int iconError;

    @Bindable
    public String getLastName() {
        return lastName;
    }

    @Bindable
    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        notifyPropertyChanged(BR.lastName);//通知lastName更新，更新内容从getLastName()中获取
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        notifyPropertyChanged(BR.firstName);
    }

    @Bindable
    public Drawable getIconDrawable() {
        return iconDrawable;
    }

    public void setIconDrawable(Drawable iconDrawable) {
        this.iconDrawable = iconDrawable;
        notifyPropertyChanged(BR.iconDrawable);
    }

    @Bindable
    public Bitmap getIconBitmap() {
        return iconBitmap;
    }

    public void setIconBitmap(Bitmap iconBitmap) {
        this.iconBitmap = iconBitmap;
        notifyPropertyChanged(BR.iconBitmap);
    }

    @Bindable
    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
        notifyPropertyChanged(BR.iconUrl);
    }

    @Bindable
    public int getIconPlaceHolder() {
        return iconPlaceHolder;
    }

    public void setIconPlaceHolder(int iconPlaceHolder) {
        this.iconPlaceHolder = iconPlaceHolder;
        notifyPropertyChanged(BR.iconPlaceHolder);
    }

    @Bindable
    public int getIconError() {
        return iconError;
    }

    public void setIconError(int iconError) {
        this.iconError = iconError;
        notifyPropertyChanged(iconError);
    }



}
