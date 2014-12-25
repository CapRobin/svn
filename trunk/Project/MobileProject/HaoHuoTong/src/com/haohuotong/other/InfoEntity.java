package com.haohuotong.other;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

public class InfoEntity extends Entity implements Parcelable {

    private String Msg_ID;
    private String Msg_Code;
    private String Msg_Type;
    private String Msg_Content;
    private String Msg_Tel;
    private String Msg_NetPhone;
    private String Msg_Flag;
    private String Msg_Deal;
    private String Msg_Success;
    private String Msg_Date;
    private String Msg_StartCity;

    public InfoEntity() {
        super();
        this.Msg_ID = "";
        this.Msg_Code = "";
        this.Msg_Type = "";
        this.Msg_Content = "";
        this.Msg_Tel = "";
        this.Msg_NetPhone = "";
        this.Msg_Flag = "";
        this.Msg_Deal = "";
        this.Msg_Success = "";
        this.Msg_Date = "";
        this.Msg_StartCity = "";
    }

    public InfoEntity(String string) {
        super();
        String[] strings = string.split("\\|\\|");
        int i = 0;
        this.Msg_ID = strings[i++];
        this.Msg_Code = strings[i++];
        this.Msg_Type = strings[i++];
        this.Msg_Content = strings[i++];
        this.Msg_Tel = strings[i++];
        // FIXME: this.Msg_NetPhone = strings[i++];
        this.Msg_Flag = strings[i++];
        // FIXME: this.Msg_Deal = strings[i++];
        this.Msg_Success = strings[i++];
        this.Msg_Date = strings[i++];
        // FIXME: this.Msg_StartCity = strings[i++];
    }

    public String getMsg_Code() {
        return Msg_Code;
    }

    public void setMsg_Code(String msg_Code) {
        Msg_Code = msg_Code;
    }

    public String getMsg_Type() {
        return Msg_Type;
    }

    public void setMsg_Type(String msg_Type) {
        Msg_Type = msg_Type;
    }

    public String getMsg_Content() {
        return Msg_Content;
    }

    public void setMsg_Content(String msg_Content) {
        Msg_Content = msg_Content;
    }

    public String getMsg_Tel() {
        return Msg_Tel;
    }

    public void setMsg_Tel(String msg_Tel) {
        Msg_Tel = msg_Tel;
    }

    public String getMsg_Flag() {
        return Msg_Flag;
    }

    public void setMsg_Flag(String msg_Flag) {
        Msg_Flag = msg_Flag;
    }

    public String getMsg_NetPhone() {
        return Msg_NetPhone;
    }

    public void setMsg_NetPhone(String msg_NetPhone) {
        Msg_NetPhone = msg_NetPhone;
    }

    public String getMsg_ID() {
        return Msg_ID;
    }

    public void setMsg_ID(String msg_ID) {
        Msg_ID = msg_ID;
    }

    public String getMsg_Deal() {
        return Msg_Deal;
    }

    public void setMsg_Deal(String msg_Deal) {
        Msg_Deal = msg_Deal;
    }

    public String getMsg_Success() {
        return Msg_Success;
    }

    public void setMsg_Success(String msg_Success) {
        Msg_Success = msg_Success;
    }

    public String getMsg_Date() {
        return Msg_Date;
    }

    public void setMsg_Date(String msg_Date) {
        Msg_Date = msg_Date;
    }

    public String getMsg_StartCity() {
        return Msg_StartCity;
    }

    public void setMsg_StartCity(String msg_StartCity) {
        Msg_StartCity = msg_StartCity;
    }

    public static enum Type {
        CargoInfo, TruckInfo, All;

        public static int toInteger(Type type) {
            switch (type) {
                case CargoInfo:
                    return 0;
                case TruckInfo:
                    return 1;
                case All:
                    return 8;
                default:
                    return -1;
            }
        }

        public static String toString(Type type) {
            return String.valueOf(toInteger(type));
        }

        public static Type fromInt(int i) {
            switch (i) {
                case 0:
                    return CargoInfo;
                case 1:
                    return TruckInfo;
                default:
                    return All;
            }
        }
    }

    @Override
    protected void writeFieldValues(ContentValues values) {
        super.writeFieldValues(values);
        values.put(Fields.MSG_ID, this.Msg_ID);
        values.put(Fields.MSG_CODE, this.Msg_Code);
        values.put(Fields.MSG_TYPE, this.Msg_Type);
        values.put(Fields.MSG_CONTENT, this.Msg_Content);
        values.put(Fields.MSG_TEL, this.Msg_Tel);
        values.put(Fields.MSG_NETPHONE, this.Msg_NetPhone);
        values.put(Fields.MSG_FLAG, this.Msg_Flag);
        values.put(Fields.MSG_DEAL, this.Msg_Deal);
        values.put(Fields.MSG_DATE, this.Msg_Date);
        values.put(Fields.MSG_STARTCITYP, this.Msg_StartCity);
    }

    @Override
    protected void readFieldValues(Cursor c) {
        super.readFieldValues(c);
        this.Msg_ID = c.getString(c.getColumnIndex(Fields.MSG_ID));
        this.Msg_Code = c.getString(c.getColumnIndex(Fields.MSG_CODE));
        this.Msg_Type = c.getString(c.getColumnIndex(Fields.MSG_TYPE));
        this.Msg_Content = c.getString(c.getColumnIndex(Fields.MSG_CONTENT));
        this.Msg_Tel = c.getString(c.getColumnIndex(Fields.MSG_TEL));
        this.Msg_NetPhone = c.getString(c.getColumnIndex(Fields.MSG_NETPHONE));
        this.Msg_Flag = c.getString(c.getColumnIndex(Fields.MSG_FLAG));
        this.Msg_Deal = c.getString(c.getColumnIndex(Fields.MSG_DEAL));
        this.Msg_Date = c.getString(c.getColumnIndex(Fields.MSG_DATE));
        this.Msg_StartCity = c.getString(c
                .getColumnIndex(Fields.MSG_STARTCITYP));
    }

    @Override
    protected void addFieldDefinitions(ArrayList<String> fieldDefinitions) {
        super.addFieldDefinitions(fieldDefinitions);
        fieldDefinitions.add(Fields.MSG_ID + " TEXT");
        fieldDefinitions.add(Fields.MSG_CODE + " TEXT");
        fieldDefinitions.add(Fields.MSG_TYPE + " TEXT");
        fieldDefinitions.add(Fields.MSG_CONTENT + " TEXT");
        fieldDefinitions.add(Fields.MSG_TEL + " TEXT");
        fieldDefinitions.add(Fields.MSG_NETPHONE + " TEXT");
        fieldDefinitions.add(Fields.MSG_FLAG + " TEXT");
        fieldDefinitions.add(Fields.MSG_DEAL + " TEXT");
        fieldDefinitions.add(Fields.MSG_DATE + " TEXT");
        fieldDefinitions.add(Fields.MSG_STARTCITYP + " TEXT");
    }

    public class Fields extends Entity.Fields {
        public static final String MSG_ID = "ID";
        public static final String MSG_CODE = "Code";
        public static final String MSG_TYPE = "Type";
        public static final String MSG_CONTENT = "Conent";
        public static final String MSG_TEL = "Tel";
        public static final String MSG_NETPHONE = "NetPhone";
        public static final String MSG_FLAG = "Flag";
        public static final String MSG_DEAL = "Deal";
        public static final String MSG_DATE = "Date";
        public static final String MSG_STARTCITYP = "StartCity";
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Msg_ID);
        dest.writeString(this.Msg_Code);
        dest.writeString(this.Msg_Type);
        dest.writeString(this.Msg_Content);
        dest.writeString(this.Msg_Tel);
        dest.writeString(this.Msg_NetPhone);
        dest.writeString(this.Msg_Flag);
        dest.writeString(this.Msg_Deal);
        dest.writeString(this.Msg_Success);
        dest.writeString(this.Msg_Date);
        dest.writeString(this.Msg_StartCity);
    }

    // this is used to regenerate your object. All Parcelables must have a
    // CREATOR that implements these two methods
    public static final Parcelable.Creator<InfoEntity> CREATOR = new Parcelable.Creator<InfoEntity>() {
        public InfoEntity createFromParcel(Parcel in) {
            return new InfoEntity(in);
        }

        public InfoEntity[] newArray(int size) {
            return new InfoEntity[size];
        }
    };

    private InfoEntity(Parcel in) {
        this.Msg_ID = in.readString();
        this.Msg_Code = in.readString();
        this.Msg_Type = in.readString();
        this.Msg_Content = in.readString();
        this.Msg_Tel = in.readString();
        this.Msg_NetPhone = in.readString();
        this.Msg_Flag = in.readString();
        this.Msg_Deal = in.readString();
        this.Msg_Success = in.readString();
        this.Msg_Date = in.readString();
        this.Msg_StartCity = in.readString();
    }

}
