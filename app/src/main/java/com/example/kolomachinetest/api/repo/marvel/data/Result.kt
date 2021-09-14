package com.example.kolomachinetest.api.repo.marvel.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.example.kolomachinetest.api.repo.marvel.data.Thumbnail
import com.example.kolomachinetest.api.repo.marvel.data.Comics
import com.example.kolomachinetest.api.repo.marvel.data.Series
import com.example.kolomachinetest.api.repo.marvel.data.Stories
import com.example.kolomachinetest.api.repo.marvel.data.TextObject
import com.example.kolomachinetest.api.repo.marvel.data.Price
import com.example.kolomachinetest.api.repo.marvel.data.Creators
import android.os.Parcel
import java.util.ArrayList

class Result : Parcelable {
    @SerializedName("id")
    var id = 0

    @SerializedName("name")
    var name: String? = null

    @SerializedName("description")
    var description: String? = null

    @SerializedName("modified")
    var modified: String? = null

    @SerializedName("thumbnail")
    var thumbnail: Thumbnail? = null

    @SerializedName("resourceURI")
    var resourceURI: String? = null

    @SerializedName("comics")
    var comics: Comics? = null

    @SerializedName("series")
    var series: Series? = null

    @SerializedName("stories")
    var stories: Stories? = null

    @SerializedName("events")
    var events: Events? = null

    @SerializedName("urls")
    var urls: List<Url>? = null

    @SerializedName("digitalId")
    var digitalId = 0

    @SerializedName("title")
    var title: String? = null

    @SerializedName("issueNumber")
    var issueNumber = 0

    @SerializedName("variantDescription")
    var variantDescription: String? = null

    @SerializedName("isbn")
    var isbn: String? = null

    @SerializedName("upc")
    var upc: String? = null

    @SerializedName("diamondCode")
    var diamondCode: String? = null

    @SerializedName("ean")
    var ean: String? = null

    @SerializedName("issn")
    var issn: String? = null

    @SerializedName("format")
    var format: String? = null

    @SerializedName("pageCount")
    var pageCount = 0

    @SerializedName("textObjects")
    var textObjects: List<TextObject?>? = null

    @SerializedName("variants")
    var variants: List<Series>? = null

    @SerializedName("collectedIssues")
    var collectedIssues: List<Series>? = null

    @SerializedName("prices")
    var prices: List<Price>? = null

    @SerializedName("images")
    var images: List<Thumbnail>? = null

    @SerializedName("creators")
    var creators: Creators? = null

    @SerializedName("characters")
    var characters: Creators? = null

    constructor() {}

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(id)
        dest.writeString(name)
        dest.writeString(description)
        dest.writeString(modified)
        dest.writeParcelable(thumbnail, flags)
        dest.writeString(resourceURI)
        dest.writeParcelable(comics, flags)
        dest.writeParcelable(series, flags)
        dest.writeParcelable(stories, flags)
        dest.writeParcelable(events, flags)
        dest.writeTypedList(urls)
        dest.writeInt(digitalId)
        dest.writeString(title)
        dest.writeInt(issueNumber)
        dest.writeString(variantDescription)
        dest.writeString(isbn)
        dest.writeString(upc)
        dest.writeString(diamondCode)
        dest.writeString(ean)
        dest.writeString(issn)
        dest.writeString(format)
        dest.writeInt(pageCount)
        dest.writeList(textObjects)
        dest.writeTypedList(variants)
        dest.writeTypedList(collectedIssues)
        dest.writeTypedList(prices)
        dest.writeTypedList(images)
        dest.writeParcelable(creators, flags)
        dest.writeParcelable(characters, flags)
    }

    fun readFromParcel(source: Parcel) {
        id = source.readInt()
        name = source.readString()
        description = source.readString()
        modified = source.readString()
        thumbnail = source.readParcelable(Thumbnail::class.java.classLoader)
        resourceURI = source.readString()
        comics = source.readParcelable(Comics::class.java.classLoader)
        series = source.readParcelable(Series::class.java.classLoader)
        stories = source.readParcelable(Stories::class.java.classLoader)
        events = source.readParcelable(Events::class.java.classLoader)
        urls = source.createTypedArrayList(Url.CREATOR)
        digitalId = source.readInt()
        title = source.readString()
        issueNumber = source.readInt()
        variantDescription = source.readString()
        isbn = source.readString()
        upc = source.readString()
        diamondCode = source.readString()
        ean = source.readString()
        issn = source.readString()
        format = source.readString()
        pageCount = source.readInt()
        textObjects = ArrayList()
        source.readList(textObjects as ArrayList<TextObject?>, TextObject::class.java.classLoader)
        variants = source.createTypedArrayList(Series.CREATOR)
        collectedIssues = source.createTypedArrayList(Series.CREATOR)
        prices = source.createTypedArrayList(Price.CREATOR)
        images = source.createTypedArrayList(Thumbnail.CREATOR)
        creators = source.readParcelable(Creators::class.java.classLoader)
        characters = source.readParcelable(Creators::class.java.classLoader)
    }

    protected constructor(`in`: Parcel) {
        id = `in`.readInt()
        name = `in`.readString()
        description = `in`.readString()
        modified = `in`.readString()
        thumbnail = `in`.readParcelable(Thumbnail::class.java.classLoader)
        resourceURI = `in`.readString()
        comics = `in`.readParcelable(Comics::class.java.classLoader)
        series = `in`.readParcelable(Series::class.java.classLoader)
        stories = `in`.readParcelable(Stories::class.java.classLoader)
        events = `in`.readParcelable(Events::class.java.classLoader)
        urls = `in`.createTypedArrayList(Url.CREATOR)
        digitalId = `in`.readInt()
        title = `in`.readString()
        issueNumber = `in`.readInt()
        variantDescription = `in`.readString()
        isbn = `in`.readString()
        upc = `in`.readString()
        diamondCode = `in`.readString()
        ean = `in`.readString()
        issn = `in`.readString()
        format = `in`.readString()
        pageCount = `in`.readInt()
        textObjects = ArrayList()
        `in`.readList(textObjects as ArrayList<TextObject?>, TextObject::class.java.classLoader)
        variants = `in`.createTypedArrayList(Series.CREATOR)
        collectedIssues = `in`.createTypedArrayList(Series.CREATOR)
        prices = `in`.createTypedArrayList(Price.CREATOR)
        images = `in`.createTypedArrayList(Thumbnail.CREATOR)
        creators = `in`.readParcelable(Creators::class.java.classLoader)
        characters = `in`.readParcelable(Creators::class.java.classLoader)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Result> = object : Parcelable.Creator<Result> {
            override fun createFromParcel(source: Parcel): Result {
                return Result(source)
            }

            override fun newArray(size: Int): Array<Result?> {
                return arrayOfNulls(size)
            }
        }
    }
}