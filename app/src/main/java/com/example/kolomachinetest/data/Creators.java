package com.example.kolomachinetest.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Creators implements Parcelable {


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
        dest.writeTypedList(this.items);
        dest.writeInt(this.returned);
    }

    public void readFromParcel(Parcel source) {
        this.available = source.readInt();
        this.collectionURI = source.readString();
        this.items = source.createTypedArrayList(Item.CREATOR);
        this.returned = source.readInt();
    }

    public Creators() {
    }

    protected Creators(Parcel in) {
        this.available = in.readInt();
        this.collectionURI = in.readString();
        this.items = in.createTypedArrayList(Item.CREATOR);
        this.returned = in.readInt();
    }

    public static final Parcelable.Creator<Creators> CREATOR = new Parcelable.Creator<Creators>() {
        @Override
        public Creators createFromParcel(Parcel source) {
            return new Creators(source);
        }

        @Override
        public Creators[] newArray(int size) {
            return new Creators[size];
        }
    };
}
