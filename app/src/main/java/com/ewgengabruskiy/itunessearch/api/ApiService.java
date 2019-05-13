package com.ewgengabruskiy.itunessearch.api;


import com.ewgengabruskiy.itunessearch.model.AlbumDetailResultModel;
import com.ewgengabruskiy.itunessearch.model.AlbumsResultModel;
import com.ewgengabruskiy.itunessearch.model.TrackModel;

import java.util.ArrayList;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface ApiService {

    @GET("search")
    Observable<AlbumsResultModel> getAlbumsList(@QueryMap Map<String,String> map);

    @GET("lookup")
    Observable<AlbumDetailResultModel> getAlbumDetail (@QueryMap Map<String,String> map);
}
