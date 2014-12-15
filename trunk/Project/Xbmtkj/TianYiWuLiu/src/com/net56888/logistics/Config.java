package com.net56888.logistics;

import android.provider.Settings;

import com.net56888.logistics.data.InfoEntity;
import com.net56888.logistics.data.UserEntity;

public class Config {
    private String mCity = "银川";
    private InfoEntity.Type mType = InfoEntity.Type.All;
    private UserEntity mUser = null;
    private static Config instance = new Config();
    private String mUuid = null;

    private Config() {
    }

    public static Config getInstance() {
        return instance;
    }

    public String getCity() {
        return mCity;
    }

    public void setCity(String city) {
        mCity = city;
    }

    public InfoEntity.Type getType() {
        return mType;
    }

    public void setType(InfoEntity.Type Type) {
        this.mType = Type;
    }

    public UserEntity getUser() {
        return mUser;
    }

    public void setUser(UserEntity mUser) {
        this.mUser = mUser;
    }

    public String getUuid() {
        if (null == mUuid) {
            mUuid = Settings.Secure.getString(App.getAppContext().getContentResolver(),
                    android.provider.Settings.Secure.ANDROID_ID);
        }
        return mUuid;
    }

    public String getModel() {
        return android.os.Build.MODEL;
    }

}
