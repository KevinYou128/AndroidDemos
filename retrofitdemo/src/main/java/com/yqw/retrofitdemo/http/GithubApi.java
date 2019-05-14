package com.yqw.retrofitdemo.http;

import com.yqw.retrofitdemo.bean.Contributors;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubApi {
    @GET("repos/{owner}/{repo}/contributors")
    Call<ResponseBody> getContributors(@Path("owner") String owner, @Path("repo") String repo);
    @GET("repos/{owner}/{repo}/contributors")
    Call<List<Contributors>> getContributorsBeans(@Path("owner") String owner, @Path("repo") String repo);
    @GET("github/contributors")
    Call<List<Contributors>> getContributorsBeans();
}
