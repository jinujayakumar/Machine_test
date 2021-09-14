package com.example.kolomachinetest.api.repo.marvel.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import android.os.Parcel

class Url : Parcelable {
    @SerializedName("available")
    var type: String? = null

    @SerializedName("collectionURI")
    var url: String? = null
    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(type)
        dest.writeString(url)
    }

    fun readFromParcel(source: Parcel) {
        type = source.readString()
        url = source.readString()
    }

    constructor() {}
    protected constructor(`in`: Parcel) {
        type = `in`.readString()
        url = `in`.readString()
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Url> = object : Parcelable.Creator<Url> {
            override fun createFromParcel(source: Parcel): Url {
                return Url(source)
            }

            override fun newArray(size: Int): Array<Url?> {
                return arrayOfNulls(size)
            }
        }
    }
}