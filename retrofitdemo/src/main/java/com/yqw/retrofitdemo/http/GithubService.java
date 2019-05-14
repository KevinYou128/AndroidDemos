package com.yqw.retrofitdemo.http;

import retrofit2.Retrofit;

public class GithubService {
    public static final String API_URL = "https://api.github.com/";

    public static GithubApi getApi(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .build();
        return retrofit.create(GithubApi.class);
    }
}
