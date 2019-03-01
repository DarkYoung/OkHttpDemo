package com.example.jason.okhttpdemo.okhttp.listener;

/**
 * Created by jason on 19-2-28.
 */

public class DisposeDataHandle {
    private DisposeDataListener mListener;
    private Class<?> mClass;
    private String mSource;

    public DisposeDataHandle(DisposeDataListener listener) {
        this.mListener = listener;
    }

    public DisposeDataHandle(DisposeDataListener listener, Class<?> aClass) {
        this.mListener = listener;
        this.mClass = aClass;
    }

    public DisposeDataHandle(DisposeDataListener listener, String source) {
        this.mListener = listener;
        this.mSource = source;
    }

    public DisposeDataListener getmListener() {
        return mListener;
    }

    public Class<?> getmClass() {
        return mClass;
    }

    public String getmSource() {
        return mSource;
    }
}
