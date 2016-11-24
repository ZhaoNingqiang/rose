package com.flower.rose.been.sub;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @Description:
 * @Author: ZhaoNingqiang
 * @Time 2016/10/09 上午11:37
 */

public class Picture implements Parcelable {
    public String href;
    public String alt;
    public String title;
    public String thumbnail;
    public int width;
    public int height;
    public int itemHeight;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.href);
        dest.writeString(this.alt);
        dest.writeString(this.title);
        dest.writeString(this.thumbnail);
    }

    public Picture() {
    }

    protected Picture(Parcel in) {
        this.href = in.readString();
        this.alt = in.readString();
        this.title = in.readString();
        this.thumbnail = in.readString();
    }

    public static final Creator<Picture> CREATOR = new Creator<Picture>() {
        @Override
        public Picture createFromParcel(Parcel source) {
            return new Picture(source);
        }

        @Override
        public Picture[] newArray(int size) {
            return new Picture[size];
        }
    };
}
