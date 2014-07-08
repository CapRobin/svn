package com.estar.data;

import java.util.HashMap;
import java.util.Map;

import android.util.Log;

import com.estar.comm.MsgInfos;
import com.estar.comm.MsgInfosSet;
import com.estar.net.httpRequestEx;

public class DataSource {
    private httpRequestEx mHttp;
    private final String mUrlBase = "http://mtw.net188.net/";//mtw.net188.net	192.168.0.58
    private final String mUrlBaseData = "http://mtw.net188.net:7070/";//mtw.net188.net

    private static DataSource instance = new DataSource();

    private DataSource() {
        mHttp = new httpRequestEx();
    }
    
    public static DataSource getInstance() {
        return instance;
    }
    //取得手机公告
    public HashMap<String, String> getAdv(String city){
    	String url = mUrlBase + "estar/mclient/getadv.asp";
        Map<String, String> params = new HashMap<String, String>();
        params.put("action", "GetAdv");
        params.put("city", city);
        String res = mHttp.getHttpData(url, params);
        //返回结果集
        Map<String, String> params1 = new HashMap<String, String>();
        String[] strings = res.split("@@");
        if(strings.length>0){
        	for(int i=1 ;i<strings.length;i++){
        		String[] items=strings[i].split("\\|\\|");
        		//Log.e("!!!!!!!!!!!!",String.valueOf(items.length));
        		if(items.length>1){
        			params1.put(items[0], items[1]);
        		}
        	}
        }
        
        return (HashMap<String, String>) params1;
    }
    
    //检测网络
    public String checkConnection() {
        String url = mUrlBase + "estar/mclient/checkhost.htm";
        Map<String, String> params = new HashMap<String, String>();
        //params.put("action", "Client_TestInfo");
        String res = mHttp.getHttpData(url, params);
        return res;
    }
    //检测版本号及下载地址
    public String checkCurVer(){
    	String url = mUrlBase + "estar/mclient/UpdateVersion.htm";
        Map<String, String> params = new HashMap<String, String>();
        //params.put("action", "Client_TestInfo");
        String res = mHttp.getHttpData(url, params);
        return res;
    }
    //登录
    public String checkUser(String username,
    		String password,
    		String diskinfo,
    		String macaddr,
    		String post){
    	String url = mUrlBase + "estar/mclient/client_login.asp";
        Map<String, String> params = new HashMap<String, String>();
        params.put("action", "Login_Client");
        params.put("loginSign", "0");
        params.put("username", username);
        params.put("password", password);
        params.put("diskinfo", diskinfo);
        params.put("macaddr", macaddr);
        params.put("post", post);
        String res = mHttp.getHttpData(url, params);
        return res;
    }
    //注册
    public String regUser(String username,
    		String password,
    		String name1,
    		String sex,
    		String company,
    		String province,
    		String city,
    		String url,
    		String sj,
    		String tel,
    		String tel1,
    		String sj1,
    		String email,
    		String address,
    		String post,
    		String diskinfo,
    		String macaddr,
    		String town,
    		String usertype){
    	String url1 = mUrlBase + "estar/mclient/Register.asp";
        Map<String, String> params = new HashMap<String, String>();
        params.put("action", "Reg");
        params.put("username", username);//m_strSj
        params.put("password", password);//m_strPwd
        params.put("name1", name1);//m_strName1
        params.put("sex", sex);
        params.put("company", company);//m_strCompany
        params.put("province", province);//m_strProvince
        params.put("city", city);//m_strCity
        params.put("url", url);
        params.put("sj", username);
        params.put("tel", tel);//m_strTel
        params.put("tel1", tel1);
        params.put("sj1", sj1);//m_strQQ
        params.put("email", email);
        params.put("address", address);
        params.put("post", post);
        params.put("diskinfo", diskinfo);//diskinfo
        params.put("macaddr", macaddr);//macaddr
        params.put("town", town);//m_strTown
        params.put("usertype", usertype);//m_strUserType
        String res = mHttp.getHttpData(url1, params);
        return res;
    }
    //发布信息
    public String sendHCInfo(String InfoSign,
    		String msg_code,
    		String msg_type,
    		String msg_content,
    		String msg_tel,
    		String msg_flag,
    		String msg_startcity,
    		String msg_netphone,
    		String infonum){
    	if (InfoSign.equals("")){
    		InfoSign="0";
    	}
    	String url = mUrlBase + "estar/mclient/SetInfo.asp";
        Map<String, String> params = new HashMap<String, String>();
        params.put("action", "Client_AddInfo");
        params.put("InfoSign", InfoSign);
        params.put("AddSign", "NewInfo");
        params.put("Msg_Code", msg_code);
        params.put("Msg_Type", msg_type);
        params.put("Msg_Content", msg_content);
        params.put("Msg_Tel", msg_tel);
        params.put("Msg_Flag", msg_flag);
        params.put("Msg_StartCity", msg_startcity);
        params.put("Msg_NetPhone", msg_netphone);
        params.put("InfoNum", infonum);
        String res = mHttp.getHttpData(url, params);
        return res;
    }
    //取得信息
    public MsgInfosSet GetDatas(String tableType,
    		String msg_code,
    		String keywords,
    		String msg_type,
    		String page
    		){
    	String url = mUrlBase + "estar/mclient/GetmInfo.asp";
    	Map<String, String> params = new HashMap<String, String>();
    	params.put("action", "GetDatas");
    	params.put("table", tableType);
    	params.put("Code", msg_code);
    	params.put("KeyWords", keywords);
    	params.put("inType", msg_type);
    	params.put("pag", page);
    	String res = mHttp.getHttpData(url, params);
    	//Log.e("!!!!!!!!!!!!!GetDatas",res);
    	MsgInfosSet mis=new MsgInfosSet();
    	
    	int pageNum = 0;
        int totalNum = 0;
    	//res="@@0|0@@";
    	String[] strings = res.split("@@");
    	//Log.e("!!!!!!!!!!!!!res_strings_Length",String.valueOf(strings.length));
    	if(strings.length>2){
    		int i = 1;
	    	try {
				String[] nums=strings[i++].split("\\|");
				pageNum = Integer.parseInt(nums[0]);
				//Log.e("!!!!!!!!!!!!!pageNum",String.valueOf(pageNum));
				mis.setmTotalPage(pageNum);
				totalNum = Integer.parseInt(nums[1]);
				mis.setmTotalNum(totalNum);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				i = 1; // so, it has no page or total.
			}
	    	//
	    	long iMaxID=0;
	    	if (Integer.parseInt(page) <= pageNum){
	    		for (; i < strings.length; i++) {
	    			MsgInfos info=new MsgInfos(strings[i]);
	    			long id=Integer.parseInt(info.getID().toString());
	    			if(iMaxID<=id)
	    			{
	    				iMaxID=id;
	    			}
                    mis.add(info);
                }//for
	    		mis.setmMaxID(iMaxID);
	    	}
    	}else//无信息
    	{
    		if(page.equals("1")){
	    		mis.setmTotalPage(pageNum);
	    		mis.setmTotalNum(totalNum);
    		}
    	}

		return mis;
    }
    //取得刷新的信息
    public MsgInfosSet GetNewDatas(String tableType,
    		String msg_code,
    		String keywords,
    		String msg_type,
    		String MaxID){
    	String url = mUrlBase + "estar/mclient/GetmInfo.asp";
    	Map<String, String> params = new HashMap<String, String>();
    	params.put("action", "GetNewDatas");
    	params.put("table", tableType);
    	params.put("Code", msg_code);
    	params.put("KeyWords", keywords);
    	params.put("inType", msg_type);
    	params.put("pag", "1");
    	params.put("MaxID", MaxID);
    	String res = mHttp.getHttpData(url, params);
    	
    	MsgInfosSet mis=new MsgInfosSet();
    	
    	int pageNum = 0;
        int totalNum = 0;
    	//res="@@0|0@@";
    	String[] strings = res.split("@@");
    	//Log.e("00000000000GetNewDatas_MaxID",MaxID);
    	//Log.e("!!!!!!!!!!!!!getnewinfo_strings_Length",String.valueOf(strings.length));
    	if(strings.length>2){
    		int i = 1;
	    	try {
				String[] nums=strings[i++].split("\\|");
				pageNum = Integer.parseInt(nums[0]);
				mis.setmTotalPage(pageNum);
				totalNum = Integer.parseInt(nums[1]);
				mis.setmTotalNum(totalNum);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				i = 1; // so, it has no page or total.
			}
	    	long iMaxID=Integer.parseInt(MaxID);
	    	for (; i < strings.length; i++) {
	    		MsgInfos info=new MsgInfos(strings[i]);
    			long id=Integer.parseInt(info.getID().toString());
    			if(iMaxID<=id)
    			{
    				iMaxID=id;
    			}
                mis.add(info);
	    	}//for
	    	mis.setmMaxID(iMaxID);
    	}else
    	{
    		mis.setmMaxID(Integer.parseInt(MaxID));
    	}

		return mis;
    }
}
