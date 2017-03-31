package com.example.administrator.retrofittest.bean;

/**
 * Created by Administrator on 2017/3/24 0024.
 */

public class UploadData {
//    {"ret":200,"msg":"有心课堂,传递给你的不仅仅是技术✈️","data":
//        [{"url":"uploads/test2.png","filename":"test2.png"},{"url":"uploads/test.png","filename":"test.png"}]}

    public String url;
    public String filename;

    public UploadData(String url, String filename) {
        this.url = url;
        this.filename = filename;
    }

    @Override
    public String toString() {
        return "UploadData{" +
                "url='" + url + '\'' +
                ", filename='" + filename + '\'' +
                '}';
    }
}
