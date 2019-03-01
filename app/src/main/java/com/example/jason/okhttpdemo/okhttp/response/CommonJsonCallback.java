package com.example.jason.okhttpdemo.okhttp.response;

import android.os.Handler;
import android.os.Looper;

import com.example.jason.okhttpdemo.okhttp.exception.OkHttpException;
import com.example.jason.okhttpdemo.okhttp.listener.DisposeDataHandle;
import com.example.jason.okhttpdemo.okhttp.listener.DisposeDataListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static com.example.jason.okhttpdemo.okhttp.CommonConsts.*;

/**
 * Created by jason on 19-3-1.
 */

public class CommonJsonCallback implements Callback {

    private DisposeDataListener mListener;
    private Class<?> mClass;
    private Handler mHandler;

    public CommonJsonCallback(DisposeDataHandle handle) {
        mListener = handle.getmListener();
        mClass = handle.getmClass();
        mHandler = new Handler(Looper.getMainLooper());//将数据返回主线程中
    }

    @Override
    public void onFailure(Call call, final IOException e) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mListener.onFailure(e);
            }
        });
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        //还在子线程中
        final String result = response.body().string();
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                handleResponse(result);
            }
        });
    }

    private void handleResponse(String result) {
        if (null == result || "".equals(result)) {
            mListener.onFailure(new OkHttpException(NETWORK_ERROR, EMPTY_MSG));
            return;
        }
        try {
            JSONObject resultObj = new JSONObject(result);
            if (resultObj.has(RESULT_CODE)) {
                if (resultObj.optInt(RESULT_CODE) == RESULT_CODE_VALUE) {
                    if (null == mClass) {
                        mListener.onSuccess(resultObj);
                    } else {
                        Object obj = ResponseEntityToModule.parseJsonObjectToModule(resultObj, mClass);
                        if (null == obj) {
                            mListener.onFailure(new OkHttpException(JSON_ERROR, EMPTY_MSG));
                        } else {
                            //返回实体对象
                            mListener.onSuccess(obj);
                        }
                    }
                } else {
                    mListener.onFailure(new OkHttpException(JSON_ERROR, EMPTY_MSG));
                }
            }
        } catch (JSONException e) {
            mListener.onFailure(new OkHttpException(OTHER_ERROR, e.getMessage()));
        }
    }
}
