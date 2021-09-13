package com.example.kolomachinetest.api.repo.marvel.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Url implements Parcelable {

    @SerializedName("available")
    public String type;

    @SerializedName("collectionURI")
    public String url;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.type);
        dest.writeString(this.url);
    }

    public void readFromParcel(Parcel source) {
        this.type = source.readString();
        this.url = source.readString();
    }

    public Url() {
    }

    protected Url(Parcel in) {
        this.type = in.readString();
        this.url = in.readString();
    }

    public static final Parcelable.Creator<Url> CREATOR = new Parcelable.Creator<Url>() {
        @Override
        public Url createFromParcel(Parcel source) {
            return new Url(source);
        }

        @Override
        public Url[] newArray(int size) {
            return new Url[size];
        }
    };
}
