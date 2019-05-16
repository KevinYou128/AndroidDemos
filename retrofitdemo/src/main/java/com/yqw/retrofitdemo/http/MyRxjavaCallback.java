package com.yqw.retrofitdemo.http;

import android.content.Context;

import com.yqw.retrofitdemo.utils.LogUtil;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class MyRxjavaCallback<T> implements Observer<T> {
    private Context context;
//    private ObserverOnNextListener listener;

//    protected MyRxjavaCallback(Context context,ObserverOnNextListener listener) {
//        this.context = context;
//    }
    protected MyRxjavaCallback(Context context) {
        this.context = context;
    }

    @Override
    public void onSubscribe(Disposable d) {
        LogUtil.e("onSubscribe");
    }

    @Override
    public void onNext(T t) {
        onSuccessful(t);
    }

    @Override
    public void onError(Throwable e) {
        LogUtil.e("onError:"+e.getMessage());
    }

    @Override
    public void onComplete() {
        LogUtil.i("onComplete");
    }
    public abstract void onSuccessful(T t);
}
