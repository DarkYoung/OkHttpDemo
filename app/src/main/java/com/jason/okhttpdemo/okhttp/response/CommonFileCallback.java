package com.jason.okhttpdemo.okhttp.response;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by jason on 19-3-1.
 * 处理文本下载回调
 */

public class CommonFileCallback implements Callback {
    @Override
    public void onFailure(Call call, IOException e) {

    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {

    }
}
