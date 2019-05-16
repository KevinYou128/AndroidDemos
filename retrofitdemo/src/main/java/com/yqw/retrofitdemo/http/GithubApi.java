package com.yqw.retrofitdemo.http;

import com.yqw.retrofitdemo.bean.Contributors;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface GithubApi {
    @GET("repos/{owner}/{repo}/contributors")
    Call<ResponseBody> getContributors(@Path("owner") String owner, @Path("repo") String repo);
    @GET("repos/{owner}/{repo}/contributors")
    Call<List<Contributors>> getContributorsBeans(@Path("owner") String owner, @Path("repo") String repo);

    @Headers({
            "Accept: application/vnd.github.v3.full+json",
            "User-Agent: RetrofitBean-Sample-App",
            "name:ljd"
    })
    @GET("repos/square/retrofit/contributors")
    Call<List<Contributors>> getContributorsBeans();

    @GET("search/repositories")
    Call<ResponseBody> getRepositories(@Query("q")String owner,
                                              @Query("since")String time,
                                              @Query("page")int page,
                                              @Query("per_page")int per_Page);
//    @GET("search/repositories")
//    Call<RetrofitBean> queryRetrofitByGetCall(@Query("q")String owner,
//                                              @Query("since")String time,
//                                              @Query("page")int page,
//                                              @Query("per_page")int per_Page);

    @GET("search/repositories")
    Call<ResponseBody> getRepositoriesByMap(@QueryMap Map<String,String> map);
//    @GET("search/repositories")
//    Call<RetrofitBean> queryRetrofitByGetCallMap(@QueryMap Map<String,String> map);

//    @FormUrlEncoded
//    @POST("upUser")
//    Call<List<Contributors>> postTestPostBeans(@Field("content") String content);
//    @FormUrlEncoded
//    @POST("upUser")
//    Call<ResponseBody> postTestPostBean(@Field("content") String content);

    @GET("repos/square/retrofit/contributors")
    Observable<List<Contributors>> getContributorsByRxjava();
}
