package com.example.kolomachinetest.api.repo.marvel.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import android.os.Parcel
import com.example.kolomachinetest.api.repo.marvel.data.Stories

class Stories : Parcelable {
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
        dest.writeTypedList(items)
        dest.writeInt(returned)
    }

    fun readFromParcel(source: Parcel) {
        available = source.readInt()
        collectionURI = source.readString()
        items = source.createTypedArrayList(Item.CREATOR)
        returned = source.readInt()
    }

    constructor() {}
    protected constructor(`in`: Parcel) {
        available = `in`.readInt()
        collectionURI = `in`.readString()
        items = `in`.createTypedArrayList(Item.CREATOR)
        returned = `in`.readInt()
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Stories> = object : Parcelable.Creator<Stories> {
            override fun createFromParcel(source: Parcel): Stories {
                return Stories(source)
            }

            override fun newArray(size: Int): Array<Stories?> {
                return arrayOfNulls(size)
            }
        }
    }
}