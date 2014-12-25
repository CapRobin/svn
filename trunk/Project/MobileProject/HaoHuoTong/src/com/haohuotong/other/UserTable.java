package com.haohuotong.other;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.haohuotong.other.DataBase;
import com.haohuotong.other.DataTable;
import com.haohuotong.other.EntitySet;

public class UserTable extends DataTable {

    private static final String TABLE_NAME = "UserTable";
    
    public UserTable(DataBase database) {
        super(database);
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public String getTableCreateClause() {
        return (new UserEntity()).getCreateTableClause(TABLE_NAME);
    }

    public UserEntity fetch() {
        SQLiteDatabase db = mDataBase.open().getReadableDatabase();
        Cursor c = db.query(getTableName(), null, null, null, null, null, null);
        EntitySet result = new EntitySet();

        c.moveToFirst();
        while(!c.isAfterLast()) {
            UserEntity entity = new UserEntity();
            entity.getFieldValues(c);
            result.add(entity);
            c.moveToNext();
        }
        
        c.close();
        mDataBase.close();
        
        if (result.size() > 0) return (UserEntity)result.get(0);
        return null;
    }
    
    public void clear() {
        SQLiteDatabase db = mDataBase.open().getReadableDatabase();
        db.delete(getTableName(), null, null);
        mDataBase.close();
    }
}
