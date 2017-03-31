package com.example.administrator.retrofittest.net;

import com.example.administrator.retrofittest.bean.BaseCallModel;
import com.example.administrator.retrofittest.interfaces.MyCallback;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/3/31 0031.
 */

public class RetrofitManage {

    public   Retrofit retrofit;
    private RetrofitManage(){
        initRetrofit();
    }
    public static RetrofitManage getInstance(){
        return Singleton.singleton;
    }
    private static class Singleton{
        protected static RetrofitManage singleton = new RetrofitManage();
    }
    private void initRetrofit(){
        String base_url="http://api.stay4it.com/";
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
         retrofit = new Retrofit.Builder().baseUrl(base_url).client(okHttpClient).addConverterFactory(GsonConverterFactory.create()).build();
    }


    public  <T> T createService(Class<T>t){
        return retrofit.create(t);
    }

    public void enqueue(Call call,MyCallback callback){
        call.enqueue(callback);
    }
}
