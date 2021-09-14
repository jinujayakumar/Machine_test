package com.example.kolomachinetest.api.repo.marvel.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import android.os.Parcel
import com.example.kolomachinetest.api.repo.marvel.data.Thumbnail

class Thumbnail : Parcelable {
    @SerializedName("path")
    var path: String? = null

    @SerializedName("extension")
    var extension: String? = null
    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(path)
        dest.writeString(this.extension)
    }

    fun readFromParcel(source: Parcel) {
        path = source.readString()
        this.extension = source.readString()
    }

    constructor() {}
    protected constructor(`in`: Parcel) {
        path = `in`.readString()
        this.extension = `in`.readString()
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Thumbnail> = object : Parcelable.Creator<Thumbnail> {
            override fun createFromParcel(source: Parcel): Thumbnail {
                return Thumbnail(source)
            }

            override fun newArray(size: Int): Array<Thumbnail?> {
                return arrayOfNulls(size)
            }
        }
    }
}