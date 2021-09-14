package com.example.kolomachinetest.api.repo.marvel.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import android.os.Parcel
import com.example.kolomachinetest.api.repo.marvel.data.ApiResponse

class ApiResponse : Parcelable {
    @SerializedName("code")
    var code = 0

    @SerializedName("status")
    var status: String? = null

    @SerializedName("copyright")
    var copyright: String? = null

    @SerializedName("attributionText")
    var attributionText: String? = null

    @SerializedName("attributionHTML")
    var attributionHTML: String? = null

    @SerializedName("etag")
    var etag: String? = null

    @SerializedName("data")
    var data: Data? = null
    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(this.code)
        dest.writeString(status)
        dest.writeString(copyright)
        dest.writeString(attributionText)
        dest.writeString(attributionHTML)
        dest.writeString(etag)
        dest.writeParcelable(data, flags)
    }

    fun readFromParcel(source: Parcel) {
        this.code = source.readInt()
        status = source.readString()
        copyright = source.readString()
        attributionText = source.readString()
        attributionHTML = source.readString()
        etag = source.readString()
        data = source.readParcelable(Data::class.java.classLoader)
    }

    constructor()
    constructor(`in`: Parcel) {
        this.code = `in`.readInt()
        status = `in`.readString()
        copyright = `in`.readString()
        attributionText = `in`.readString()
        attributionHTML = `in`.readString()
        etag = `in`.readString()
        data = `in`.readParcelable(Data::class.java.classLoader)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<ApiResponse> = object : Parcelable.Creator<ApiResponse> {
            override fun createFromParcel(source: Parcel): ApiResponse {
                return ApiResponse(source)
            }

            override fun newArray(size: Int): Array<ApiResponse?> {
                return arrayOfNulls(size)
            }
        }
    }
}