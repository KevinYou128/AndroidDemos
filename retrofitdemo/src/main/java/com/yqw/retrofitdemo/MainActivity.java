package com.yqw.retrofitdemo;

import android.graphics.Movie;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.yqw.retrofitdemo.bean.Contributors;
import com.yqw.retrofitdemo.bean.Contributorsss;
import com.yqw.retrofitdemo.http.Api;
import com.yqw.retrofitdemo.http.GithubService;
import com.yqw.retrofitdemo.http.MyRetrofitCallback;
import com.yqw.retrofitdemo.utils.GsonUtil;
import com.yqw.retrofitdemo.utils.LogUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        updata3();
    }

    /**
     * 封装后调用
     * 传递path数据
     */
    private void updata2() {

        Call<List<Contributors>> call = GithubService.getGithubApi()
                .getContributorsBeans("square", "retrofit");
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
    private void updata3() {

        Call<List<Contributors>> call = GithubService.getGithubApi()
                .getContributorsBeans();
        call.enqueue(new MyRetrofitCallback<List<Contributors>>(this) {
            @Override
            public void onSuccessful(Call<List<Contributors>> call, Response<List<Contributors>> response) {
                for (Contributors contributors : response.body()) {
                    if (!TextUtils.isEmpty(contributors.getLogin())) {
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
    private void updata4_1() {
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
    private void updata4_2() {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("q", "retrofit");
        queryMap.put("since", "2016-03-29");
        queryMap.put("page", "1");
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
     * post 调用mock接口
     */
    private void updata5() {

        Call<ResponseBody> call = GithubService.getMockApi()
                .postTestPostBean("contentcontent");
        call.enqueue(new MyRetrofitCallback<ResponseBody>(this) {
            @Override
            public void onSuccessful(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    //这里之所以不直接用Bean解析，是因为post传递数据如果跟服务器没有沟通好，可能出现解析异常
                    String result = response.body().string();
                    List<Contributorsss> list = GsonUtil.jsonToList(result, Contributorsss.class);

                    LogUtil.e(result);
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });
    }

    private void updataRxjava() {

        Api.getApiService().getTopMovie(0, 10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Movie>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtil.e("onSubscribe: ");
                    }

                    @Override
                    public void onNext(Movie movie) {
//                        LogUtil.e("onNext: " + movie.getTitle());
//                        List<Subjects> list = movie.getSubjects();
//                        for (Subjects sub : list) {
//                            Log.d(TAG, "onNext: " + sub.getId() + "," + sub.getYear() + "," + sub.getTitle());
//                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e("onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        LogUtil.e("onComplete: Over!");
                    }
                });

    }

    /**
     * 原始调用
     */
    private void updata() {
        //todo 这里可以用ResponseBody返回json串自己处理解析json
        //todo 也可以使用Bean自动解析json串
        Call<ResponseBody> call = GithubService.getGithubApi()
                .getContributors("square", "retrofit");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
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
