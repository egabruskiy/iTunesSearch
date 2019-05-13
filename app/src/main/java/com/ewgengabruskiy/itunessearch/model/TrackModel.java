package com.ewgengabruskiy.itunessearch.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.concurrent.TimeUnit;

public class  TrackModel {

    @SerializedName("trackName")
    @Expose
    private String trackName;

    @SerializedName("trackViewUrl")
    @Expose
    private String trackViewUrl;

    @SerializedName("trackPrice")
    @Expose
    private String trackPrice;

    @SerializedName("artistName")
    @Expose
    private String artistName;

    @SerializedName("artworkUrl30")
    @Expose
    private String artworkUrl30;

    @SerializedName("trackTimeMillis")
    @Expose
    private Long time;



    public String getTrackName() {
        return trackName;
    }

    public String getTrackViewUrl() {
        return trackViewUrl;
    }

    public String getTrackPrice() {
        return trackPrice;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getTime() {
        return msToHHMMSS(time);
    }

    public String getArtworkUrl30() {
        return artworkUrl30;
    }


    private String msToHHMMSS(Long ms){
        Long hour = TimeUnit.MILLISECONDS.toHours(ms);
       Long min = TimeUnit.MILLISECONDS.toMinutes(ms) - TimeUnit.HOURS.toMinutes(hour);
       Long sec = TimeUnit.MILLISECONDS.toSeconds(ms) - TimeUnit.MINUTES.toSeconds(min);

        if ( ms >= 3600000) {
            return "$hour"+":"+"$min"+":"+"$sec";
        }
        return "$min"+":"+"$sec";
    }

}



