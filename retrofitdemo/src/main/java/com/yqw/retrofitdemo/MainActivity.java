package com.yqw.retrofitdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;

import com.yqw.retrofitdemo.bean.Contributors;
import com.yqw.retrofitdemo.bean.Contributorsss;
import com.yqw.retrofitdemo.http.GithubService;
import com.yqw.retrofitdemo.http.MyRetrofitCallback;
import com.yqw.retrofitdemo.http.MyRxjavaCallback;
import com.yqw.retrofitdemo.utils.DialogUtils;
import com.yqw.retrofitdemo.utils.GsonUtil;
import com.yqw.retrofitdemo.utils.LogUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.OnClick;
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
        ButterKnife.bind(this);

    }


    @OnClick({R.id.bt_get_retrofit2,
    R.id.bt_get_retrofit2_s,
    R.id.bt_post_retrofit2_s,
    R.id.bt_get_retrofit2_rx,
    R.id.bt_get_retrofit2_rx_s,
    R.id.bt_post_retrofit2_rx_s,
    R.id.bt_up_retrofit2_rx_s,
    R.id.bt_down_retrofit2_rx})
    void onClickButton(View v){
        switch (v.getId()){
            case R.id.bt_get_retrofit2:
                //原始调用 get
                updata();
                break;
            case R.id.bt_get_retrofit2_s:
                //封装后调用 get
                updata2();
                break;
            case R.id.bt_post_retrofit2_s:
                //封装后调用 post
                updata5();
                break;
            case R.id.bt_get_retrofit2_rx:
                //retrofit2+rxjava get
                updataRxjava2();
                break;
            case R.id.bt_get_retrofit2_rx_s:
                //retrofit2+rxjava get 封装
                updataRxjava2_s();
                break;
            case R.id.bt_post_retrofit2_rx_s:
                //retrofit2+rxjava post 封装
                break;
            case R.id.bt_up_retrofit2_rx_s:
                //retrofit2+rxjava 上传 封装
                break;
            case R.id.bt_down_retrofit2_rx:
                //retrofit2+rxjava 下载 封装
                break;
        }
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
                DialogUtils.showPrompt(MainActivity.this,"list的第一个数据："
                +response.body().get(0).toString());
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
                    DialogUtils.showPrompt(MainActivity.this,result);

                    LogUtil.e(result);
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });
    }


    /**
     * 配合rxjava2的网络请求
     * 封装
     */
    private void updataRxjava2_s(){
        GithubService.getGithubApi().getContributorsByRxjava()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyRxjavaCallback<List<Contributors>>(this) {
                    @Override
                    public void onSuccessful(List<Contributors> contributors) {
                        LogUtil.e(contributors.toString());
                        LogUtil.e(Thread.currentThread().getName());
                        DialogUtils.showPrompt(MainActivity.this,"成功后第一个值："+contributors.get(0).getLogin());
                    }
                });
    }
    /**
     * 配合rxjava2的网络请求
     */
    private void updataRxjava2(){
        GithubService.getGithubApi().getContributorsByRxjava()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Contributors>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtil.e("onSubscribe");
                    }

                    @Override
                    public void onNext(List<Contributors> contributors) {
                        LogUtil.e(contributors.toString());
                        LogUtil.e(Thread.currentThread().getName());
                        DialogUtils.showPrompt(MainActivity.this,"成功后第一个值："+contributors.get(0).getLogin());
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e("onError");
                    }

                    @Override
                    public void onComplete() {
                        LogUtil.e("onComplete");
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
                        DialogUtils.showPrompt(MainActivity.this,result);
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
