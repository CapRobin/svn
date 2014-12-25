package com.haohuotong.other;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.haohuotong.other.DataTable;
import com.haohuotong.other.EntitySet;

public class LocationTable extends DataTable {
    
    private static final String TABLE_NAME = "LocationTable";

    public LocationTable(LogisticsDB logisticsDB) {
        super(logisticsDB);
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public String getTableCreateClause() {
        return (new LocationEntity()).getCreateTableClause(TABLE_NAME);
    }
    
    public EntitySet fetchByParentID(int parentId) {
        String selection = LocationEntity.Fields.Msg_ParentID + " = ?";
        String selectionArgs[] = new String[] { String.valueOf(parentId) };
        return fetchByConditions(selection, selectionArgs);
    }

    public EntitySet fetchByConditions(String selection, String selectionArgs[]) {
        SQLiteDatabase db = mDataBase.open().getReadableDatabase();
        Cursor c = db.query(getTableName(), null, selection, selectionArgs, null, null, null);
        EntitySet result = new EntitySet();

        c.moveToFirst();
        while(!c.isAfterLast()) {
            LocationEntity entity = new LocationEntity();
            entity.getFieldValues(c);
            result.add(entity);
            c.moveToNext();
        }
        c.close();
        mDataBase.close();
        
        return result;
    }

}
