package com.example.jason.okhttpdemo.okhttp;

import com.example.jason.okhttpdemo.okhttp.listener.DisposeDataHandle;
import com.example.jason.okhttpdemo.okhttp.response.CommonJsonCallback;
import com.example.jason.okhttpdemo.okhttp.ssl.HttpsUtils;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import static com.example.jason.okhttpdemo.okhttp.CommonConsts.TIME_OUT;

/**
 * Created by jason on 19-3-1.
 * 发送GET、POST等请求
 * ssl认证、处理请求超时等
 */

public class CommonOkHttpClient {
    private static OkHttpClient mOkHttpClient;

    static {
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
        //对https的认证
        okHttpBuilder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String s, SSLSession sslSession) {
                return true;//信任所有证书
            }
        });
        okHttpBuilder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpBuilder.readTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpBuilder.writeTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpBuilder.followRedirects(true);//允许重定向
        okHttpBuilder.sslSocketFactory(HttpsUtils.getSslSocketFactory());

        mOkHttpClient = okHttpBuilder.build();
    }

    public static Call post(Request request, DisposeDataHandle handle) {
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new CommonJsonCallback(handle));
        return call;
    }

    public static Call get(Request request, DisposeDataHandle handle) {
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new CommonJsonCallback(handle));
        return call;
    }


}
