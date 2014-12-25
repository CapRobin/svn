package com.haohuotong.other;

import java.util.HashMap;
import java.util.Map;


public class DataSource {

    private final String TAG = "DataSource";
    private HttpRequestEx mHttp;
    // private final String mUrlBase = "http://10.0.1.3:3000/";
    private final String mUrlBase = "http://nmgclient2.net188.net/";
    private final int mPageSize = 8;

    private static DataSource instance = new DataSource();

    private DataSource() {
        mHttp = new HttpRequestEx();
    }

    public static DataSource getInstance() {
        return instance;
    }

    public int getPageSize() {
        return mPageSize;
    }

    public String checkConnectionSync() {
        String url = mUrlBase + "estar/mclient/info_upload.asp";
        Map<String, String> params = new HashMap<String, String>();
        params.put("action", "Client_TestInfo");
        HttpResultEx res = mHttp.getHttpData(url, params);
        return res.getData();
    }

    public void checkConnectionAsync(final Callback cb) {

        new Thread(new Runnable() {

            public void run() {
                String url = mUrlBase + "estar/mclient/info_upload.asp";
                Map<String, String> params = new HashMap<String, String>();
                params.put("action", "Client_TestInfo");
                HttpResultEx res = mHttp.getHttpData(url, params);

                cb.onCheckConnection(res.getData(), res.getStatusCode());
            }

        }).start();
    }

    public EntitySet getInitialInfoListSync(String city, InfoEntity.Type type) {
        String url = mUrlBase + "estar/mclient/GetInfo.asp";
        Map<Object, Object> params = new HashMap<Object, Object>();
        params.put("action", "GetTopDatas");
        params.put("Code", city);
        params.put("inType", InfoEntity.Type.toInteger(type));
        HttpResultEx res = mHttp.getHttpData(url, params);
        EntitySet es = new EntitySet();

        if (res.getStatusCode() == 200) {
            int i = 1;

            String[] strings = res.getData().split("@@");
            try {
                int page = Integer.parseInt(strings[i++]);
                es.setTotalPage(page);
                int total = Integer.parseInt(strings[i++]);
                es.setTotalNum(total);
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
                i = 1; // so, it has no page or total.
            }

            for (; i < strings.length; i++) {
                es.add(new InfoEntity(strings[i]));
            }

        }

        return es;
    }

    public EntitySet getPagedInfoListSync(String city, InfoEntity.Type type,
            int startNum, int totalNum) {
        String url = mUrlBase + "estar/mclient/GetInfo.asp";
        Map<Object, Object> params = new HashMap<Object, Object>();
        params.put("action", "GetCurDatas");
        params.put("Code", city);
        params.put("inType", InfoEntity.Type.toInteger(type));
        params.put("StartNum", startNum);
        params.put("TotalNum", totalNum);
        HttpResultEx res = mHttp.getHttpData(url, params);
        EntitySet es = new EntitySet();

        if (res.getStatusCode() == 200) {
            String[] strings = res.getData().split("@@");
            for (int i = 1; i < strings.length; i++) {
                es.add(new InfoEntity(strings[i]));
            }
        }

        return es;
    }

    public EntitySet getPagedInfoListSync2(String city, String keywords,
            InfoEntity.Type type, int page) {
        String url = mUrlBase + "estar/mclient/GetMInfo.asp";
        Map<Object, Object> params = new HashMap<Object, Object>();
        params.put("action", "GetDatas");
        params.put("Code", city);
        params.put("Keywords", keywords);
        params.put("inType", InfoEntity.Type.toInteger(type));
        params.put("pag", page);
        HttpResultEx res = mHttp.getHttpData(url, params);
//        http://nmgclient2.net188.net/estar/mclient/GetMInfo.asp  {action=GetDatas, inType=8, pag=1, Code=银川, Keywords=}
        int pageNum = 0;
        int totalNum = 0;
        EntitySet es = new EntitySet();
        if (res.getStatusCode() == 200) {
            int i = 1;
            String[] strings = res.getData().split("@@");
            try {
                String[] nums = strings[i++].split("\\|");
                pageNum = Integer.parseInt(nums[0]);
                es.setTotalPage(pageNum);
                totalNum = Integer.parseInt(nums[1]);
                es.setTotalNum(totalNum);
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
                i = 1; // so, it has no page or total.
            }

            if (page <= pageNum) {
                for (; i < strings.length; i++) {
                    es.add(new InfoEntity(strings[i]));
                }
            }
        }

        return es;
    }

    public void getLoginInfoAsync(final Callback cb, final String username,
            final String password, final String diskinfo, final String macaddr) {
        new Thread(new Runnable() {

            public void run() {
                String url = mUrlBase + "estar/mclient/client_login.asp";
                Map<Object, Object> params = new HashMap<Object, Object>();
                params.put("action", "Login_Client");
                params.put("loginSign", 0);
                params.put("username", username);
                params.put("password", password);
                params.put("diskinfo", diskinfo);
                params.put("macaddr", macaddr);
                HttpResultEx res = mHttp.getHttpData(url, params);
                UserEntity user = null;

                if (res.getStatusCode() == 200) {
                    user = new UserEntity(res.getData());
                }

                cb.onLogin(user);
            }

        }).start();

    }

    public void registerUserAsync(final Callback cb, final String usertype,
            final String username, final String password, final String name1,
            final String postcard, final String sex, final String province,
            final String city, final String tel, final String sj,
            final String cartype, final String carnums, final String roads, final String diskinfo,
            final String macaddr) {
        new Thread(new Runnable() {

            public void run() {
                String url = mUrlBase + "estar/mclient/Register.asp";
                Map<Object, Object> params = new HashMap<Object, Object>();
                params.put("action", "Reg");
                params.put("usertype", usertype);
                params.put("username", username);
                params.put("password", password);
                params.put("name1", name1);
                params.put("postcard", postcard);
                params.put("sex", sex);
                params.put("province", province);
                params.put("city", city);
                params.put("tel", tel);
                params.put("sj", sj);
                params.put("cartype", cartype);
                params.put("carnums", carnums);
                params.put("roads", roads);
                params.put("diskinfo", diskinfo);
                params.put("macaddr", macaddr);
                HttpResultEx res = mHttp.getHttpData(url, params);

                cb.onRegisterUser(res.getData());
            }

        }).start();
    }

    public void publishInfoAsync(final Callback cb, final InfoEntity info,
            final UserEntity user) {

        new Thread(new Runnable() {

            public void run() {
                String url = mUrlBase + "estar/mclient/SetInfo.asp";
                Map<Object, Object> params = new HashMap<Object, Object>();
                params.put("action", "Client_AddInfo");
                params.put("AddSign", "NewInfo");
                params.put("InfoSign", 0);

                params.put("Msg_Type", info.getMsg_Type());
                params.put("Msg_Content", info.getMsg_Content());
                params.put("Msg_StartCity", info.getMsg_StartCity());

                params.put("Msg_Code", user.getMsg_City());
                params.put("Msg_Tel", user.getMsg_Tel());
                params.put("Msg_NetPhone", user.getMsg_Net_Phone());
                params.put("Msg_Flag", user.getMsg_ID());
                params.put("InfoNum", user.getMsg_Info_Num());

                HttpResultEx res = mHttp.getHttpData(url, params);

                String result = "";
                if (res.getStatusCode() == 200) {
                    result = res.getData();
                    if (0 == result.compareToIgnoreCase("AddOK")) {
                        result = "信息发布成功";
                    } else if (0 == result.compareToIgnoreCase("Error")) {
                        result = "信息发布失败";
                    } else if (0 == result.compareToIgnoreCase("AddError")) {
                        result = "信息发布失败";
                    }
                }
                cb.onPublish(result);
            }

        }).start();
    }
    
    public void checkUpdateAsync(final Callback cb) {
        new Thread(new Runnable() {

            public void run() {
                String url = mUrlBase + "estar/mclient/UpdateVersion.asp";
                Map<Object, Object> params = new HashMap<Object, Object>();
                params.put("action", "Auto2");

                HttpResultEx res = mHttp.getHttpData(url, params);

                String result = "";
                if (res.getStatusCode() == 200) {
                    result = res.getData();
                }
                cb.onCheckUpdate(result);
            }

        }).start();
    }

    public static interface Callback {
        public void onLogin(Entity entity);

        public void onPublish(String result);

        public void onCheckConnection(String result, int statusCode);

        public void onRegisterUser(String result);
        
        public void onCheckUpdate(String result);
    }

}
