package com.example.kolomachinetest.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ApiResponse implements Parcelable {

    @SerializedName("code")
    public int code;

    @SerializedName("status")
    public String status;

    @SerializedName("copyright")
    public String copyright;

    @SerializedName("attributionText")
    public String attributionText;

    @SerializedName("attributionHTML")
    public String attributionHTML;

    @SerializedName("etag")
    public String etag;

    @SerializedName("data")
    public Data data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getAttributionText() {
        return attributionText;
    }

    public void setAttributionText(String attributionText) {
        this.attributionText = attributionText;
    }

    public String getAttributionHTML() {
        return attributionHTML;
    }

    public void setAttributionHTML(String attributionHTML) {
        this.attributionHTML = attributionHTML;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.code);
        dest.writeString(this.status);
        dest.writeString(this.copyright);
        dest.writeString(this.attributionText);
        dest.writeString(this.attributionHTML);
        dest.writeString(this.etag);
        dest.writeParcelable(this.data, flags);
    }

    public void readFromParcel(Parcel source) {
        this.code = source.readInt();
        this.status = source.readString();
        this.copyright = source.readString();
        this.attributionText = source.readString();
        this.attributionHTML = source.readString();
        this.etag = source.readString();
        this.data = source.readParcelable(Data.class.getClassLoader());
    }

    public ApiResponse() {
    }

    protected ApiResponse(Parcel in) {
        this.code = in.readInt();
        this.status = in.readString();
        this.copyright = in.readString();
        this.attributionText = in.readString();
        this.attributionHTML = in.readString();
        this.etag = in.readString();
        this.data = in.readParcelable(Data.class.getClassLoader());
    }

    public static final Parcelable.Creator<ApiResponse> CREATOR = new Parcelable.Creator<ApiResponse>() {
        @Override
        public ApiResponse createFromParcel(Parcel source) {
            return new ApiResponse(source);
        }

        @Override
        public ApiResponse[] newArray(int size) {
            return new ApiResponse[size];
        }
    };
}
