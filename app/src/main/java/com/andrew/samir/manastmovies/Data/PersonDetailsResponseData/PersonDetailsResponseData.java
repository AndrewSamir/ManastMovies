package com.andrew.samir.manastmovies.Data.PersonDetailsResponseData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PersonDetailsResponseData {

    @Expose
    @SerializedName("homepage")
    private String homepage;
    @Expose
    @SerializedName("imdb_id")
    private String imdbId;
    @Expose
    @SerializedName("adult")
    private boolean adult;
    @Expose
    @SerializedName("profile_path")
    private String profilePath;
    @Expose
    @SerializedName("place_of_birth")
    private String placeOfBirth;
    @Expose
    @SerializedName("popularity")
    private double popularity;
    @Expose
    @SerializedName("biography")
    private String biography;
    @Expose
    @SerializedName("gender")
    private int gender;
    @Expose
    @SerializedName("also_known_as")
    private List<String> alsoKnownAs;
    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("id")
    private int id;
    @Expose
    @SerializedName("deathday")
    private String deathday;
    @Expose
    @SerializedName("known_for_department")
    private String knownForDepartment;
    @Expose
    @SerializedName("birthday")
    private String birthday;

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public boolean getAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getPopularity() {
        return popularity+"";
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public List<String> getAlsoKnownAs() {
        return alsoKnownAs;
    }

    public void setAlsoKnownAs(List<String> alsoKnownAs) {
        this.alsoKnownAs = alsoKnownAs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeathday() {
        return deathday;
    }

    public void setDeathday(String deathday) {
        this.deathday = deathday;
    }

    public String getKnownForDepartment() {
        return knownForDepartment;
    }

    public void setKnownForDepartment(String knownForDepartment) {
        this.knownForDepartment = knownForDepartment;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
