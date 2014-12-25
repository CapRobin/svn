package com.haohuotong.other;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.haohuotong.activity.LunchActivity;
import com.haohuotong.global.Constant;
import com.haohuotong.util.MethodUtil;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class LogisticsDB extends DataBase {

    private static final String DATABASE_PATH = "/data/data/com.haohuotong/databases/";
    private static final String DATABASE_NAME = "Logistics";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase mDb;
    private final DBOpenHelper mOpenHelper;
    
    private LocationTable mLocationTable;
    private UserTable mUserTable;

    public LogisticsDB(Context context) {
        mOpenHelper = new DBOpenHelper(context);
        mLocationTable = new LocationTable(this);
        mUserTable = new UserTable(this);
    }
    
    public LocationTable getLocationTable() {
        return mLocationTable;
    }
    
    public UserTable getUserTable() {
        return mUserTable;
    }

    @Override
    public LogisticsDB open() throws SQLException {
        try {
            mOpenHelper.createDataBase();
            mOpenHelper.openDataBase();
        } catch (SQLException ex) {
            throw ex;
        } catch (IOException ex) {
            throw new Error("Unable to create database");
        }

        return this;
    }

    @Override
    public void close() {
        mOpenHelper.close();
    }

    private class DBOpenHelper extends SQLiteOpenHelper {
        private final Context mHelperContext;

        public DBOpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            mHelperContext = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }

        public void createDataBase() throws IOException {
            if (checkDataBase())
                return;

            try {
                copyDataBase();
                initDataBase();
        	
//			try {
//				if (!myCheckDataBase()) {
//					LunchActivity activity = new LunchActivity();
//					boolean boo = activity.isExist();
//					if (boo) {
//						System.out.println("成功！");
//					}
//				} else {
//					return;
//				}
        	
            } catch (Exception e) {
                throw new Error("Error copying database");
            }
        }

        
//        private void isExit() {
//			File f = new File(Constant.dbPath);
//			if (!f.exists()) {
//				boolean ret = MethodUtil.importDatabase(LogisticsDB.this,f);
//				if(ret){
//					//sendMsgUpdateUI(1,"数据库加载成功...");
//				}else{
//					sendMsgUpdateUI(0,"数据库加载失败!");
//					finish();
//				}
//			}else{
//				//sendMsgUpdateUI(1,"数据库加载成功...");
//			}
//        	
//		}
        /*
        public SQLiteDatabase getDatabase() {
            String myPath = DATABASE_PATH + DATABASE_NAME;
            return SQLiteDatabase.openDatabase(myPath, null,
                    SQLiteDatabase.OPEN_READONLY);
        }
        */

        private boolean myCheckDataBase() {
            SQLiteDatabase checkDB = null;
            try {
                String myPath = Constant.dbPath;
                checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
            } catch (SQLiteException e) {
            } finally {
                if (checkDB != null) {
                    checkDB.close();
                }
            }
            return checkDB != null ? true : false;
        }
    
        private boolean checkDataBase() {
            SQLiteDatabase checkDB = null;
            try {
                String myPath = DATABASE_PATH + DATABASE_NAME;
                checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
            } catch (SQLiteException e) {
            } finally {
                if (checkDB != null) {
                    checkDB.close();
                }
            }
            return checkDB != null ? true : false;
        }

        private void copyDataBase() throws IOException {
            File pathFile = new File(DATABASE_PATH);
            if ((pathFile != null) && (!pathFile.exists())) {
                pathFile.mkdirs();
            }
                     
            InputStream myInput = mHelperContext.getAssets()
                    .open(DATABASE_NAME);
            String outFileName = DATABASE_PATH + DATABASE_NAME;
            OutputStream myOutput = new FileOutputStream(outFileName);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }

            myOutput.flush();
            myOutput.close();
            myInput.close();
        }

        private void initDataBase() {
            String myPath = DATABASE_PATH + DATABASE_NAME;
            mDb = SQLiteDatabase.openDatabase(myPath, null,
                    SQLiteDatabase.OPEN_READWRITE);
            mDb.execSQL(mUserTable.getTableCreateClause());
            mDb.close();
            
        }

        public void openDataBase() throws SQLException {
            String myPath = DATABASE_PATH + DATABASE_NAME;
            mDb = SQLiteDatabase.openDatabase(myPath, null,
                    SQLiteDatabase.OPEN_READWRITE);
        }

        @Override
        public synchronized void close() {

            if (mDb != null)
                mDb.close();

            super.close();

        }

    }

    @Override
    public SQLiteDatabase getWritableDatabase() {
        return mDb;
    }

    @Override
    public SQLiteDatabase getReadableDatabase() {
        return getWritableDatabase();
    }

}
