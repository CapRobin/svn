package com.net56888.logistics.data;

import java.util.ArrayList;

import android.content.ContentValues;
import android.database.Cursor;

public abstract class Record {
    
    private ContentValues mFieldValues;
    private ArrayList<String> mFieldDefinitions;
    
    public Record() {
        mFieldValues = new ContentValues();
        mFieldDefinitions = new ArrayList<String>(); 
    }
    
    public final ContentValues setFieldValues() {
        mFieldValues.clear();  
        writeFieldValues(mFieldValues);
        return mFieldValues;
    }
    
    public final void getFieldValues(Cursor c) {
        readFieldValues(c);
    }

    public final String getCreateTableClause(String tableName) {
        mFieldDefinitions.clear();
        addFieldDefinitions(mFieldDefinitions);
        
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE ").append(tableName);
        sb.append("(");
        for (String def : mFieldDefinitions) {
            sb.append(def).append(",");
        }
        int idx = sb.lastIndexOf(",");
        if (idx != -1) {
            sb.deleteCharAt(idx);
        }
        sb.append(");");
        return sb.toString();
    }

    public abstract boolean insert(DataTable table);

    public abstract boolean update(DataTable table);

    public abstract boolean delete(DataTable table);
    
    protected abstract void writeFieldValues(ContentValues values);
    
    protected abstract void readFieldValues(Cursor c);
    
    protected abstract void addFieldDefinitions(ArrayList<String> fieldDefinitions);
}
