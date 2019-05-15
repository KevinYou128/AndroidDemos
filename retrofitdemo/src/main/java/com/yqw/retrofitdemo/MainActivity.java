package com.yqw.retrofitdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.yqw.retrofitdemo.bean.Contributors;
import com.yqw.retrofitdemo.bean.TestPostBean;
import com.yqw.retrofitdemo.http.GithubService;
import com.yqw.retrofitdemo.http.MyRetrofitCallback;
import com.yqw.retrofitdemo.utils.LogUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        updata5();
    }

    /**
     * 封装后调用
     * 传递path数据
     */
    private void updata2(){

        Call<List<Contributors>> call = GithubService.getGithubApi()
                .getContributorsBeans("square","retrofit");
        call.enqueue(new MyRetrofitCallback<List<Contributors>>(this) {
            @Override
            public void onSuccessful(Call<List<Contributors>> call, Response<List<Contributors>> response) {
//                try {
//                    String result = response.body().string();
//                    LogUtil.e(result);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            }
        });
    }

    /**
     * 封装后调用
     * 不传递数据
     */
    private void updata3(){

        Call<List<Contributors>> call = GithubService.getGithubApi()
                .getContributorsBeans();
        call.enqueue(new MyRetrofitCallback<List<Contributors>>(this) {
            @Override
            public void onSuccessful(Call<List<Contributors>> call, Response<List<Contributors>> response) {
                for (Contributors contributors:response.body()){
                    if (!TextUtils.isEmpty(contributors.getLogin())){
                        LogUtil.e(contributors.getLogin());
                    }
                }
            }
        });
    }
    /**
     * 封装后调用
     * 传递Query数据
     */
    private void updata4_1(){
        Call<ResponseBody> call = GithubService.getGithubApi()
                .getRepositories("retrofit", "2016-03-29", 1, 3);
        call.enqueue(new MyRetrofitCallback<ResponseBody>(this) {
            @Override
            public void onSuccessful(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    String result = response.body().string();
                    LogUtil.e(result);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    /**
     * 封装后调用
     * 传递Query数据 使用map
     */
    private void updata4_2(){
        Map<String,String> queryMap = new HashMap<>();
        queryMap.put("q", "retrofit");
        queryMap.put("since","2016-03-29");
        queryMap.put("page","1");
        queryMap.put("per_page", "3");
        Call<ResponseBody> call = GithubService.getGithubApi()
                .getRepositoriesByMap(queryMap);
        call.enqueue(new MyRetrofitCallback<ResponseBody>(this) {
            @Override
            public void onSuccessful(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String result = response.body().string();
                    LogUtil.e(result);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    /**
     * 封装后调用
     * post
     */
    private void updata5(){

        Call<List<Contributors>> call = GithubService.getGithubApi()
                .postTestPostBean(new TestPostBean("a","a"));
        call.enqueue(new MyRetrofitCallback<List<Contributors>>(this) {
            @Override
            public void onSuccessful(Call<List<Contributors>> call, Response<List<Contributors>> response) {
                for (Contributors contributors:response.body()){
                    if (!TextUtils.isEmpty(contributors.getLogin())){
                        LogUtil.e(contributors.getLogin());
                    }
                }
            }
        });
    }

    /**
     * 原始调用
     */
    private void updata(){
        //todo 这里可以用ResponseBody返回json串自己处理解析json
        //todo 也可以使用Bean自动解析json串
        Call<ResponseBody> call = GithubService.getGithubApi()
                .getContributors("square","retrofit");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        String result = response.body().string();
                        LogUtil.e(result);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
