package com.haohuotong.other;

import android.database.Cursor;

import com.haohuotong.other.Entity;

public class LocationEntity extends Entity {
    private int Msg_ID;
    private String Msg_Location;
    private int Msg_ParentID;
    
    public LocationEntity() {
        this.Msg_ID = 0;
        this.Msg_Location = "";
        this.Msg_ParentID = 0;
    }

    public int getMsg_ID() {
        return Msg_ID;
    }

    public String getMsg_Location() {
        return Msg_Location;
    }

    public int getMsg_ParentID() {
        return Msg_ParentID;
    }

    @Override
    protected void readFieldValues(Cursor c) {
        super.readFieldValues(c);
        this.Msg_ID = c.getInt(c.getColumnIndex(Fields.Msg_ID));
        this.Msg_Location = c.getString(c.getColumnIndex(Fields.Msg_Location));
        this.Msg_ParentID = c.getInt(c.getColumnIndex(Fields.Msg_ParentID));
    }
    
    public class Fields extends Entity.Fields {
        public static final String Msg_ID = "ID";
        public static final String Msg_Location = "Location";
        public static final String Msg_ParentID = "ParentID";
    }
    

}
