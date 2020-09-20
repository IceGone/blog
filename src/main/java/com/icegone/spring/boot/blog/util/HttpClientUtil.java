package com.icegone.spring.boot.blog.util;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;

/**
 *  http工具类
 * @author Icegone
 * @date 2019/6/1
 */
public class HttpClientUtil {
    /** 请求方式为POST **/
    private static final String HTTP_POST_METHOD = "POST";

    /** 请求方式为GET**/
    private static final String HTTP_GET_METHOD = "GET";

    /**
     * 发送http请求，获取请求结果
     * @param url 请求的url
     * @param method 请求的方式：POST/GET
     * @return 返回请求后的结果字符串
     */
    public static String reqClient(String url, String method, String postData) {
        HttpClient httpClient = new HttpClient();
        try {
            if (HTTP_POST_METHOD.equalsIgnoreCase(method)) {
                PostMethod httpMethod = new PostMethod(url);
                if(StringUtils.isNotEmpty(postData)){
                    httpMethod.setRequestHeader("Content-type", "application/json; charset=UTF-8");
                    httpMethod.setRequestHeader("Accept", "application/json; charset=UTF-8");
                    //httpMethod.setRequestHeader("Access-Control-Allow-Origin", "*");
                    httpMethod.setRequestBody(postData);
                }
                int statusCode = httpClient.executeMethod(httpMethod);
                httpClient.getParams().setContentCharset("UTF-8");
                return httpMethod.getResponseBodyAsString();
            } else if (HTTP_GET_METHOD.equalsIgnoreCase(method)) {
                GetMethod httpMethod = new GetMethod(url);
                int statusCode = httpClient.executeMethod(httpMethod);
                httpClient.getParams().setContentCharset("UTF-8");
                return httpMethod.getResponseBodyAsString();
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
