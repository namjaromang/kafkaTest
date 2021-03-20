package com.onemedics.pushapi.test;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import net.minidev.json.JSONValue;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class TestUtil {
    private static String GATEWAY_URL = "https://dev.onemedics.net:9443";
    private static String OAUTH_URL = "/oauth/token";

    public static String getOauthToken() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpClientContext context = HttpClientContext.create();
        HttpPost httpPost = new HttpPost(GATEWAY_URL + OAUTH_URL);
        httpPost.addHeader("Authorization", "Basic ZG9zb28td2ViOnNlY3JldA==");
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");

        List<NameValuePair> testParam = new ArrayList<>();
        testParam.add(new BasicNameValuePair("username", "admindosoo01"));
        testParam.add(new BasicNameValuePair("password", "string12"));
        testParam.add(new BasicNameValuePair("grant_type", "password"));

        httpPost.setEntity(new UrlEncodedFormEntity(testParam, "UTF-8"));

        CloseableHttpResponse httpResponse = httpClient.execute(httpPost, context);
        try {
            HttpEntity respEntity = httpResponse.getEntity();

            Object obj = JSONValue.parse(new InputStreamReader(respEntity.getContent()));
            EntityUtils.consume(httpResponse.getEntity());

            return String.valueOf(((net.minidev.json.JSONObject) obj).get("access_token"));
        } finally {
            httpResponse.close();
        }
    }
}
