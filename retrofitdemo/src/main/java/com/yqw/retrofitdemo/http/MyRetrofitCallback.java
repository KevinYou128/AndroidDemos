package com.yqw.retrofitdemo.http;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.yqw.retrofitdemo.utils.DialogUtils;
import com.yqw.retrofitdemo.utils.LogUtil;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class MyRetrofitCallback<T> implements Callback<T>{
    private Context context;

    protected MyRetrofitCallback(Context context) {
        this.context = context;
    }

    public abstract void onSuccessful(Call<T> call, Response<T> response);

    private void onSuccess(Response<ResponseBody> response){
        try {
            String result = response.body().string();
            LogUtil.e(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void onFail(Call<T> call,Throwable t,Response<T> response){
        if (null == response){
            Toast.makeText(context,t.toString(),Toast.LENGTH_SHORT).show();
            return;
        }
        LogUtil.e("RESPONSE code is "+response.code()+": "+ response.raw().toString());
        DialogUtils.showPrompt(context,response.message());
        if (null != response.errorBody()){
            //解析后台返回的错误信息
//            ErrorEntity errorEntity = new ErrorEntity();
//            try {
//                errorEntity = ErrorEntity.parse(response.errorBody().string());
//            } catch (IOException e) {
//                Log.e(TAG, "ErrorEntity解析错误:" + e.getMessage());
//            }
//            String message;
//            if (errorEntity.getErrorMessage() != null) {
//                message = errorEntity.getErrorMessage();
//            }else {
//                message ="账号已过期，请重新登录";
//            }
            // errorEntity.getErrorCode() 获取后台返回的 errorCode，根据 errorCode 前端做相应的处理
        }
    }

    @Override
    public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
        if (200 == response.code()){
            onSuccess((Response<ResponseBody>) response);
            onSuccessful(call,response);
        }else {
            onFail(call,null,response);
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        onFail(call,t,null);
    }
}
