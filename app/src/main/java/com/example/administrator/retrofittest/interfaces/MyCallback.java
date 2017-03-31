package com.example.administrator.retrofittest.interfaces;

import android.util.Log;

import com.example.administrator.retrofittest.bean.BaseCallModel;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.security.AlgorithmConstraints;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/3/31 0031.
 */

public abstract class MyCallback<T extends BaseCallModel> implements Callback<T> {
    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            //200是服务器有合理响应
            if(response.body().ret == 200){
                onSuc(response);
            }else if (response.body().ret == 1){
            }
            else if (response.body().ret == 2){
                //登录失效，重新登录
                onAutoLogin();
            } else {
                onFail(response.body().msg);
            }

        } else {//失败响应
            onFailure(call, new RuntimeException("response error,detail = " + response.raw().toString()));
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {//网络问题会走该回调
        Log.d("vivi","onFailure");
        if(t instanceof SocketTimeoutException){
            //
        }else if(t instanceof ConnectException){
            //
        }else if(t instanceof RuntimeException){
            //
        }
        onFail(t.getMessage());
    }

    public abstract void onSuc(Response<T> response);

    public abstract void onFail(String message);

    public abstract void onAutoLogin();

}

