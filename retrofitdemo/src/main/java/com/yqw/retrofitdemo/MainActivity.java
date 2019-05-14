package com.yqw.retrofitdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.yqw.retrofitdemo.bean.Contributors;
import com.yqw.retrofitdemo.http.GithubService;
import com.yqw.retrofitdemo.http.MyRetrofitCallback;
import com.yqw.retrofitdemo.utils.LogUtil;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        updata4();
    }

    private void updata(){

        Call<ResponseBody> call = GithubService.getApi()
                .getContributors("square","retrofit");
        call.enqueue(new MyRetrofitCallback<ResponseBody>(this) {
            @Override
            public void onSuccessful(Call<ResponseBody> call, Response<ResponseBody> response) {
//                try {
//                    String result = response.body().string();
//                    LogUtil.e(result);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            }
        });
    }
    private void updata3(){

        Call<List<Contributors>> call = GithubService.getApi()
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
    private void updata4(){

        Call<List<Contributors>> call = GithubService.getApi()
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

//            @Override
//            public void onResponse(Call<List<Contributors>> call, Response<List<Contributors>> response) {
//                if (response.isSuccessful()){
//                    List<Contributors> contributors = response.body();
//                    LogUtil.e(contributors.get(0).getLogin());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Contributors>> call, Throwable t) {
//
//            }

        });
    }
    private void updata2(){

        Call<ResponseBody> call = GithubService.getApi()
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
