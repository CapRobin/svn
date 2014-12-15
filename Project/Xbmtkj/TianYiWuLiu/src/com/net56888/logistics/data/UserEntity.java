package com.net56888.logistics.data;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;

public class UserEntity extends Entity {
    
    private String Error = "";

    private String Msg_ID;
    private String Msg_Name;
    private String Msg_Sex;
    private String Msg_Company;
    private String Msg_Tel;
    private String Msg_Mobile;
    private String Msg_Expire;
    private String Msg_Publish_Enabled;
    private String Msg_HandWriting_Enabled;
    private String Msg_Agent_Company;
    private String Msg_Agent_Tel;
    private String Msg_Keyword_Filter;
    private String Msg_Net_Phone;
    private String Msg_Tel_2;
    private String Msg_City;
    private String Msg_Province;
    private String Msg_WebSite;
    private String Msg_Email;
    private String Msg_Address;
    private String Msg_PostNum;
    private String Msg_Publish_Interval;
    private String Msg_Info_Num;
    private String Msg_Info_Length;
    private String Msg_Registration_Changable;
    private String Msg_Password_Changable;
    private String Msg_Suicide_Enabled;
    private String Msg_Back_Province;
    private String Msg_Back_City;

    public UserEntity() {
        this.Msg_Name = "";
        this.Msg_Sex = "";
        this.Msg_Company = "";
        this.Msg_Tel = "";
        this.Msg_Mobile = "";
        this.Msg_Expire = "";
        this.Msg_Publish_Enabled = "";
        this.Msg_HandWriting_Enabled = "";
        this.Msg_Agent_Company = "";
        this.Msg_Agent_Tel = "";
        this.Msg_Keyword_Filter = "";
        this.Msg_Net_Phone = "";
        this.Msg_Tel_2 = "";
        this.Msg_City = "";
        this.Msg_Province = "";
        this.Msg_WebSite = "";
        this.Msg_Email = "";
        this.Msg_Address = "";
        this.Msg_PostNum = "";
        this.Msg_Publish_Interval = "";
        this.Msg_Info_Num = "";
        this.Msg_Info_Length = "";
        this.Msg_Registration_Changable = "";
        this.Msg_Password_Changable = "";
        this.Msg_Suicide_Enabled = "";
        this.Msg_Back_Province = "";
        this.Msg_Back_City = "";
    }

    public UserEntity(String string) {
        if (parseError(string)) return;
        
        String[] strings = string.split("@@@@@");
        int i = 1;

        this.Msg_ID = strings[i++];
        this.Msg_Name = strings[i++];
        this.Msg_Sex = strings[i++];
        this.Msg_Company = strings[i++];
        this.Msg_Tel = strings[i++];
        this.Msg_Mobile = strings[i++];
        this.Msg_Expire = strings[i++];
        this.Msg_Publish_Enabled = strings[i++];
        this.Msg_HandWriting_Enabled = strings[i++];
        this.Msg_Agent_Company = strings[i++];
        this.Msg_Agent_Tel = strings[i++];
        this.Msg_Keyword_Filter = strings[i++];
        this.Msg_Net_Phone = strings[i++];
        this.Msg_Tel_2 = strings[i++];
        this.Msg_City = strings[i++];
        this.Msg_Province = strings[i++];
        this.Msg_WebSite = strings[i++];
        this.Msg_Email = strings[i++];
        this.Msg_Address = strings[i++];
        this.Msg_PostNum = strings[i++];
        this.Msg_Publish_Interval = strings[i++];
        this.Msg_Info_Num = strings[i++];
        this.Msg_Info_Length = strings[i++];
        this.Msg_Registration_Changable = strings[i++];
        this.Msg_Password_Changable = strings[i++];
        this.Msg_Suicide_Enabled = strings[i++];
        //FIXME: this.Msg_Back_Province = strings[i++];
        //FIXME: this.Msg_Back_City = strings[i++];
    }

    private boolean parseError(String string) {
        if (string.indexOf("Error") == 0) {
            this.Error = "登录失败，请重新登录。";
            return true;
        } else if (string.indexOf("UserOrPassError") == 0) {
            this.Error = "用户名或密码错误";
            return true;
        } else if (string.indexOf("NotGoInSign") == 0) {
            this.Error = "还没有通过审核";
            return true;
        } else if (string.indexOf("HaveNoTime") == 0) {
            this.Error = "此号已经到期";
            return true;
        } else if (string.indexOf("NotTheMachine") == 0) {
            this.Error = "登录机器与绑定不一致";
            return true;
        }
        
        return false;
    }
    
    public String getError() {
        return this.Error;
    }

    public String getMsg_ID() {
        return Msg_ID;
    }

    public void setMsg_ID(String msg_ID) {
        Msg_ID = msg_ID;
    }

    public String getMsg_Name() {
        return Msg_Name;
    }

    public void setMsg_Name(String msg_Name) {
        Msg_Name = msg_Name;
    }

    public String getMsg_Sex() {
        return Msg_Sex;
    }

    public void setMsg_Sex(String msg_Sex) {
        Msg_Sex = msg_Sex;
    }

    public String getMsg_Company() {
        return Msg_Company;
    }

    public void setMsg_Company(String msg_Company) {
        Msg_Company = msg_Company;
    }

    public String getMsg_Tel() {
        return Msg_Tel;
    }

    public void setMsg_Tel(String msg_Tel) {
        Msg_Tel = msg_Tel;
    }

    public String getMsg_Mobile() {
        return Msg_Mobile;
    }

    public void setMsg_Mobile(String msg_Mobile) {
        Msg_Mobile = msg_Mobile;
    }

    public String getMsg_Expire() {
        return Msg_Expire;
    }

    public void setMsg_Expire(String msg_Expire) {
        Msg_Expire = msg_Expire;
    }

    public String getMsg_Publish_Enabled() {
        return Msg_Publish_Enabled;
    }

    public void setMsg_Publish_Enabled(String msg_Publish_Enabled) {
        Msg_Publish_Enabled = msg_Publish_Enabled;
    }

    public String getMsg_HandWriting_Enabled() {
        return Msg_HandWriting_Enabled;
    }

    public void setMsg_HandWriting_Enabled(String msg_HandWriting_Enabled) {
        Msg_HandWriting_Enabled = msg_HandWriting_Enabled;
    }

    public String getMsg_Agent_Company() {
        return Msg_Agent_Company;
    }

    public void setMsg_Agent_Company(String msg_Agent_Company) {
        Msg_Agent_Company = msg_Agent_Company;
    }

    public String getMsg_Agent_Tel() {
        return Msg_Agent_Tel;
    }

    public void setMsg_Agent_Tel(String msg_Agent_Tel) {
        Msg_Agent_Tel = msg_Agent_Tel;
    }

    public String getMsg_Keyword_Filter() {
        return Msg_Keyword_Filter;
    }

    public void setMsg_Keyword_Filter(String msg_Keyword_Filter) {
        Msg_Keyword_Filter = msg_Keyword_Filter;
    }

    public String getMsg_Net_Phone() {
        return Msg_Net_Phone;
    }

    public void setMsg_Net_Phone(String msg_Net_Phone) {
        Msg_Net_Phone = msg_Net_Phone;
    }

    public String getMsg_Tel_2() {
        return Msg_Tel_2;
    }

    public void setMsg_Tel_2(String msg_Tel_2) {
        Msg_Tel_2 = msg_Tel_2;
    }

    public String getMsg_City() {
        return Msg_City;
    }

    public void setMsg_City(String msg_City) {
        Msg_City = msg_City;
    }

    public String getMsg_Province() {
        return Msg_Province;
    }

    public void setMsg_Province(String msg_Province) {
        Msg_Province = msg_Province;
    }

    public String getMsg_WebSite() {
        return Msg_WebSite;
    }

    public void setMsg_WebSite(String msg_WebSite) {
        Msg_WebSite = msg_WebSite;
    }

    public String getMsg_Email() {
        return Msg_Email;
    }

    public void setMsg_Email(String msg_Email) {
        Msg_Email = msg_Email;
    }

    public String getMsg_Address() {
        return Msg_Address;
    }

    public void setMsg_Address(String msg_Address) {
        Msg_Address = msg_Address;
    }

    public String getMsg_PostNum() {
        return Msg_PostNum;
    }

    public void setMsg_PostNum(String msg_PostNum) {
        Msg_PostNum = msg_PostNum;
    }

    public String getMsg_Publish_Interval() {
        return Msg_Publish_Interval;
    }

    public void setMsg_Publish_Interval(String msg_Publish_Interval) {
        Msg_Publish_Interval = msg_Publish_Interval;
    }

    public String getMsg_Info_Num() {
        return Msg_Info_Num;
    }

    public void setMsg_Info_Num(String msg_Info_Num) {
        Msg_Info_Num = msg_Info_Num;
    }

    public String getMsg_Info_Length() {
        return Msg_Info_Length;
    }

    public void setMsg_Info_Length(String msg_Info_Length) {
        Msg_Info_Length = msg_Info_Length;
    }

    public String getMsg_Registration_Changable() {
        return Msg_Registration_Changable;
    }

    public void setMsg_Registration_Changable(String msg_Registration_Changable) {
        Msg_Registration_Changable = msg_Registration_Changable;
    }

    public String getMsg_Password_Changable() {
        return Msg_Password_Changable;
    }

    public void setMsg_Password_Changable(String msg_Password_Changable) {
        Msg_Password_Changable = msg_Password_Changable;
    }

    public String getMsg_Suicide_Enabled() {
        return Msg_Suicide_Enabled;
    }

    public void setMsg_Suicide_Enabled(String msg_Suicide_Enabled) {
        Msg_Suicide_Enabled = msg_Suicide_Enabled;
    }

    public String getMsg_Back_Province() {
        return Msg_Back_Province;
    }

    public void setMsg_Back_Province(String msg_Back_Province) {
        Msg_Back_Province = msg_Back_Province;
    }

    public String getMsg_Back_City() {
        return Msg_Back_City;
    }

    public void setMsg_Back_City(String msg_Back_City) {
        Msg_Back_City = msg_Back_City;
    }

    @Override
    protected void writeFieldValues(ContentValues values) {
        super.writeFieldValues(values);
        values.put(Fields.Msg_ID, this.Msg_ID);
        values.put(Fields.Msg_Name, this.Msg_Name);
        values.put(Fields.Msg_Sex, this.Msg_Sex);
        values.put(Fields.Msg_Company, this.Msg_Company);
        values.put(Fields.Msg_Tel, this.Msg_Tel);
        values.put(Fields.Msg_Mobile, this.Msg_Mobile);
        values.put(Fields.Msg_Expire, this.Msg_Expire);
        values.put(Fields.Msg_Publish_Enabled, this.Msg_Publish_Enabled);
        values.put(Fields.Msg_HandWriting_Enabled, this.Msg_HandWriting_Enabled);
        values.put(Fields.Msg_Agent_Company, this.Msg_Agent_Company);
        values.put(Fields.Msg_Agent_Tel, this.Msg_Agent_Tel);
        values.put(Fields.Msg_Keyword_Filter, this.Msg_Keyword_Filter);
        values.put(Fields.Msg_Net_Phone, this.Msg_Net_Phone);
        values.put(Fields.Msg_Tel_2, this.Msg_Tel_2);
        values.put(Fields.Msg_City, this.Msg_City);
        values.put(Fields.Msg_Province, this.Msg_Province);
        values.put(Fields.Msg_WebSite, this.Msg_WebSite);
        values.put(Fields.Msg_Email, this.Msg_Email);
        values.put(Fields.Msg_Address, this.Msg_Address);
        values.put(Fields.Msg_PostNum, this.Msg_PostNum);
        values.put(Fields.Msg_Publish_Interval, this.Msg_Publish_Interval);
        values.put(Fields.Msg_Info_Num, this.Msg_Info_Num);
        values.put(Fields.Msg_Info_Length, this.Msg_Info_Length);
        values.put(Fields.Msg_Registration_Changable, this.Msg_Registration_Changable);
        values.put(Fields.Msg_Password_Changable, this.Msg_Password_Changable);
        values.put(Fields.Msg_Suicide_Enabled, this.Msg_Suicide_Enabled);
        values.put(Fields.Msg_Back_Province, this.Msg_Back_Province);
        values.put(Fields.Msg_Back_City, this.Msg_Back_City);
    }

    @Override
    protected void readFieldValues(Cursor c) {
        super.readFieldValues(c);
        this.Msg_ID = c.getString(c.getColumnIndex(Fields.Msg_ID));
        this.Msg_Name = c.getString(c.getColumnIndex(Fields.Msg_Name));
        this.Msg_Sex = c.getString(c.getColumnIndex(Fields.Msg_Sex));
        this.Msg_Company = c.getString(c.getColumnIndex(Fields.Msg_Company));
        this.Msg_Tel = c.getString(c.getColumnIndex(Fields.Msg_Tel));
        this.Msg_Mobile = c.getString(c.getColumnIndex(Fields.Msg_Mobile));
        this.Msg_Expire = c.getString(c.getColumnIndex(Fields.Msg_Expire));
        this.Msg_Publish_Enabled = c.getString(c.getColumnIndex(Fields.Msg_Publish_Enabled));
        this.Msg_HandWriting_Enabled = c.getString(c.getColumnIndex(Fields.Msg_HandWriting_Enabled));
        this.Msg_Agent_Company = c.getString(c.getColumnIndex(Fields.Msg_Agent_Company));
        this.Msg_Agent_Tel = c.getString(c.getColumnIndex(Fields.Msg_Agent_Tel));
        this.Msg_Keyword_Filter = c.getString(c.getColumnIndex(Fields.Msg_Keyword_Filter));
        this.Msg_Net_Phone = c.getString(c.getColumnIndex(Fields.Msg_Net_Phone));
        this.Msg_Tel_2 = c.getString(c.getColumnIndex(Fields.Msg_Tel_2));
        this.Msg_City = c.getString(c.getColumnIndex(Fields.Msg_City));
        this.Msg_Province = c.getString(c.getColumnIndex(Fields.Msg_Province));
        this.Msg_WebSite = c.getString(c.getColumnIndex(Fields.Msg_WebSite));
        this.Msg_Email = c.getString(c.getColumnIndex(Fields.Msg_Email));
        this.Msg_Address = c.getString(c.getColumnIndex(Fields.Msg_Address));
        this.Msg_PostNum = c.getString(c.getColumnIndex(Fields.Msg_PostNum));
        this.Msg_Publish_Interval = c.getString(c.getColumnIndex(Fields.Msg_Publish_Interval));
        this.Msg_Info_Num = c.getString(c.getColumnIndex(Fields.Msg_Info_Num));
        this.Msg_Info_Length = c.getString(c.getColumnIndex(Fields.Msg_Info_Length));
        this.Msg_Registration_Changable = c.getString(c.getColumnIndex(Fields.Msg_Registration_Changable));
        this.Msg_Password_Changable = c.getString(c.getColumnIndex(Fields.Msg_Password_Changable));
        this.Msg_Suicide_Enabled = c.getString(c.getColumnIndex(Fields.Msg_Suicide_Enabled));
        this.Msg_Back_Province = c.getString(c.getColumnIndex(Fields.Msg_Back_Province));
        this.Msg_Back_City = c.getString(c.getColumnIndex(Fields.Msg_Back_City));
    }

    @Override
    protected void addFieldDefinitions(ArrayList<String> fieldDefinitions) {
        super.addFieldDefinitions(fieldDefinitions);
        fieldDefinitions.add(Fields.Msg_ID + " TEXT");
        fieldDefinitions.add(Fields.Msg_Name + " TEXT");
        fieldDefinitions.add(Fields.Msg_Sex + " TEXT");
        fieldDefinitions.add(Fields.Msg_Company + " TEXT");
        fieldDefinitions.add(Fields.Msg_Tel + " TEXT");
        fieldDefinitions.add(Fields.Msg_Mobile + " TEXT");
        fieldDefinitions.add(Fields.Msg_Expire + " TEXT");
        fieldDefinitions.add(Fields.Msg_Publish_Enabled + " TEXT");
        fieldDefinitions.add(Fields.Msg_HandWriting_Enabled + " TEXT");
        fieldDefinitions.add(Fields.Msg_Agent_Company + " TEXT");
        fieldDefinitions.add(Fields.Msg_Agent_Tel + " TEXT");
        fieldDefinitions.add(Fields.Msg_Keyword_Filter + " TEXT");
        fieldDefinitions.add(Fields.Msg_Net_Phone + " TEXT");
        fieldDefinitions.add(Fields.Msg_Tel_2 + " TEXT");
        fieldDefinitions.add(Fields.Msg_City + " TEXT");
        fieldDefinitions.add(Fields.Msg_Province + " TEXT");
        fieldDefinitions.add(Fields.Msg_WebSite + " TEXT");
        fieldDefinitions.add(Fields.Msg_Email + " TEXT");
        fieldDefinitions.add(Fields.Msg_Address + " TEXT");
        fieldDefinitions.add(Fields.Msg_PostNum + " TEXT");
        fieldDefinitions.add(Fields.Msg_Publish_Interval + " TEXT");
        fieldDefinitions.add(Fields.Msg_Info_Num + " TEXT");
        fieldDefinitions.add(Fields.Msg_Info_Length + " TEXT");
        fieldDefinitions.add(Fields.Msg_Registration_Changable + " TEXT");
        fieldDefinitions.add(Fields.Msg_Password_Changable + " TEXT");
        fieldDefinitions.add(Fields.Msg_Suicide_Enabled + " TEXT");
        fieldDefinitions.add(Fields.Msg_Back_Province + " TEXT");
        fieldDefinitions.add(Fields.Msg_Back_City + " TEXT");
    }

    public class Fields extends Entity.Fields {
        public static final String Msg_ID = "ID";
        public static final String Msg_Name = "Name";
        public static final String Msg_Sex = "Sex";
        public static final String Msg_Company = "Company";
        public static final String Msg_Tel = "Tel";
        public static final String Msg_Mobile = "Mobile";
        public static final String Msg_Expire = "Expire";
        public static final String Msg_Publish_Enabled = "Publish_Enabled";
        public static final String Msg_HandWriting_Enabled = "HandWriting_Enabled";
        public static final String Msg_Agent_Company = "Agent_Company";
        public static final String Msg_Agent_Tel = "Agent_Tel";
        public static final String Msg_Keyword_Filter = "Keyword_Filter";
        public static final String Msg_Net_Phone = "Net_Phone";
        public static final String Msg_Tel_2 = "Tel_2";
        public static final String Msg_City = "City";
        public static final String Msg_Province = "Province";
        public static final String Msg_WebSite = "WebSite";
        public static final String Msg_Email = "Email";
        public static final String Msg_Address = "Address";
        public static final String Msg_PostNum = "PostNum";
        public static final String Msg_Publish_Interval = "Publish_Interval";
        public static final String Msg_Info_Num = "Info_Num";
        public static final String Msg_Info_Length = "Info_Length";
        public static final String Msg_Registration_Changable = "Registration_Changable";
        public static final String Msg_Password_Changable = "Password_Changable";
        public static final String Msg_Suicide_Enabled = "Suicide_Enabled";
        public static final String Msg_Back_Province = "Back_Province";
        public static final String Msg_Back_City = "Back_City";
    }

}
