package com.yqw.retrofitdemo.http;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubApi {
    @GET("repos/{owner}/{repo}/contributors")
    Call<ResponseBody> getContributors(@Path("owner") String owner, @Path("repo") String repo);
}
