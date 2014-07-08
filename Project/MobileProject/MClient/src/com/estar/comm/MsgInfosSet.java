package com.estar.comm;

import java.util.ArrayList;

public class MsgInfosSet extends ArrayList<MsgInfos>{
	
	private final int mPageSize = 20;
    private int mTotalNum = -1;
    private int mTotalPage = -1;
    private long mMaxID=0;

    
    public int getmPageSize() {
		return mPageSize;
	}
    
    public int getmTotalNum() {
		return mTotalNum;
	}
    
    public int getmTotalPage() {
		return mTotalPage;
	}
    
    public long getmMaxID() {
		return mMaxID;
	}
    
    public void setmTotalNum(int mTotalNum) {
		this.mTotalNum = mTotalNum;
	}
    
    public void setmTotalPage(int mTotalPage) {
		this.mTotalPage = mTotalPage;
	}
    
    public void setmMaxID(long lMaxID) {
		this.mMaxID = lMaxID;
	}
    
    public boolean showBottomView(){
    	boolean show=true;
    	if(mTotalPage<=1){
    		show=false;
    	}
    	return show;
    }
}
