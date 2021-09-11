package com.example.kolomachinetest.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Data implements Parcelable {

    @SerializedName("offset")
    public int offset;

    @SerializedName("limit")
    public int limit;

    @SerializedName("total")
    public int total;

    @SerializedName("count")
    public int count;

    @SerializedName("results")
    public ArrayList<Character> results;

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ArrayList<Character> getResults() {
        return results;
    }

    public void setResults(ArrayList<Character> results) {
        this.results = results;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.offset);
        dest.writeInt(this.limit);
        dest.writeInt(this.total);
        dest.writeInt(this.count);
        dest.writeList(this.results);
    }

    public void readFromParcel(Parcel source) {
        this.offset = source.readInt();
        this.limit = source.readInt();
        this.total = source.readInt();
        this.count = source.readInt();
        this.results = new ArrayList<>();
        source.readList(this.results, Character.class.getClassLoader());
    }

    public Data() {
    }

    protected Data(Parcel in) {
        this.offset = in.readInt();
        this.limit = in.readInt();
        this.total = in.readInt();
        this.count = in.readInt();
        this.results = new ArrayList<>();
        in.readList(this.results, Character.class.getClassLoader());
    }

    public static final Parcelable.Creator<Data> CREATOR = new Parcelable.Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel source) {
            return new Data(source);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };
}
