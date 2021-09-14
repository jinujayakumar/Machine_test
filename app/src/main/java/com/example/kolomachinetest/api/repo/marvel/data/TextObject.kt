package com.example.kolomachinetest.api.repo.marvel.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import android.os.Parcel
import com.example.kolomachinetest.api.repo.marvel.data.TextObject

class TextObject : Parcelable {
    @SerializedName("type")
    var type: String? = null

    @SerializedName("language")
    var language: String? = null

    @SerializedName("text")
    var text: String? = null
    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(type)
        dest.writeString(language)
        dest.writeString(text)
    }

    fun readFromParcel(source: Parcel) {
        type = source.readString()
        language = source.readString()
        text = source.readString()
    }

    constructor() {}
    protected constructor(`in`: Parcel) {
        type = `in`.readString()
        language = `in`.readString()
        text = `in`.readString()
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<TextObject> = object : Parcelable.Creator<TextObject> {
            override fun createFromParcel(source: Parcel): TextObject {
                return TextObject(source)
            }

            override fun newArray(size: Int): Array<TextObject?> {
                return arrayOfNulls(size)
            }
        }
    }
}