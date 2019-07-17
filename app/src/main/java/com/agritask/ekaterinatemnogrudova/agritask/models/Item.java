package com.agritask.ekaterinatemnogrudova.agritask.models;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

public class Item {
    private int resId;
    private int id;

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @BindingAdapter("android:src")
    public static void setImageResource(ImageView imageView, int resource){
        imageView.setImageResource(resource);
    }
}

