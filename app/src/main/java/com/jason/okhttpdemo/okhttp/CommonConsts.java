package com.jason.okhttpdemo.okhttp;

/**
 * Created by jason on 19-3-1.
 */

public class CommonConsts {
    /**
     * 逻辑层异常
     */
    public static final String RESULT_CODE = "ecode";
    public static final int RESULT_CODE_VALUE = 0;
    public static final String ERROR_MSG = "emsg";
    public static final String EMPTY_MSG = "";

    /**
     * 非逻辑错误
     */
    public static final int NETWORK_ERROR = -1;
    public static final int JSON_ERROR = -2;
    public static final int OTHER_ERROR = -3;


    public static final int TIME_OUT = 30;

}
