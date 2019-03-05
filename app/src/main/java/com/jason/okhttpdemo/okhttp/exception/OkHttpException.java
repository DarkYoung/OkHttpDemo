package com.jason.okhttpdemo.okhttp.exception;

/**
 * Created by jason on 19-2-28.
 */

public class OkHttpException extends Exception {

    /**
     * 错误码
     */
    private int eCode;
    /**
     * 错误信息
     */
    private Object eMsg;

    public OkHttpException(int eCode, Object eMsg) {
        this.eCode = eCode;
        this.eMsg = eMsg;
    }

    public int geteCode() {
        return eCode;
    }

    public Object geteMsg() {
        return eMsg;
    }
}
