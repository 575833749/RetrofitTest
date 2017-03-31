package com.example.administrator.retrofittest.api;

import com.example.administrator.retrofittest.bean.BaseCallModel;
import com.example.administrator.retrofittest.bean.UploadData;
import com.example.administrator.retrofittest.bean.User;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by Administrator on 2017/3/31 0031.
 */

public interface UploadService {
    /**
     * 多文件上传
     */
    @Multipart
    @POST("v1/public/core/?service=user.updateAvatar")
    Call<BaseCallModel<List<UploadData>>> upload(@Part List<MultipartBody.Part> parts);

}
