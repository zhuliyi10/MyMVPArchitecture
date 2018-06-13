package com.zhuliyi.commonlib.http;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Describe : 统一处理 Http 请求和响应结果的处理类
 * Author : zhuly
 * Date : 2018-06-12
 */

public interface GlobalHttpHandler {
    Response onHttpResultResponse(String httpResult, Interceptor.Chain chain, Response response);
    Request onHttpRequestBefore(Interceptor.Chain chain, Request request);
}
