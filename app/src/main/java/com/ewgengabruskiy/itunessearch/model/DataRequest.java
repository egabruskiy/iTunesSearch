package com.ewgengabruskiy.itunessearch.model;

import com.ewgengabruskiy.itunessearch.api.ApiHolder;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class DataRequest {

    public Observable<AlbumsResultModel> getAlbums(String query) {
        Map<String, String> data = new HashMap<>();
        data.put("entity", "album");
        data.put("term", query);

        return ApiHolder.getInstance().getApi().getAlbumsList(data)
                .subscribeOn(Schedulers.io());
    }

    public  Observable<AlbumDetailResultModel> getAlbum(String collectionId) {
        Map<String, String> data = new HashMap<>();
        data.put("entity", "song");
        data.put("id", collectionId);

        return ApiHolder.getInstance().getApi().getAlbumDetail(data)
                .subscribeOn(Schedulers.io());
    }
}
