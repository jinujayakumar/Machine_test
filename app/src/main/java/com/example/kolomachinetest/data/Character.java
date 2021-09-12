package com.example.kolomachinetest.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Character implements Parcelable {

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
        dest.writeList(this.urls);
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
        this.urls = new ArrayList<>();
        source.readList(this.urls, Url.class.getClassLoader());
    }

    public Character() {
    }

    protected Character(Parcel in) {
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
        this.urls = new ArrayList<>();
        in.readList(this.urls, Url.class.getClassLoader());
    }

    public static final Parcelable.Creator<Character> CREATOR = new Parcelable.Creator<Character>() {
        @Override
        public Character createFromParcel(Parcel source) {
            return new Character(source);
        }

        @Override
        public Character[] newArray(int size) {
            return new Character[size];
        }
    };
}
