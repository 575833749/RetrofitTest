package com.example.administrator.retrofittest;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.administrator.retrofittest.api.UploadService;
import com.example.administrator.retrofittest.bean.BaseCallModel;
import com.example.administrator.retrofittest.bean.UploadData;
import com.example.administrator.retrofittest.interfaces.MyCallback;
import com.example.administrator.retrofittest.net.RetrofitManage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadFile();
            }
        });

    }

    /**
     * 多文件上传
     */
    private void uploadFile(){
        String path1 = Environment.getExternalStorageDirectory() + File.separator + "test.png";
        String path2 = Environment.getExternalStorageDirectory() + File.separator + "test2.png";
        UploadService service = RetrofitManage.getInstance().createService(UploadService.class);
        File file = new File(path1);
        File file2 = new File(path2);
        MediaType mediaType=MediaType.parse("multipart/form-data");
        final RequestBody requestBody = RequestBody.create(mediaType, file);
        final RequestBody requestBody2 = RequestBody.create(mediaType, file2);
        MultipartBody.Part part = MultipartBody.Part.createFormData("file0", file.getName(), requestBody);
        MultipartBody.Part part2 = MultipartBody.Part.createFormData("file1", file2.getName(), requestBody2);
        List<MultipartBody.Part> parts=new ArrayList<>();
        parts.add(part);
        parts.add(part2);
        Call<BaseCallModel<List<UploadData>>> upload = service.upload(parts);
        RetrofitManage.getInstance().enqueue(upload, new MyCallback<BaseCallModel<List<UploadData>>>() {
            @Override
            public void onSuc(Response<BaseCallModel<List<UploadData>>> response) {

                List<UploadData> data = response.body().data;
                for (int i = 0 ; i<data.size();i++){
                    Log.d("vivi","---"+data.get(i));
                }
            }

            @Override
            public void onFail(String message) {

            }

            @Override
            public void onAutoLogin() {

            }
        });
    }
}
