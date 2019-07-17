package com.agritask.ekaterinatemnogrudova.agritask.models;

import android.databinding.BindingAdapter;
import android.widget.ImageView;
import com.google.gson.annotations.SerializedName;

public class Item {
    @SerializedName("resId")
    private int resId;
    @SerializedName("id")
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

