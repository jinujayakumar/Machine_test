package com.example.kolomachinetest.api.repo.marvel.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import android.os.Parcel
import com.example.kolomachinetest.api.repo.marvel.data.Price

class Price : Parcelable {
    @SerializedName("type")
    var type: String? = null

    @SerializedName("price")
    var price = 0.0
    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(type)
        dest.writeDouble(price)
    }

    fun readFromParcel(source: Parcel) {
        type = source.readString()
        price = source.readDouble()
    }

    constructor() {}
    protected constructor(`in`: Parcel) {
        type = `in`.readString()
        price = `in`.readDouble()
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Price> = object : Parcelable.Creator<Price> {
            override fun createFromParcel(source: Parcel): Price {
                return Price(source)
            }

            override fun newArray(size: Int): Array<Price?> {
                return arrayOfNulls(size)
            }
        }
    }
}