package com.example.kolomachinetest.api.repo.marvel.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class TextObject implements Parcelable {


    @SerializedName("type")
    public String type;

    @SerializedName("language")
    public String language;

    @SerializedName("text")
    public String text;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.type);
        dest.writeString(this.language);
        dest.writeString(this.text);
    }

    public void readFromParcel(Parcel source) {
        this.type = source.readString();
        this.language = source.readString();
        this.text = source.readString();
    }

    public TextObject() {
    }

    protected TextObject(Parcel in) {
        this.type = in.readString();
        this.language = in.readString();
        this.text = in.readString();
    }

    public static final Parcelable.Creator<TextObject> CREATOR = new Parcelable.Creator<TextObject>() {
        @Override
        public TextObject createFromParcel(Parcel source) {
            return new TextObject(source);
        }

        @Override
        public TextObject[] newArray(int size) {
            return new TextObject[size];
        }
    };
}
