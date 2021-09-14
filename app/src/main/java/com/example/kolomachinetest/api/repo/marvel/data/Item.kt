package com.example.kolomachinetest.api.repo.marvel.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import android.os.Parcel

class Item : Parcelable {
    @SerializedName("resourceURI")
    var resourceURI: String? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("type")
    var type: String? = null
    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(resourceURI)
        dest.writeString(name)
        dest.writeString(type)
    }

    fun readFromParcel(source: Parcel) {
        resourceURI = source.readString()
        name = source.readString()
        type = source.readString()
    }

    constructor() {}
    protected constructor(`in`: Parcel) {
        resourceURI = `in`.readString()
        name = `in`.readString()
        type = `in`.readString()
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Item> = object : Parcelable.Creator<Item> {
            override fun createFromParcel(source: Parcel): Item {
                return Item(source)
            }

            override fun newArray(size: Int): Array<Item?> {
                return arrayOfNulls(size)
            }
        }
    }
}