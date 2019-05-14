package com.yqw.retrofitdemo.http;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GithubService {
//    public static final String API_URL = "https://api.github.com/";
    public static final String API_URL = " https://www.easy-mock.com/mock/5c416a800f1aa4017c9bf3b9/example/";

    public static GithubApi getApi(){
//        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                // 设置超时时间
//                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
//                // 设置读写时间
//                .readTimeout(10000L, TimeUnit.MILLISECONDS)
//                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
//                .client(okHttpClient)
                // 添加生成bean的工厂
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(GithubApi.class);
    }
}
