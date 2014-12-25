package com.haohuotong.other;

import java.util.ArrayList;

import com.haohuotong.other.Entity;

public class EntitySet extends ArrayList<Entity> {
    private static final long serialVersionUID = -7779550560770346694L;
    private int mTotalNum = -1;
    private int mTotalPage = -1;

    public int getTotalNum() {
        return mTotalNum;
    }

    public void setTotalNum(int TotalNum) {
        this.mTotalNum = TotalNum;
    }

    public int getTotalPage() {
        return mTotalPage;
    }

    public void setTotalPage(int TotalPage) {
        this.mTotalPage = TotalPage;
    }

}
