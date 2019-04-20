package com.andrew.samir.manastmovies.Data.PersonImagesResponseData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PersonImagesResponseData {

    @Expose
    @SerializedName("id")
    private int id;
    @Expose
    @SerializedName("profiles")
    private List<Profiles> profiles;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Profiles> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<Profiles> profiles) {
        this.profiles = profiles;
    }
}
