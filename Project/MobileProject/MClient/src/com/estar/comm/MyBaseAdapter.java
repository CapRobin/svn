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

//�Զ���������
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
			if(startcity.equals("ȫ��"))
			{
				startcity="";
			}
			this.mStartCity=startcity;
			if(stopcity.equals("ȫ��"))
			{
				stopcity="";
			}
			this.mStopCity=stopcity;
			//�鿴��Ϣ����
			this.m_strTableType=strTableType;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			if(convertView == null){  
				convertView = LayoutInflater.from(mContext).inflate(R.layout.listitem1, null);  
          }
		  
		//��ʾ��Ϣ��ʽ��ʱ��:����:����
		  String strType,strTime,strContent,str,strKeyword;
		  strType=newsItems.get(position).getMsg_Type().toString();
		  strTime=newsItems.get(position).getMsg_AddTime().toString();
		  if(!strTime.equals("")){
				//�������ڸ�ʽ
			  MsgInfos mis=new MsgInfos();
			  strTime=mis.GetTimeHM(strTime);
			}
		  
		  strContent=newsItems.get(position).getMsg_Content().toString();
		  if(strType.equals("0"))//��Դ
		  {
			  if(m_strTableType.equals("�ֲ���Ϣ")){
				  strType="��";
			  }else{
				  strType="��";
			  }
			  
		  }else {
			  if(m_strTableType.equals("ú̿��Ϣ")){
				  strType="ú";
			  }else{
				  strType="��";
			  }
		  }
		//title
          TextView tvTitle = (TextView)convertView.findViewById(R.id.listitem1ItemTitle);  
		//content
          TextView tvContent = (TextView)convertView.findViewById(R.id.listitem1ItemText);  
		  //======================================
		  //��������ؼ��ֲ�Ϊ�գ���Ϊ����
		  if(!mStopCity.equals(""))
		  {
			  //---------------------------------------------------------
			  //�ж����������Ƿ�Ϊ���
			  int result=mStopCity.indexOf(",");
			  if(result==-1){//�����ؼ���
	        	  strKeyword=mStopCity;
	          }else{//����ؼ���
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
			  //===========ʹ�ؼ��ֱ�ɫ=========================
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
       * ��������б���  (׷��)
       * @param newsitem  
       */ 
      public void appendNewsItem(MsgInfos newsitem){  
          newsItems.add(newsitem);  
          notifyDataSetChanged();
      }
      /**
       * ��������б����飨���أ�
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
       * ��������б���  ��ͷ�����룩
       * @param newsitem  
       */ 
      public void addNewsItem(MsgInfos newsitem){  
          //newsItems.add(newsitem);  
    	  //newsItems.clear();
          newsItems.add(0, newsitem);
          notifyDataSetChanged();
          
      }
      /**
       * ��������б����飨ˢ�£�
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