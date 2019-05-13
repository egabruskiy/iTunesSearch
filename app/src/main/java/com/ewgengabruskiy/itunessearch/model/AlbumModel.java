package com.ewgengabruskiy.itunessearch.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AlbumModel implements Serializable {

    @SerializedName("artistId")
    @Expose
    private Integer artistId;

    @SerializedName("amgArtistId")
    @Expose
    private Integer amgArtistId;

    @SerializedName("artistName")
    @Expose
    private String artistName;

    @SerializedName("collectionName")
    @Expose
    private String collectionName;

    @SerializedName("collectionId")
    @Expose
    private Integer collectionId;

    @SerializedName("artistViewUrl")
    @Expose
    private String artistViewUrl;

    @SerializedName("artworkUrl100")
    @Expose
    private String   artworkUrl100;

    @SerializedName("collectionPrice")
    @Expose
    private String collectionPrice;

    @SerializedName("currency")
    @Expose
    private String currency;

    @SerializedName("primaryGenreName")
    @Expose
    private String primaryGenreName;

    @SerializedName("releaseDate")
    @Expose
    private String releaseDate;


    @SerializedName("trackCount")
    @Expose
    private Integer trackCount;



    public String getReleaseDate() {
        return releaseDate.substring( 0 , 4 );
    }

    public Integer getArtistId() {
        return artistId;
    }

    public Integer getAmgArtistId() {
        return amgArtistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public Integer getCollectionId() {
        return collectionId;
    }

    public String getArtistViewUrl() {
        return artistViewUrl;
    }

    public String getArtworkUrl100() {
        return artworkUrl100;
    }

    public String getCollectionPrice() {
        return collectionPrice;
    }

    public String getCurrency() {
        return currency;
    }

    public String getPrimaryGenreName() {
        return primaryGenreName;
    }

    public Integer getTrackCount() {
        return trackCount;
    }
}

