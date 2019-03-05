package com.jason.okhttpdemo.okhttp.request;

import java.io.File;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by jason on 19-2-28.
 */

public class CommonRequest {
    /**
     * GET请求
     *
     * @param url
     * @param params
     * @return
     */
    public static Request craeteGetRequest(String url, RequestParams params) {
        StringBuilder urlBuilder = new StringBuilder(url).append("?");
        if (null != params) {
            for (Map.Entry<String, String> entry : params.getUrlParams().entrySet()) {
                urlBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
        }
        return new Request.Builder().url(urlBuilder.substring(0, urlBuilder.length() - 1)).build();
    }

    /**
     * POST请求
     *
     * @param url
     * @param params
     * @return
     */
    public static Request createPostRequest(String url, RequestParams params) {
        FormBody.Builder mFormBodyBuild = new FormBody.Builder();
        if (null != params) {
            for (Map.Entry<String, String> entry : params.getUrlParams().entrySet()) {
                mFormBodyBuild.add(entry.getKey(), entry.getValue());
            }
        }
        FormBody mFormBody = mFormBodyBuild.build();
        return new Request.Builder().url(url).post(mFormBody).build();
    }

    private static final MediaType FILE_TYPE = MediaType.get("application/octet-stream");

    /**
     * 文件上传请求
     *
     * @param url
     * @param params
     * @return
     */
    public static Request createMultiPostRequest(String url, RequestParams params) {
        MultipartBody.Builder multipartBody = new MultipartBody.Builder();
        multipartBody.setType(MultipartBody.FORM);
        if (null != params) {
            for (Map.Entry<String, Object> entry : params.getFileParams().entrySet()) {
                if (entry.getValue() instanceof File) {
                    multipartBody.addPart(Headers.of("Content-Disposition", "form-data; name=\"" +
                            entry.getKey() + "\""), RequestBody.create(FILE_TYPE, (File) entry.getValue()));
                } else if (entry.getValue() instanceof String) {
                    multipartBody.addPart(Headers.of("Content-Disposition", "form-data; name=\"" +
                            entry.getKey() + "\""), RequestBody.create(null, (String) entry.getValue()));
                }
            }
        }
        return new Request.Builder().url(url).post(multipartBody.build()).build();
    }
}
