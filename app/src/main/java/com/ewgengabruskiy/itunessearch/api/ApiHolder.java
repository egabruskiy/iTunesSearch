package com.ewgengabruskiy.itunessearch.api;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiHolder {

    private static ApiHolder instance = new ApiHolder();

    public static ApiHolder getInstance() {
        if (instance == null) {
            instance = new ApiHolder();
        }
        return instance;
    }

    private ApiService api;

    private ApiHolder() {
        api = new Retrofit.Builder()
                .baseUrl("https://itunes.apple.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()))
                .build()
                .create(ApiService.class);
    }

    public ApiService getApi() {
        return api;
    }

}
