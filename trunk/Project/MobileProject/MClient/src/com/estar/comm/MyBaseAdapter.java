package com.estar.comm;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.estar.NMGMClient.R;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

//自定义适配器
public	class MyBaseAdapter extends BaseAdapter{
		private String m_strTableType;
		private List<MsgInfos> newsItems;  
		private Context mContext;
		private String mStartCity="";
		private String mStopCity="";

		public MyBaseAdapter(Context context,List<MsgInfos> newsitems) {
			// TODO Auto-generated constructor stub
			this.mContext = context;
			this.newsItems = newsitems;  
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return newsItems.size(); 
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return newsItems.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}
		
		
		public void SetCitys(String strTableType,String startcity,String stopcity){
			if(startcity.equals("全国"))
			{
				startcity="";
			}
			this.mStartCity=startcity;
			if(stopcity.equals("全国"))
			{
				stopcity="";
			}
			this.mStopCity=stopcity;
			//查看信息类型
			this.m_strTableType=strTableType;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			if(convertView == null){  
				convertView = LayoutInflater.from(mContext).inflate(R.layout.listitem1, null);  
          }
		  
		//显示信息格式：时间:类型:内容
		  String strType,strTime,strContent,str,strKeyword;
		  strType=newsItems.get(position).getMsg_Type().toString();
		  strTime=newsItems.get(position).getMsg_AddTime().toString();
		  if(!strTime.equals("")){
				//更改日期格式
			  MsgInfos mis=new MsgInfos();
			  strTime=mis.GetTimeHM(strTime);
			}
		  
		  strContent=newsItems.get(position).getMsg_Content().toString();
		  if(strType.equals("0"))//货源
		  {
			  if(m_strTableType.equals("钢材信息")){
				  strType="钢";
			  }else{
				  strType="货";
			  }
			  
		  }else {
			  if(m_strTableType.equals("煤炭信息")){
				  strType="煤";
			  }else{
				  strType="车";
			  }
		  }
		//title
          TextView tvTitle = (TextView)convertView.findViewById(R.id.listitem1ItemTitle);  
		//content
          TextView tvContent = (TextView)convertView.findViewById(R.id.listitem1ItemText);  
		  //======================================
		  //如果搜索关键字不为空，则为搜索
		  if(!mStopCity.equals(""))
		  {
			  //---------------------------------------------------------
			  //判断搜索条件是否为多个
			  int result=mStopCity.indexOf(",");
			  if(result==-1){//单个关键字
	        	  strKeyword=mStopCity;
	          }else{//多个关键字
	        	  String[] strings = mStopCity.split(",");
	        	  int i=0,k=0;
	        	  for(;i<strings.length;i++){
	        		  k=i;
	        		  int result2=strContent.indexOf(strings[k]);
	        		  if(result2>=0)
	        			  break;
	        	  }
	        	  if(k>=strings.length)
	        		  k=strings.length-1;
	        	  strKeyword=strings[k];
	          }
			  //-----------------------------------------------------------
			  //===========使关键字变色=========================
		        SpannableString s = new SpannableString(strContent);
		        Pattern p = Pattern.compile(strKeyword);
		        Matcher m = p.matcher(s);
		        int iKey=0;
		        while (m.find()) {
		            int start = m.start();
		            int end = m.end();
		            s.setSpan(new ForegroundColorSpan(Color.RED), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		            if(iKey==0)
		        	{
		        		iKey=start;
		        	}
		        }
		        //str=strTime+"["+strType+"]...";
		        str="["+strType+"]...";
		        tvTitle.setText(str);
		        //tvContent.append("...");
		        //tvContent.append(s.subSequence(iKey, s.length()));
		        tvContent.setText(s.subSequence(iKey, s.length()));
				//================================================
		  }else
		  {
			  //str=strTime+"["+strType+"]";
			  str="["+strType+"]";
			  tvTitle.setText(str);
			  tvContent.setText(strContent); 
		  }
		  //======================================
          
		  return convertView;
		}

		/**  
       * 添加数据列表项  (追加)
       * @param newsitem  
       */ 
      public void appendNewsItem(MsgInfos newsitem){  
          newsItems.add(newsitem);  
          notifyDataSetChanged();
      }
      /**
       * 添加数据列表数组（加载）
       */
      public void appendNewItems(List<MsgInfos> newsitems){
    	  int iSize=newsitems.size();
    	  if(iSize>0){
    		  for(int i=0;i<iSize;i++){
    			  appendNewsItem(newsitems.get(i));
    		  }
    	  }
      }
      /**  
       * 添加数据列表项  （头部插入）
       * @param newsitem  
       */ 
      public void addNewsItem(MsgInfos newsitem){  
          //newsItems.add(newsitem);  
    	  //newsItems.clear();
          newsItems.add(0, newsitem);
          notifyDataSetChanged();
          
      }
      /**
       * 添加数据列表数组（刷新）
       */
      public void addNewItems(List<MsgInfos> newsitems){
    	  int iSize=newsitems.size();
    	  if(iSize>0){
    		  for(int i=0;i<iSize;i++){
    			  addNewsItem(newsitems.get(i));
    		  }
    	  }
      }
		
	}