package com.yqw.retrofitdemo.http;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GithubService {
//    public static final String API_URL = "https://api.github.com/";
    public static final String API_URL = "http://yapi.demo.qunar.com/mock/67388/";
//    public static final String API_URL = " https://www.easy-mock.com/mock/5c416a800f1aa4017c9bf3b9/example/";

    public static GithubApi getGithubApi() {
        return createRetrofitService(GithubApi.class);
    }

    public static <T> T createRetrofitService(final Class<T> service) {
//        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
//        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

//        OkHttpClient.Builder builder = new OkHttpClient.Builder()
//                .addInterceptor(httpLoggingInterceptor);

//        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                // 设置超时时间
//                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
//                // 设置读写时间
//                .readTimeout(10000L, TimeUnit.MILLISECONDS)
//                .build();

        Retrofit retrofit = new Retrofit.Builder()
                //这个是配合日志拦截器
//                .client(ProgressHelper.addProgress(builder).build())
                //这个是配置okhttp超时、读写时间设置
//                .client(okHttpClient)
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                //添加生成bean的工厂
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(API_URL)
                .build();

        return retrofit.create(service);
    }
}
