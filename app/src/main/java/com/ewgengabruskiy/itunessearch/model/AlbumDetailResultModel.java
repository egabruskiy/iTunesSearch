package com.ewgengabruskiy.itunessearch.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AlbumDetailResultModel {

    @SerializedName("resultCount")
    @Expose
    private Integer resultCount;

    @SerializedName("results")
    @Expose
    private List<TrackModel> tracks = null;



    public Integer getResultCount() {
        return resultCount;
    }


    public List<TrackModel> getTracks() {
        return tracks;
    }
}


