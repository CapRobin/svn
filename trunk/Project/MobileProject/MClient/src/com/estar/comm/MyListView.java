package com.estar.comm;

import java.util.Date;

import com.estar.NMGMClient.R;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.AbsListView.OnScrollListener;

public class MyListView extends ListView implements OnScrollListener, OnClickListener{

	private final String TAG = "--------MyListView----------";
	///////////////////////公共参数及接口/////////////////////////////////////////////
	public interface IListViewState
	{
		int LVS_NORMAL = 0;					//  普通状态
		int LVS_PULL_REFRESH = 1;			//  下拉刷新状态
		int LVS_RELEASE_REFRESH = 2;		//  松开刷新状态
		int LVS_LOADING = 3;				//  加载状态
	}
	//
	public interface IOnRefreshListener
	{
		void OnRefresh();
	}
	//头部下拉刷新相关参数
	private View mHeadView;				
	private TextView mRefreshTextview;
	private TextView mLastUpdateTextView;
	private ImageView mArrowImageView;
	private ProgressBar mHeadProgressBar;
	
	private int mHeadContentWidth;
	private int mHeadContentHeight;

	private IOnRefreshListener mOnRefreshListener;			// 头部刷新监听器
	
	// 用于保证startY的值在一个完整的touch事件中只被记录一次
	private boolean mIsRecord = false;
	// 标记的Y坐标值
	private int mStartY = 0;
	// 当前视图能看到的第一个项的索引
	private int mFirstItemIndex = -1;
	// MOVE时保存的Y坐标值
	private int mMoveY = 0;
	// LISTVIEW状态
	private int mViewState = IListViewState.LVS_NORMAL;

	private final static int RATIO = 2;
	
	private RotateAnimation animation;
	private RotateAnimation reverseAnimation;
	private boolean mBack = false;
	//
	private Context mContext;
	///////////////////////////////////////////////////////////////////////////
	//====================自定义类构造函数=======================================
	public MyListView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init(context);
		mContext=context;
	}

	public MyListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init(context);
		mContext=context;
	}
	//
	private void init(Context context) {
		// TODO Auto-generated method stub
		//初始化头部（下拉刷新）
		initHeadView(context);
		//初始化底部（点击查看更多）
		initLoadMoreView(context);
		//设置滚动监听
		setOnScrollListener(this);
	}
	//初始化头部headview试图（下拉刷新）
	private void initHeadView(Context context) {
		// TODO Auto-generated method stub
		mHeadView = LayoutInflater.from(context).inflate(R.layout.head, null);//head.xml
		///head.xml中子栏目
		mArrowImageView = (ImageView) mHeadView.findViewById(R.id.head_arrowImageView);
		mArrowImageView.setMinimumWidth(60);
		mHeadProgressBar= (ProgressBar) mHeadView.findViewById(R.id.head_progressBar);
		mRefreshTextview = (TextView) mHeadView.findViewById(R.id.head_tipsTextView);
		mLastUpdateTextView = (TextView) mHeadView.findViewById(R.id.head_lastUpdatedTextView);
		//取得头部的宽度与高度
		measureView(mHeadView);
		mHeadContentHeight = mHeadView.getMeasuredHeight();
		mHeadContentWidth = mHeadView.getMeasuredWidth();
		mHeadView.setPadding(0, -1 * mHeadContentHeight, 0, 0);
		mHeadView.invalidate();
		//添加HeaderView
		addHeaderView(mHeadView, null, false);
		//
		animation = new RotateAnimation(0, -180,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f);
		animation.setInterpolator(new LinearInterpolator());
		animation.setDuration(250);
		animation.setFillAfter(true);

		reverseAnimation = new RotateAnimation(-180, 0,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f,
				RotateAnimation.RELATIVE_TO_SELF, 0.5f);
		reverseAnimation.setInterpolator(new LinearInterpolator());
		reverseAnimation.setDuration(200);
		reverseAnimation.setFillAfter(true);
	}
	/*此方法直接照搬自网络上的一个下拉刷新的demo，
	 * 计算headView的width以及height
	 */
	private void measureView(View child) {
		// TODO Auto-generated method stub
		ViewGroup.LayoutParams p = child.getLayoutParams();
		if (p == null) {
			p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
					ViewGroup.LayoutParams.WRAP_CONTENT);
		}
		int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0 + 0, p.width);
		int lpHeight = p.height;
		int childHeightSpec;
		if (lpHeight > 0) {
			childHeightSpec = MeasureSpec.makeMeasureSpec(lpHeight,
					MeasureSpec.EXACTLY);
		} else {
			childHeightSpec = MeasureSpec.makeMeasureSpec(0,
					MeasureSpec.UNSPECIFIED);
		}
		child.measure(childWidthSpec, childHeightSpec);
	}
	
	//相关头部其他函数
	public void setOnRefreshListener(IOnRefreshListener listener)
	{
		mOnRefreshListener = listener;
	}
	
	private void onRefresh()
	{
		if (mOnRefreshListener != null)
		{
			mOnRefreshListener.OnRefresh();
		}
	}
	
	public String GetCurTime(){
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	    java.util.Date currentTime_1 = new java.util.Date(); 
	    String now=formatter.format(currentTime_1);
	    return now;
	}
	
	public void onRefreshComplete()
	{
		mHeadView.setPadding(0,  -1 * mHeadContentHeight, 0, 0);
		String str=mContext.getString(R.string.string01);
		mLastUpdateTextView.setText(str+ GetCurTime());
		switchViewState(IListViewState.LVS_NORMAL);
	}
	
	// 切换headview视图
	private void switchViewState(int state) {
		// TODO Auto-generated method stub
		switch(state)
		{
			case IListViewState.LVS_NORMAL:
			{
				//Log.e(TAG, "convert to IListViewState.LVS_NORMAL");
				mArrowImageView.clearAnimation();
				mArrowImageView.setImageResource(R.drawable.arrow);
			}
				break;
			case IListViewState.LVS_PULL_REFRESH:
			{
				//Log.e(TAG, "convert to IListViewState.LVS_PULL_REFRESH");
				mHeadProgressBar.setVisibility(View.GONE);
				mArrowImageView.setVisibility(View.VISIBLE);
				mRefreshTextview.setText(R.string.string02);
				mArrowImageView.clearAnimation();

				// 是由RELEASE_To_REFRESH状态转变来的
				if (mBack)
				{
					mBack = false;
					mArrowImageView.clearAnimation();
					mArrowImageView.startAnimation(reverseAnimation);
				}
			}
				break;
			case IListViewState.LVS_RELEASE_REFRESH:
			{
				//Log.e(TAG, "convert to IListViewState.LVS_RELEASE_REFRESH");
				mHeadProgressBar.setVisibility(View.GONE);
				mArrowImageView.setVisibility(View.VISIBLE);
				mRefreshTextview.setText(R.string.string03);
				mArrowImageView.clearAnimation();
				mArrowImageView.startAnimation(animation);
			}
				break;
			case IListViewState.LVS_LOADING:
			{
				//Log.e(TAG, "convert to IListViewState.LVS_LOADING");
				mHeadProgressBar.setVisibility(View.VISIBLE);
				mArrowImageView.clearAnimation();
				mArrowImageView.setVisibility(View.GONE);
				mRefreshTextview.setText(R.string.string04);
			}
				break;
				default:
					return;
		}	
		
		mViewState = state;
	}
	//
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		if (mOnRefreshListener != null)
		{
			switch (ev.getAction()) {
			case MotionEvent.ACTION_DOWN:		
				doActionDown(ev);
				break;
			case MotionEvent.ACTION_MOVE:
				doActionMove(ev);
				break;
			case MotionEvent.ACTION_UP:
				doActionUp(ev);
				break;
			default:
				break;
			}	
		}
		return super.onTouchEvent(ev);
	}
	//
	private void doActionDown(MotionEvent ev)
	{
		if(mIsRecord == false && mFirstItemIndex == 0)
		{
			mStartY = (int) ev.getY();
			mIsRecord = true;
		}
	}
	
	private void doActionMove(MotionEvent ev)
	{
		mMoveY = (int) ev.getY();
		
		if(mIsRecord == false && mFirstItemIndex == 0)
		{
			mStartY = (int) ev.getY();
			mIsRecord = true;
		}
		
		if (mIsRecord == false || mViewState == IListViewState.LVS_LOADING)
		{
			return ;
		}	
		
		int offset = (mMoveY - mStartY) / RATIO;	
		
		switch(mViewState)
		{
			case IListViewState.LVS_NORMAL:
			{
				if (offset > 0)
				{		
					mHeadView.setPadding(0, offset - mHeadContentHeight, 0, 0);
					switchViewState(IListViewState.LVS_PULL_REFRESH);
				}
			}
				break;
			case IListViewState.LVS_PULL_REFRESH:
			{
				setSelection(0);
				mHeadView.setPadding(0, offset - mHeadContentHeight, 0, 0);
				if (offset < 0)
				{
					switchViewState(IListViewState.LVS_NORMAL);
				}else if (offset > mHeadContentHeight)
				{
					switchViewState(IListViewState.LVS_RELEASE_REFRESH);
				}
			}
				break;
			case IListViewState.LVS_RELEASE_REFRESH:
			{
				setSelection(0);
				mHeadView.setPadding(0, offset - mHeadContentHeight, 0, 0);
				if (offset >= 0 && offset <= mHeadContentHeight)
				{
					mBack = true;
					switchViewState(IListViewState.LVS_PULL_REFRESH);
				}else if (offset < 0)
				{
					switchViewState(IListViewState.LVS_NORMAL);
				}else{
				
				}
				
			}
				break;
			default:
				return;
		};		
		
	}
	
	private void doActionUp(MotionEvent ev)
	{
		mIsRecord = false;
		mBack = false;
		
		if (mViewState == IListViewState.LVS_LOADING)
		{
			return ;
		}
		
		switch(mViewState)
		{
		case IListViewState.LVS_NORMAL:
		
			break;
		case IListViewState.LVS_PULL_REFRESH:
			mHeadView.setPadding(0, -1 * mHeadContentHeight, 0, 0);
			switchViewState(IListViewState.LVS_NORMAL);
			break;
		case IListViewState.LVS_RELEASE_REFRESH:
			mHeadView.setPadding(0, 0, 0, 0);
			switchViewState(IListViewState.LVS_LOADING);
			onRefresh();
			break;
		}	
		
	}

	/////////////////////底部相关参数及函数/////////////////////////////////////////////////
	private View mFootView;								
	private View mLoadMoreView;
	private TextView mLoadMoreTextView;
	private View mLoadingView;
	private IOnLoadMoreListener mLoadMoreListener;						// 加载更多监听器
	private int mLoadMoreState = IListViewState.LVS_NORMAL;
	private boolean bLoadMoreComplete=false;
	
	public interface ILoadMoreViewState
	{
		int LMVS_NORMAL= 0;					//  普通状态
		int LMVS_LOADING = 1;				//  加载状态
		int LMVS_OVER = 2;					//  结束状态
	}
	
	public interface IOnLoadMoreListener
	{
		void OnLoadMore();
	}
	
	public void setOnLoadMoreListener(IOnLoadMoreListener listener)
	{
		mLoadMoreListener = listener;
	}

	//初始化底部footview试图（点击查看更多）
	private void initLoadMoreView(Context context) {
		// TODO Auto-generated method stub
		mFootView= LayoutInflater.from(context).inflate(R.layout.loadmore, null);
		//
		mLoadMoreView = mFootView.findViewById(R.id.load_more_view);
		mLoadMoreTextView = (TextView) mFootView.findViewById(R.id.load_more_tv);
		mLoadingView = mFootView.findViewById(R.id.loading_layout);
		//设置单击监听
		mLoadMoreView.setOnClickListener(this);
		//添加底部
		addFooterView(mFootView);
	}
	//============================================================================
	//==========================接口函数========================================
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
			case R.id.load_more_view:
			{
				if (mLoadMoreListener != null && mLoadMoreState == IListViewState.LVS_NORMAL)
				{
					updateLoadMoreViewState(ILoadMoreViewState.LMVS_LOADING);
					mLoadMoreListener.OnLoadMore();
					//LoadMoreInfos();//加载更多
				}
			}
			break;
		}
	}
	//加载更多
	private void LoadMoreInfos() {
		// TODO Auto-generated method stub
		updateLoadMoreViewState(ILoadMoreViewState.LMVS_LOADING);
		mLoadMoreListener.OnLoadMore();
	}

	////flag 数据是否已全部加载完毕
	public void onLoadMoreComplete(boolean flag)
	{
		bLoadMoreComplete=flag;
		if (flag)
		{
			updateLoadMoreViewState(ILoadMoreViewState.LMVS_OVER);
		}else{
			updateLoadMoreViewState(ILoadMoreViewState.LMVS_NORMAL);
		}
		
	}
	//
	private void updateLoadMoreViewState(int state) {
		// TODO Auto-generated method stub
		switch(state)
		{
			case ILoadMoreViewState.LMVS_NORMAL:
				//添加底部
				//addFooterView(mFootView);
				//
				mLoadingView.setVisibility(View.GONE);
				mLoadMoreTextView.setVisibility(View.VISIBLE);
				mLoadMoreTextView.setText(R.string.string05);
				break;
			case ILoadMoreViewState.LMVS_LOADING:
				mLoadingView.setVisibility(View.VISIBLE);
				mLoadMoreTextView.setVisibility(View.GONE);
				break;
			case ILoadMoreViewState.LMVS_OVER:
				mLoadingView.setVisibility(View.GONE);
				//mLoadMoreTextView.setVisibility(View.VISIBLE);
				//mLoadMoreTextView.setText(R.string.string06);
				mLoadMoreTextView.setVisibility(View.GONE);
				//去掉
				//removeFootView();
				break;
				default:
					break;
		}
		
		mLoadMoreState = state;
	}
	//
	private void removeFootView() {
		// TODO Auto-generated method stub
		removeFooterView(mFootView);
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub
		mFirstItemIndex = firstVisibleItem;
		//如果移动到最后一行，则加载更多；
		/*Log.e(TAG+"visibleItemCount",String.valueOf(visibleItemCount));
		Log.e(TAG+"firstVisibleItem",String.valueOf(firstVisibleItem));
		Log.e(TAG+"totalItemCount",String.valueOf(totalItemCount));
		if(view.getLastVisiblePosition() + 1 == totalItemCount){
			if(totalItemCount>0 && !bLoadMoreComplete){
				Log.e(TAG,"onScroll_end,then loadmoreinfos");
				LoadMoreInfos();
			}
		}*/
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
		
	}
	//============================================================================

}
