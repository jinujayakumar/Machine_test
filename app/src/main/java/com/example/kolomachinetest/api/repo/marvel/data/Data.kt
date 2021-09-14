package com.example.kolomachinetest.api.repo.marvel.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import android.os.Parcel
import java.util.ArrayList

class Data : Parcelable {
    @SerializedName("offset")
    var offset = 0

    @SerializedName("limit")
    var limit = 0

    @SerializedName("total")
    var total = 0

    @SerializedName("count")
    var count = 0

    @SerializedName("results")
    var results: ArrayList<Result?>? = null
    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(offset)
        dest.writeInt(limit)
        dest.writeInt(total)
        dest.writeInt(count)
        dest.writeList(results)
    }

    fun readFromParcel(source: Parcel) {
        offset = source.readInt()
        limit = source.readInt()
        total = source.readInt()
        count = source.readInt()
        results = ArrayList()
        source.readList(results!!, Result::class.java.classLoader)
    }

    constructor() {}
    protected constructor(`in`: Parcel) {
        offset = `in`.readInt()
        limit = `in`.readInt()
        total = `in`.readInt()
        count = `in`.readInt()
        results = ArrayList()
        `in`.readList(results!!, Result::class.java.classLoader)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Data> = object : Parcelable.Creator<Data> {
            override fun createFromParcel(source: Parcel): Data {
                return Data(source)
            }

            override fun newArray(size: Int): Array<Data?> {
                return arrayOfNulls(size)
            }
        }
    }
}