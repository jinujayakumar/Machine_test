package com.example.kolomachinetest.api.repo.marvel.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Events implements Parcelable {

    @SerializedName("available")
    public int available;

    @SerializedName("collectionURI")
    public String collectionURI;

    @SerializedName("items")
    public List<Item> items;

    @SerializedName("returned")
    public int returned;

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public String getCollectionURI() {
        return collectionURI;
    }

    public void setCollectionURI(String collectionURI) {
        this.collectionURI = collectionURI;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public int getReturned() {
        return returned;
    }

    public void setReturned(int returned) {
        this.returned = returned;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.available);
        dest.writeString(this.collectionURI);
        dest.writeList(this.items);
        dest.writeInt(this.returned);
    }

    public void readFromParcel(Parcel source) {
        this.available = source.readInt();
        this.collectionURI = source.readString();
        this.items = new ArrayList<Item>();
        source.readList(this.items, Item.class.getClassLoader());
        this.returned = source.readInt();
    }

    public Events() {
    }

    protected Events(Parcel in) {
        this.available = in.readInt();
        this.collectionURI = in.readString();
        this.items = new ArrayList<Item>();
        in.readList(this.items, Item.class.getClassLoader());
        this.returned = in.readInt();
    }

    public static final Parcelable.Creator<Events> CREATOR = new Parcelable.Creator<Events>() {
        @Override
        public Events createFromParcel(Parcel source) {
            return new Events(source);
        }

        @Override
        public Events[] newArray(int size) {
            return new Events[size];
        }
    };
}
