package com.android.liqingchang.sf;

import android.util.Log;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * 使用xml实现的顺丰基础功能类
 * Created by terry on 15-7-22.
 */
public class DefaultSFMethod implements ISFMethod {

    public static final int TYPE_MAILNO = 1;
    public static final int TYPE_ORDERNO = 2;

    private OkHttpClient client;

    public DefaultSFMethod() {
        client = new OkHttpClient();
        final TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                    }

                    @Override
                    public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                    }

                    @Override
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }
                }
        };
        try {
            final SSLContext sslContext;
            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            client.setSslSocketFactory(sslSocketFactory);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        client.setHostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
    }

    @Override
    public String order(Order order) {
        return post(order.toXml());
    }

    @Override
    public String query(int type, String mailno) {
        return post(getQueryXml(type, mailno));
    }

    @Override
    public String query(String mailno) {
        return post(getQueryXml(TYPE_MAILNO, mailno));
    }

    private String getQueryXml(int type, String mailno) {
        StringBuilder sb = new StringBuilder();
        sb.append("<Request service='RouteService' lang='zh-CN'>");
        sb.append("<Head>").append(SFConstants.APICODE).append("</Head>");
        sb.append("<Body>");
        sb.append("<RouteRequest tracking_type='").append(type).append("' tracking_number='").append(mailno).append("'/>");
        sb.append("</Body>");
        sb.append("</Request>");
        return sb.toString();
    }

    private String post(String xml) {
        try {
            RequestBody requestBody = new FormEncodingBuilder()
                    .add("xml", xml)
                    .add("verifyCode", SFUtil.getVerifyCode(xml))
                    .build();
            Request request = new Request.Builder()
                    .url(SFConstants.POSTPATH)
                    .post(requestBody)
                    .build();
            Response response = client.newCall(request).execute();
            Log.i("terry", "response:" + response.body().string());
            return response.body().toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
