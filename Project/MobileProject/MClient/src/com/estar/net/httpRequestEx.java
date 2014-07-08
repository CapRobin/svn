package com.estar.net;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.util.Log;
/*
 * ���÷���
 * 				private httpRequestEx mHttp;//����
 * 				mHttp = new httpRequestEx();//����ʵ��
 * 				String url = "http://www.xxxx.com/app.asp";
		        Map<String, String> params = new HashMap<String, String>();
		        params.put("str1", "I am Post String����POST����");
		        params.put("str2", "����POST����2���Ĳ���");
		        String strResult=null;
				try {
					strResult = mHttp.postHttpData(url, params);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
 */
public class httpRequestEx {

	//HttpGet��ͳһ�����ʽ
	public String getHttpData(String url, Map<String, String> params)
	{
		String strResult=null;
		//============���½���ȡ��URL=============================
		StringBuilder sb = new StringBuilder(url);
        if (url.indexOf("?") < 0) {
            sb.append("?");
        }
        
        String key, val;
        Iterator<?> it = params.entrySet().iterator();
        try {
            while (it.hasNext()) {
                Map.Entry pairs = (Map.Entry) it.next();
                key = pairs.getKey().toString();
                val = pairs.getValue().toString();
                val = URLEncoder.encode(val, "GB2312");//UTF-8
                sb.append("&").append(key).append("=").append(val);
            }
        } catch (UnsupportedEncodingException ex) {
        	strResult="Error:"+ex.getMessage().toString();
            ex.printStackTrace();
        }
        url = sb.toString().replace("?&", "?");
        //==========================================================
        
        /*����HTTP Get����*/
        HttpGet httpRequest = null;
		try {
			httpRequest = new HttpGet(url);
			//httpRequest.addHeader("User-Agent", "AJentNewMobileClient");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			strResult="Error:"+e1.getMessage().toString();
			e1.printStackTrace();
		} 
		
		try 
        { 
          /*����HTTP request*/
          HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequest); 
          /*��״̬��Ϊ200 ok*/
          if(httpResponse.getStatusLine().getStatusCode() == 200)  
          { 
            /*ȡ����Ӧ�ַ���*/
            strResult = EntityUtils.toString(httpResponse.getEntity(),"gb2312");//utf8
            /*ɾ�������ַ�*/
            strResult = eregi_replace("(\r\n|\r|\n|\n\r)","",strResult);
          } 
          else 
          { 
        	  strResult=httpResponse.getStatusLine().toString(); 
          } 
        } 
        catch (ClientProtocolException e) 
        {  
        	strResult="Error:"+e.getMessage().toString();
        	e.printStackTrace(); 
        } 
        catch (IOException e) 
        {  
        	strResult="Error:"+e.getMessage().toString();
        	e.printStackTrace(); 
        } 
        catch (Exception e) 
        {  
        	strResult="Error:"+e.getMessage().toString();
        	e.printStackTrace();  
        }  
        
		//modify by estar in 2013-10-22 9:39 
		String strNewString=null;
		try {
			strNewString= new String(strResult.getBytes("GBK"),"gbk");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Log.e("!!!!!!!!!!!!!!!!!!!!",strNewString);
		return strNewString;
	}
	//HttpPost��ͳһ�����ʽ
	public String postHttpData(String url, Map<String, String> params)
	{
		String strResult=null;
		/*����HTTP Post����*/
        HttpPost httpRequest = null;
		try {
			httpRequest = new HttpPost(url);
			//httpRequest.addHeader("User-Agent", "AJentNewMobileClient");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			strResult="Error:"+e1.getMessage().toString();
			e1.printStackTrace();
		} 
		//ȡ�ò���
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();

        String key, val;
        Iterator<?> it = params.entrySet().iterator();
        
		while (it.hasNext()) {
			Map.Entry pairs = (Map.Entry) it.next();
			key = pairs.getKey().toString();
			val = pairs.getValue().toString();
			formparams.add(new BasicNameValuePair(key, val));
		}
		
        //
        UrlEncodedFormEntity entity = null;
        try {
            entity = new UrlEncodedFormEntity(formparams, HTTP.UTF_8);
        } catch (UnsupportedEncodingException e) {
        	strResult="Error:"+e.getMessage().toString();
            e.printStackTrace();
        }
        
        try {
			  /*����HTTP request*/
			  httpRequest.setEntity(entity);
	          /*ȡ��HTTP response*/
	          HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequest); 
	          /*��״̬��Ϊ200 ok*/
	          if(httpResponse.getStatusLine().getStatusCode() == 200)  
	          { 
	            /*ȡ����Ӧ�ַ���*/
	            strResult = EntityUtils.toString(httpResponse.getEntity(),"utf8"); 
	            /*ɾ�������ַ�*/
	            strResult = eregi_replace("(\r\n|\r|\n|\n\r)","",strResult);
	          } 
	          else 
	          { 
	        	  strResult=httpResponse.getStatusLine().toString(); 
	          }
		}catch (ClientProtocolException e) 
        {  
			strResult="Error:"+e.getMessage().toString();
            e.printStackTrace(); 
        } 
        catch (IOException e) 
        {  
        	strResult="Error:"+e.getMessage().toString();
            e.printStackTrace(); 
        } 
        catch (Exception e) 
        {  
        	strResult="Error:"+e.getMessage().toString();
            e.printStackTrace();  
        }
		
		return strResult;
	}
	//
	/* �Զ����ַ���ȡ������ */
    public String eregi_replace(String strFrom, String strTo, String strTarget)
    {
      String strPattern = "(?i)"+strFrom;
      Pattern p = Pattern.compile(strPattern);
      Matcher m = p.matcher(strTarget);
      if(m.find())
      {
        return strTarget.replaceAll(strFrom, strTo);
      }
      else
      {
        return strTarget;
      }
    }
}
