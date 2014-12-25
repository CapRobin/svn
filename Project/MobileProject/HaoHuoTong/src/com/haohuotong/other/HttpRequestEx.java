package com.haohuotong.other;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;

import android.util.Log;

public class HttpRequestEx {
    private final String TAG = "HttpRequestEx";
    
    public HttpResultEx postHttpData(String url, Map params) {
        HttpPost post = new HttpPost(url);

        setupHttpHeader(post);

        List<NameValuePair> formparams = new ArrayList<NameValuePair>();

        String key, val;
        Iterator it = params.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry) it.next();
            key = pairs.getKey().toString();
            val = pairs.getValue().toString();

            formparams.add(new BasicNameValuePair(key, val));
        }

        UrlEncodedFormEntity entity = null;
        try {
            entity = new UrlEncodedFormEntity(formparams, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        post.setEntity(entity);
        return executeHttpRequest(post);

    }

    public HttpResultEx getHttpData(String url, Map params) {

        StringBuilder sb = new StringBuilder(url);
        if (url.indexOf("?") < 0) {
            sb.append("?");
        }

        String key, val;
        Iterator it = params.entrySet().iterator();
        try {
            while (it.hasNext()) {
                Map.Entry pairs = (Map.Entry) it.next();
                key = pairs.getKey().toString();
                val = pairs.getValue().toString();
                val = URLEncoder.encode(val, "gb2312");
                sb.append("&").append(key).append("=").append(val);
            }
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }

        url = sb.toString().replace("?&", "?");
        HttpGet get = new HttpGet(url);
        setupHttpHeader(get);

        return executeHttpRequest(get);
    }

    private HttpResultEx executeHttpRequest(HttpRequestBase httpReq) {
        HttpResultEx result = new HttpResultEx();
        InputStream is = null;
        
        Log.i(TAG, httpReq.getURI().toString());

        try {
            HttpClient client = HttpClientEx.getInstance().getClient();
            HttpContext context = HttpClientEx.getInstance().getContext();
            HttpResponse resp = client.execute(httpReq, context);
            HttpEntity entity = resp.getEntity();
            if (null != entity) {
                result.setStatusCode(resp.getStatusLine().getStatusCode());
                switch (result.getStatusCode()) {
                    case HttpStatus.SC_OK: {
                        is = entity.getContent();
                        Header contentEncoding = resp
                                .getFirstHeader("Content-Encoding");
                        if (contentEncoding != null
                                && contentEncoding.getValue().equalsIgnoreCase(
                                        "gzip")) {
                            is = new GZIPInputStream(is);
                        }

                        BufferedReader rd = new BufferedReader(
                                new InputStreamReader(is,
                                        Charset.forName("gb2312")), 8 * 1024);
                        StringBuilder sb = new StringBuilder();

                        char[] chars = new char[8 * 1024];
                        int len;
                        while ((len = rd.read(chars)) >= 0) {
                            sb.append(chars, 0, len);
                        }

                        result.setData(sb.toString());
                        is.close();
                        break;
                    }
                    default:
                        break;
                }

                entity.consumeContent(); // make sure the entity has been fully
                                         // processed.
            }

        } catch (HttpHostConnectException e) {
            httpReq.abort();
            e.printStackTrace();
        } catch (ConnectTimeoutException e) {
            httpReq.abort();
            e.printStackTrace();
        } catch (Exception e) {
            httpReq.abort();
            e.printStackTrace();
        }
        
        Log.i(TAG, "HTTP STATUS: " + result.getStatusCode() + ", DATA: " + result.getData());

        return result;

    }

    private void setupHttpHeader(HttpRequestBase httpReq) {
        httpReq.addHeader("Accept-Encoding", "gzip");
    }

}
