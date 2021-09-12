package com.example.kolomachinetest.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Result implements Parcelable {

    @SerializedName("id")
    public int id;

    @SerializedName("name")
    public String name;

    @SerializedName("description")
    public String description;

    @SerializedName("modified")
    public String modified;

    @SerializedName("thumbnail")
    public Thumbnail thumbnail;

    @SerializedName("resourceURI")
    public String resourceURI;

    @SerializedName("comics")
    public Comics comics;

    @SerializedName("series")
    public Series series;

    @SerializedName("stories")
    public Stories stories;

    @SerializedName("events")
    public Events events;

    @SerializedName("urls")
    public List<Url> urls;

    @SerializedName("digitalId")
    public int digitalId;

    @SerializedName("title")
    public String title;

    @SerializedName("issueNumber")
    public int issueNumber;

    @SerializedName("variantDescription")
    public String variantDescription;

    @SerializedName("isbn")
    public String isbn;

    @SerializedName("upc")
    public String upc;

    @SerializedName("diamondCode")
    public String diamondCode;

    @SerializedName("ean")
    public String ean;

    @SerializedName("issn")
    public String issn;

    @SerializedName("format")
    public String format;

    @SerializedName("pageCount")
    public int pageCount;

    @SerializedName("textObjects")
    public List<TextObject> textObjects;

    @SerializedName("variants")
    public List<Series> variants;

    @SerializedName("collectedIssues")
    public List<Series> collectedIssues;

    @SerializedName("prices")
    public List<Price> prices;

    @SerializedName("images")
    public List<Thumbnail> images;

    @SerializedName("creators")
    public Creators creators;

    @SerializedName("characters")
    public Creators characters;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getResourceURI() {
        return resourceURI;
    }

    public void setResourceURI(String resourceURI) {
        this.resourceURI = resourceURI;
    }

    public Comics getComics() {
        return comics;
    }

    public void setComics(Comics comics) {
        this.comics = comics;
    }

    public Series getSeries() {
        return series;
    }

    public void setSeries(Series series) {
        this.series = series;
    }

    public Stories getStories() {
        return stories;
    }

    public void setStories(Stories stories) {
        this.stories = stories;
    }

    public Events getEvents() {
        return events;
    }

    public void setEvents(Events events) {
        this.events = events;
    }

    public List<Url> getUrls() {
        return urls;
    }

    public void setUrls(List<Url> urls) {
        this.urls = urls;
    }

    public int getDigitalId() {
        return digitalId;
    }

    public void setDigitalId(int digitalId) {
        this.digitalId = digitalId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(int issueNumber) {
        this.issueNumber = issueNumber;
    }

    public String getVariantDescription() {
        return variantDescription;
    }

    public void setVariantDescription(String variantDescription) {
        this.variantDescription = variantDescription;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public String getDiamondCode() {
        return diamondCode;
    }

    public void setDiamondCode(String diamondCode) {
        this.diamondCode = diamondCode;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public String getIssn() {
        return issn;
    }

    public void setIssn(String issn) {
        this.issn = issn;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public List<TextObject> getTextObjects() {
        return textObjects;
    }

    public void setTextObjects(List<TextObject> textObjects) {
        this.textObjects = textObjects;
    }

    public List<Series> getVariants() {
        return variants;
    }

    public void setVariants(List<Series> variants) {
        this.variants = variants;
    }

    public List<Series> getCollectedIssues() {
        return collectedIssues;
    }

    public void setCollectedIssues(List<Series> collectedIssues) {
        this.collectedIssues = collectedIssues;
    }

    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }

    public List<Thumbnail> getImages() {
        return images;
    }

    public void setImages(List<Thumbnail> images) {
        this.images = images;
    }

    public Result() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeString(this.modified);
        dest.writeParcelable(this.thumbnail, flags);
        dest.writeString(this.resourceURI);
        dest.writeParcelable(this.comics, flags);
        dest.writeParcelable(this.series, flags);
        dest.writeParcelable(this.stories, flags);
        dest.writeParcelable(this.events, flags);
        dest.writeTypedList(this.urls);
        dest.writeInt(this.digitalId);
        dest.writeString(this.title);
        dest.writeInt(this.issueNumber);
        dest.writeString(this.variantDescription);
        dest.writeString(this.isbn);
        dest.writeString(this.upc);
        dest.writeString(this.diamondCode);
        dest.writeString(this.ean);
        dest.writeString(this.issn);
        dest.writeString(this.format);
        dest.writeInt(this.pageCount);
        dest.writeList(this.textObjects);
        dest.writeTypedList(this.variants);
        dest.writeTypedList(this.collectedIssues);
        dest.writeTypedList(this.prices);
        dest.writeTypedList(this.images);
        dest.writeParcelable(this.creators, flags);
        dest.writeParcelable(this.characters, flags);
    }

    public void readFromParcel(Parcel source) {
        this.id = source.readInt();
        this.name = source.readString();
        this.description = source.readString();
        this.modified = source.readString();
        this.thumbnail = source.readParcelable(Thumbnail.class.getClassLoader());
        this.resourceURI = source.readString();
        this.comics = source.readParcelable(Comics.class.getClassLoader());
        this.series = source.readParcelable(Series.class.getClassLoader());
        this.stories = source.readParcelable(Stories.class.getClassLoader());
        this.events = source.readParcelable(Events.class.getClassLoader());
        this.urls = source.createTypedArrayList(Url.CREATOR);
        this.digitalId = source.readInt();
        this.title = source.readString();
        this.issueNumber = source.readInt();
        this.variantDescription = source.readString();
        this.isbn = source.readString();
        this.upc = source.readString();
        this.diamondCode = source.readString();
        this.ean = source.readString();
        this.issn = source.readString();
        this.format = source.readString();
        this.pageCount = source.readInt();
        this.textObjects = new ArrayList<>();
        source.readList(this.textObjects, TextObject.class.getClassLoader());
        this.variants = source.createTypedArrayList(Series.CREATOR);
        this.collectedIssues = source.createTypedArrayList(Series.CREATOR);
        this.prices = source.createTypedArrayList(Price.CREATOR);
        this.images = source.createTypedArrayList(Thumbnail.CREATOR);
        this.creators = source.readParcelable(Creators.class.getClassLoader());
        this.characters = source.readParcelable(Creators.class.getClassLoader());
    }

    protected Result(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.description = in.readString();
        this.modified = in.readString();
        this.thumbnail = in.readParcelable(Thumbnail.class.getClassLoader());
        this.resourceURI = in.readString();
        this.comics = in.readParcelable(Comics.class.getClassLoader());
        this.series = in.readParcelable(Series.class.getClassLoader());
        this.stories = in.readParcelable(Stories.class.getClassLoader());
        this.events = in.readParcelable(Events.class.getClassLoader());
        this.urls = in.createTypedArrayList(Url.CREATOR);
        this.digitalId = in.readInt();
        this.title = in.readString();
        this.issueNumber = in.readInt();
        this.variantDescription = in.readString();
        this.isbn = in.readString();
        this.upc = in.readString();
        this.diamondCode = in.readString();
        this.ean = in.readString();
        this.issn = in.readString();
        this.format = in.readString();
        this.pageCount = in.readInt();
        this.textObjects = new ArrayList<>();
        in.readList(this.textObjects, TextObject.class.getClassLoader());
        this.variants = in.createTypedArrayList(Series.CREATOR);
        this.collectedIssues = in.createTypedArrayList(Series.CREATOR);
        this.prices = in.createTypedArrayList(Price.CREATOR);
        this.images = in.createTypedArrayList(Thumbnail.CREATOR);
        this.creators = in.readParcelable(Creators.class.getClassLoader());
        this.characters = in.readParcelable(Creators.class.getClassLoader());
    }

    public static final Parcelable.Creator<Result> CREATOR = new Parcelable.Creator<Result>() {
        @Override
        public Result createFromParcel(Parcel source) {
            return new Result(source);
        }

        @Override
        public Result[] newArray(int size) {
            return new Result[size];
        }
    };
}
