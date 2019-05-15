package com.yqw.retrofitdemo.http;

import com.yqw.retrofitdemo.bean.Contributors;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface MockApi {

    @FormUrlEncoded
    @POST("upUser")
    Call<List<Contributors>> postTestPostBeans(@Field("content") String content);
    @FormUrlEncoded
    @POST("upUser")
    Call<ResponseBody> postTestPostBean(@Field("content") String content);
}
