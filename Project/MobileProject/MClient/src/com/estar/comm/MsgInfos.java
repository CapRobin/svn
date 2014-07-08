package com.estar.comm;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.os.Parcel;
import android.os.Parcelable;

public class MsgInfos implements Parcelable {

	private String ID;				//信息ID
	private String Msg_Code;		//信息归属地
	private String Msg_Type;		//信息类型（0：货源；1：车源）
	private String Msg_Content;		//信息内容
	private String Msg_Tel;			//联系方式
	private String Msg_NetPhone;	//网络电话或QQ
	private String Msg_Flag;		//会员ID
	private String Msg_Success;		//是否成交
	private String Msg_AddTime;		//日期时间
	
	public String getID() {
		return ID;
	}
	
	public String getMsg_Code() {
		return Msg_Code;
	}
	
	public String getMsg_Type() {
		return Msg_Type;
	}
	
	public String getMsg_Content() {
		return Msg_Content;
	}
	
	public String getMsg_Tel() {
		return Msg_Tel;
	}
	
	public String getMsg_NetPhone() {
		return Msg_NetPhone;
	}
	
	public String getMsg_Flag() {
		return Msg_Flag;
	}
	
	public String getMsg_Success() {
		return Msg_Success;
	}
	
	public String getMsg_AddTime() {
		return Msg_AddTime;
	}

	public void setID(String iD) {
		ID = iD;
	}
	
	public void setMsg_Code(String msg_Code) {
		Msg_Code = msg_Code;
	}
	
	public void setMsg_Type(String msg_Type) {
		Msg_Type = msg_Type;
	}
	
	public void setMsg_Content(String msg_Content) {
		Msg_Content = msg_Content;
	}
	
	public void setMsg_Tel(String msg_Tel) {
		Msg_Tel = msg_Tel;
	}
	
	public void setMsg_NetPhone(String msg_NetPhone) {
		Msg_NetPhone = msg_NetPhone;
	}
	
	public void setMsg_Flag(String msg_Flag) {
		Msg_Flag = msg_Flag;
	}
	
	public void setMsg_Success(String msg_Success) {
		Msg_Success = msg_Success;
	}
	
	public void setMsg_AddTime(String msg_AddTime) {
		Msg_AddTime = msg_AddTime;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(this.ID);
        dest.writeString(this.Msg_Code);
        dest.writeString(this.Msg_Type);
        dest.writeString(this.Msg_Content);
        dest.writeString(this.Msg_Tel);
        dest.writeString(this.Msg_NetPhone);
        dest.writeString(this.Msg_Flag);
        dest.writeString(this.Msg_Success);
        dest.writeString(this.Msg_AddTime);
	}
	
	public MsgInfos() {
        super();
        this.ID = "";
        this.Msg_Code = "";
        this.Msg_Type = "";
        this.Msg_Content = "";
        this.Msg_Tel = "";
        this.Msg_NetPhone = "";
        this.Msg_Flag = "";
        this.Msg_Success = "";
        this.Msg_AddTime = "";
    }
	
	public MsgInfos(String string) {
        super();
        String[] strings = string.split("\\|\\|");
        int i = 0;
        this.ID = strings[i++];
        this.Msg_Code = strings[i++];
        this.Msg_Type = strings[i++];
        this.Msg_Content = strings[i++];
        this.Msg_Tel = strings[i++];
        this.Msg_NetPhone = strings[i++];
        this.Msg_Flag = strings[i++];
        this.Msg_Success = strings[i++];
        this.Msg_AddTime = strings[i++];
    }
	
	private MsgInfos(Parcel in) {
        this.ID = in.readString();
        this.Msg_Code = in.readString();
        this.Msg_Type = in.readString();
        this.Msg_Content = in.readString();
        this.Msg_Tel = in.readString();
        this.Msg_NetPhone = in.readString();
        this.Msg_Flag = in.readString();
        this.Msg_Success = in.readString();
        this.Msg_AddTime = in.readString();
    }
	
	// this is used to regenerate your object. All Parcelables must have a
    // CREATOR that implements these two methods
	public static final Parcelable.Creator<MsgInfos> CREATOR = new Parcelable.Creator<MsgInfos>() {
        public MsgInfos createFromParcel(Parcel in) {
            return new MsgInfos(in);
        }

        public MsgInfos[] newArray(int size) {
            return new MsgInfos[size];
        }
    };
    
    
    //取得当前系统日期格式为:2005-8-23
	   public String GetCurDate()
	   {
		   java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd"); 
	       java.util.Date currentTime_1 = new java.util.Date(); 
	       String now=formatter.format(currentTime_1);
	       return now;
	   }
	   // 取得日期格式为:2005-8-23
	   public String GetCurDate(Date myDate)
	   {
		   java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd"); 
	       java.util.Date currentTime_1 = myDate; 
	       String now=formatter.format(currentTime_1);
	       return now;
	   }
	   // 取得当前系统时间格式为:2005-8-23 13:09:00
	   public String GetCurTime()
	   {
		   java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	       java.util.Date currentTime_1 = new java.util.Date(); 
	       String now=formatter.format(currentTime_1);
	       return now;
	   }
	   // 取得时间格式为:2005-8-23 13:09:00
	   public String GetCurTime(Date myDate)
	   {
		   java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	       java.util.Date currentTime_1 = myDate; 
	       String now=formatter.format(currentTime_1);
	       return now;
	   }
	   //
	    public String GetTimeHM()
	    {
	    	//java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			   java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("HH:mm");
		       java.util.Date currentTime_1 = new java.util.Date(); 
		       String now=formatter.format(currentTime_1);
		       return now;
	    }
	    //
	    public String GetTimeHM(String strDate){
	    	java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("HH:mm");
		    java.util.Date currentTime_1 = StringToDate(strDate); 
		    String now=formatter.format(currentTime_1);
		    return now;
	    }
	    //string to date
	    public Date StringToDate(String strDate){
	    	
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = null;
			try {
				date = sdf.parse(strDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	    	return date;
	    }
	    //date to string
	    public String DateToString(Date date){
	    	String sdate = null;
			try {
				sdate=(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(date);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    	return sdate;
	    	
	    }
}
