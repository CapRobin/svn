package com.cai.reader.parser;

import java.io.IOException;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

public class HttpService {
	public static final int METHOD_GET = 1;
	public static final int METHOD_POST = 2;
	/**
	 * ��ȡָ��url��HttpEntity
	 * @param uri
	 * @param params
	 * @param method
	 * @return
	 * @throws IOException
	 */
	public static HttpEntity getEntity(String uri,ArrayList<BasicNameValuePair> params,int method) throws IOException,ConnectTimeoutException{
		HttpEntity entity = null;
		//�����ͻ��˶���
		DefaultHttpClient client = new DefaultHttpClient();
		client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 3000);
		//�����������
		HttpUriRequest request = null;
		switch (method) {
		case METHOD_GET://get����
			StringBuilder sb = new StringBuilder(uri);
			if(params!=null && !params.isEmpty()){
				sb.append('?');
				for(BasicNameValuePair pair : params){
					sb.append(pair.getName())
					.append('=')
					.append(pair.getValue())
					.append('&');
				}
				sb.deleteCharAt(sb.length()-1);
			}
			request = new HttpGet(sb.toString());
			break;
		case METHOD_POST://post����
			request = new HttpPost(uri);
			if(params!=null && !params.isEmpty()){
				UrlEncodedFormEntity reqEntity = new UrlEncodedFormEntity(params);
				((HttpPost)request).setEntity(reqEntity);
			}
			break;
		}
		//ִ����������Ӧ
		HttpResponse response = client.execute(request);
		//�����Ӧ����200 ������Ӧʵ�����
		if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
			entity = response.getEntity();
		}
		return entity;
	}
	
	public static long getLength(HttpEntity entity){
		if(entity!=null)
			return entity.getContentLength();
		return 0 ;
	}
	
	public static InputStream getStream(HttpEntity entity) throws  IOException{
		if(entity!=null)
			return entity.getContent();
		return null;
	}
	
	public static byte[] getBytes(HttpEntity entity) throws IOException{
		if(entity!=null)
			return EntityUtils.toByteArray(entity);
		return null;
	}
	
	public static String toString(HttpEntity entity) throws  IOException{
		if(entity!=null)
			return EntityUtils.toString(entity);
		return null;
	}
}
