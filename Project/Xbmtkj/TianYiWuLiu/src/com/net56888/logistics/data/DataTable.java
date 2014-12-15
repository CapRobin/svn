package com.net56888.logistics.data;

import java.util.Observable;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public abstract class DataTable extends Observable {
    protected DataBase mDataBase;
    public abstract String getTableName();
    public abstract String getTableCreateClause();
    
    public DataTable(DataBase database) {
        mDataBase = database;
    }
    
    public DataBase getDataBase() {
        return mDataBase;
    }
    
    public boolean insert(Entity entity) {
        return entity.insert(this);
    }
    
    public boolean update(Entity entity) {
        return entity.update(this);
    }
    
    public boolean delete(Entity entity) {
        return entity.delete(this);
    }
}
