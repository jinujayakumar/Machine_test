package com.example.kolomachinetest.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Price implements Parcelable {

    @SerializedName("type")
    public String type;

    @SerializedName("price")
    public double price;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.type);
        dest.writeDouble(this.price);
    }

    public void readFromParcel(Parcel source) {
        this.type = source.readString();
        this.price = source.readDouble();
    }

    public Price() {
    }

    protected Price(Parcel in) {
        this.type = in.readString();
        this.price = in.readDouble();
    }

    public static final Parcelable.Creator<Price> CREATOR = new Parcelable.Creator<Price>() {
        @Override
        public Price createFromParcel(Parcel source) {
            return new Price(source);
        }

        @Override
        public Price[] newArray(int size) {
            return new Price[size];
        }
    };
}
