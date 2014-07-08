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
	///////////////////////�����������ӿ�/////////////////////////////////////////////
	public interface IListViewState
	{
		int LVS_NORMAL = 0;					//  ��ͨ״̬
		int LVS_PULL_REFRESH = 1;			//  ����ˢ��״̬
		int LVS_RELEASE_REFRESH = 2;		//  �ɿ�ˢ��״̬
		int LVS_LOADING = 3;				//  ����״̬
	}
	//
	public interface IOnRefreshListener
	{
		void OnRefresh();
	}
	//ͷ������ˢ����ز���
	private View mHeadView;				
	private TextView mRefreshTextview;
	private TextView mLastUpdateTextView;
	private ImageView mArrowImageView;
	private ProgressBar mHeadProgressBar;
	
	private int mHeadContentWidth;
	private int mHeadContentHeight;

	private IOnRefreshListener mOnRefreshListener;			// ͷ��ˢ�¼�����
	
	// ���ڱ�֤startY��ֵ��һ��������touch�¼���ֻ����¼һ��
	private boolean mIsRecord = false;
	// ��ǵ�Y����ֵ
	private int mStartY = 0;
	// ��ǰ��ͼ�ܿ����ĵ�һ���������
	private int mFirstItemIndex = -1;
	// MOVEʱ�����Y����ֵ
	private int mMoveY = 0;
	// LISTVIEW״̬
	private int mViewState = IListViewState.LVS_NORMAL;

	private final static int RATIO = 2;
	
	private RotateAnimation animation;
	private RotateAnimation reverseAnimation;
	private boolean mBack = false;
	//
	private Context mContext;
	///////////////////////////////////////////////////////////////////////////
	//====================�Զ����๹�캯��=======================================
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
		//��ʼ��ͷ��������ˢ�£�
		initHeadView(context);
		//��ʼ���ײ�������鿴���ࣩ
		initLoadMoreView(context);
		//���ù�������
		setOnScrollListener(this);
	}
	//��ʼ��ͷ��headview��ͼ������ˢ�£�
	private void initHeadView(Context context) {
		// TODO Auto-generated method stub
		mHeadView = LayoutInflater.from(context).inflate(R.layout.head, null);//head.xml
		///head.xml������Ŀ
		mArrowImageView = (ImageView) mHeadView.findViewById(R.id.head_arrowImageView);
		mArrowImageView.setMinimumWidth(60);
		mHeadProgressBar= (ProgressBar) mHeadView.findViewById(R.id.head_progressBar);
		mRefreshTextview = (TextView) mHeadView.findViewById(R.id.head_tipsTextView);
		mLastUpdateTextView = (TextView) mHeadView.findViewById(R.id.head_lastUpdatedTextView);
		//ȡ��ͷ���Ŀ����߶�
		measureView(mHeadView);
		mHeadContentHeight = mHeadView.getMeasuredHeight();
		mHeadContentWidth = mHeadView.getMeasuredWidth();
		mHeadView.setPadding(0, -1 * mHeadContentHeight, 0, 0);
		mHeadView.invalidate();
		//���HeaderView
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
	/*�˷���ֱ���հ��������ϵ�һ������ˢ�µ�demo��
	 * ����headView��width�Լ�height
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
	
	//���ͷ����������
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
	
	// �л�headview��ͼ
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

				// ����RELEASE_To_REFRESH״̬ת������
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

	/////////////////////�ײ���ز���������/////////////////////////////////////////////////
	private View mFootView;								
	private View mLoadMoreView;
	private TextView mLoadMoreTextView;
	private View mLoadingView;
	private IOnLoadMoreListener mLoadMoreListener;						// ���ظ��������
	private int mLoadMoreState = IListViewState.LVS_NORMAL;
	private boolean bLoadMoreComplete=false;
	
	public interface ILoadMoreViewState
	{
		int LMVS_NORMAL= 0;					//  ��ͨ״̬
		int LMVS_LOADING = 1;				//  ����״̬
		int LMVS_OVER = 2;					//  ����״̬
	}
	
	public interface IOnLoadMoreListener
	{
		void OnLoadMore();
	}
	
	public void setOnLoadMoreListener(IOnLoadMoreListener listener)
	{
		mLoadMoreListener = listener;
	}

	//��ʼ���ײ�footview��ͼ������鿴���ࣩ
	private void initLoadMoreView(Context context) {
		// TODO Auto-generated method stub
		mFootView= LayoutInflater.from(context).inflate(R.layout.loadmore, null);
		//
		mLoadMoreView = mFootView.findViewById(R.id.load_more_view);
		mLoadMoreTextView = (TextView) mFootView.findViewById(R.id.load_more_tv);
		mLoadingView = mFootView.findViewById(R.id.loading_layout);
		//���õ�������
		mLoadMoreView.setOnClickListener(this);
		//��ӵײ�
		addFooterView(mFootView);
	}
	//============================================================================
	//==========================�ӿں���========================================
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
					//LoadMoreInfos();//���ظ���
				}
			}
			break;
		}
	}
	//���ظ���
	private void LoadMoreInfos() {
		// TODO Auto-generated method stub
		updateLoadMoreViewState(ILoadMoreViewState.LMVS_LOADING);
		mLoadMoreListener.OnLoadMore();
	}

	////flag �����Ƿ���ȫ���������
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
				//��ӵײ�
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
				//ȥ��
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
		//����ƶ������һ�У�����ظ��ࣻ
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
