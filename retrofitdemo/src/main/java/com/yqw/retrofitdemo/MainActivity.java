package com.yqw.retrofitdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.yqw.retrofitdemo.http.GithubService;
import com.yqw.retrofitdemo.http.MyRetrofitCallback;
import com.yqw.retrofitdemo.utils.LogUtil;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        updata();
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
