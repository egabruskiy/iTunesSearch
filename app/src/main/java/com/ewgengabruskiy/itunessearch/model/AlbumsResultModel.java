package com.ewgengabruskiy.itunessearch.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.List;

public class AlbumsResultModel {

    @SerializedName("resultCount")
    @Expose
    private Integer resultCount;

    @SerializedName("results")
    @Expose
    private List<AlbumModel> albums = null;



    public Integer getResultCount() {
        return resultCount;
    }


    public List<AlbumModel> getAlbums() {
        // Sort Alphabetically
        if (albums.size() > 0) {
            Collections.sort(albums, (object1, object2) ->
                    object1.getCollectionName().compareTo(object2.getCollectionName()));
        }
        return albums;
    }

}
