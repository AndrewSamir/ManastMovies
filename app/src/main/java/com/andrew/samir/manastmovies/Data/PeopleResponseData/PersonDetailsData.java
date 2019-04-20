package com.andrew.samir.manastmovies.Data.PeopleResponseData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PersonDetailsData {
    @Expose
    @SerializedName("adult")
    private boolean adult;
    @Expose
    @SerializedName("known_for")
    private List<KnownFor> knownFor;
    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("profile_path")
    private String profilePath;
    @Expose
    @SerializedName("id")
    private int id;
    @Expose
    @SerializedName("popularity")
    private double popularity;

    public boolean getAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public List<KnownFor> getKnownFor() {
        return knownFor;
    }

    public void setKnownFor(List<KnownFor> knownFor) {
        this.knownFor = knownFor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }
}
