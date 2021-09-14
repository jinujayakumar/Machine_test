package com.example.kolomachinetest.api.repo.marvel.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import android.os.Parcel
import java.util.ArrayList

class Comics : Parcelable {
    @SerializedName("available")
    var available = 0

    @SerializedName("collectionURI")
    var collectionURI: String? = null

    @SerializedName("items")
    var items: List<Item>? = null

    @SerializedName("returned")
    var returned = 0
    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(available)
        dest.writeString(collectionURI)
        dest.writeList(items)
        dest.writeInt(returned)
    }

    fun readFromParcel(source: Parcel) {
        available = source.readInt()
        collectionURI = source.readString()
        items = ArrayList()
        source.readList(items as ArrayList<Item>, Item::class.java.classLoader)
        returned = source.readInt()
    }


    constructor() {}
    constructor(`in`: Parcel) {
        available = `in`.readInt()
        collectionURI = `in`.readString()
        items = ArrayList()
        `in`.readList(items as ArrayList<Item>, Item::class.java.classLoader)
        returned = `in`.readInt()
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Comics> = object : Parcelable.Creator<Comics> {
            override fun createFromParcel(source: Parcel): Comics {
                return Comics(source)
            }

            override fun newArray(size: Int): Array<Comics?> {
                return arrayOfNulls(size)
            }
        }
    }
}