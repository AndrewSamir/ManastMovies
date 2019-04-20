package com.andrew.samir.manastmovies.Data.PersonImagesResponseData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Profiles {
    @Expose
    @SerializedName("aspect_ratio")
    private double aspectRatio;
    @Expose
    @SerializedName("file_path")
    private String filePath;
    @Expose
    @SerializedName("vote_average")
    private double voteAverage;
    @Expose
    @SerializedName("vote_count")
    private int voteCount;
    @Expose
    @SerializedName("height")
    private int height;
    @Expose
    @SerializedName("width")
    private int width;
    @Expose
    @SerializedName("iso_639_1")
    private String iso6391;

    public double getAspectRatio() {
        return aspectRatio;
    }

    public void setAspectRatio(double aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getIso6391() {
        return iso6391;
    }

    public void setIso6391(String iso6391) {
        this.iso6391 = iso6391;
    }
}
