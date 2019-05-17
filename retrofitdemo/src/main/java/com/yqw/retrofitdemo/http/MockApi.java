package com.yqw.retrofitdemo.http;

import com.yqw.retrofitdemo.bean.Contributors;
import com.yqw.retrofitdemo.bean.Contributorsss;
import com.yqw.retrofitdemo.bean.PicturesBean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * 模拟的接口 这里主要用来测试post请求
 */
public interface MockApi {

    @FormUrlEncoded
    @POST("upUser")
    Call<List<Contributors>> postTestPostBeans(@Field("content") String content);
    @FormUrlEncoded
    @POST("upUser")
    Call<ResponseBody> postTestPostBean(@Field("content") String content);

    @FormUrlEncoded
    @POST("upUser")
    Observable<List<Contributorsss>> postTestPostBeanByRxjava(@Field("content") String content);

    @Multipart
    @POST("upImage")
    Observable<PicturesBean> getPicturesBean(@Part("description") RequestBody description, @Part MultipartBody.Part file);
}
