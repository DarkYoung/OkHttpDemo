package com.jason.okhttpdemo.okhttp.request;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by jason on 19-2-28.
 */

public class RequestParams {
    private ConcurrentHashMap<String, String> urlParams = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, Object> fileParams = new ConcurrentHashMap<>();

    public RequestParams() {
        this((HashMap<String, String>) null);
    }

    public RequestParams(final String key, final String value) {
        this(new HashMap<String, String>() {
            {
                put(key, value);
            }
        });
    }

    public RequestParams(HashMap<String, String> source) {
        if (null != source) {
            for (Map.Entry<String, String> entry : source.entrySet()) {
                put(entry.getKey(), entry.getValue());
            }
        }
    }

    public void put(String key, String value) {
        if (null != key && null != value)
            urlParams.put(key, value);
    }

    public void put(String key, Object value) {
        if (null != key)
            fileParams.put(key, value);
    }

    public ConcurrentHashMap<String, String> getUrlParams() {
        return urlParams;
    }

    public ConcurrentHashMap<String, Object> getFileParams() {
        return fileParams;
    }
}
