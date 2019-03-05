package com.jason.okhttpdemo.okhttp.listener;

/**
 * Created by jason on 19-2-28.
 */

public interface DisposeDataListener {
    /**
     * 处理请求成功回调事件
     * @param responseObj
     */
    void onSuccess(Object responseObj);

    /**
     * 处理请求失败回调事件
     *
     * @param reasonObj
     */
    void onFailure(Object reasonObj);
}
