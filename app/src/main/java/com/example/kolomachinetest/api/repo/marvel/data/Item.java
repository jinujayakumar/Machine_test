package com.example.kolomachinetest.api.repo.marvel.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Item implements Parcelable {

    @SerializedName("resourceURI")
    public String resourceURI;

    @SerializedName("name")
    public String name;

    @SerializedName("type")
    public String type;

    public String getResourceURI() {
        return resourceURI;
    }

    public void setResourceURI(String resourceURI) {
        this.resourceURI = resourceURI;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.resourceURI);
        dest.writeString(this.name);
        dest.writeString(this.type);
    }

    public void readFromParcel(Parcel source) {
        this.resourceURI = source.readString();
        this.name = source.readString();
        this.type = source.readString();
    }

    public Item() {
    }

    protected Item(Parcel in) {
        this.resourceURI = in.readString();
        this.name = in.readString();
        this.type = in.readString();
    }

    public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel source) {
            return new Item(source);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };
}
