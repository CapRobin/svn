package com.estar.comm;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.estar.NMGMClient.R;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.Log;

public class MySuperActivity extends Activity{

	private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() 
	{
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			finish();
			//System.exit(0);
		}
	};

	/* (non-Javadoc)
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		IntentFilter filter = new IntentFilter(); 
	    filter.addAction("ExitApp"); 
	    this.registerReceiver(broadcastReceiver, filter); 

	}
	///////////////////////////////////////////////////////////////////////////
	/*
	 * 城市列表相关数据
	 */
	//市，自治区集合
		protected int[] city = { R.array.quanguo_province_item,R.array.beijin_province_item,
				R.array.tianjin_province_item, R.array.heibei_province_item,
				R.array.shanxi1_province_item, R.array.neimenggu_province_item,
				R.array.liaoning_province_item, R.array.jilin_province_item,
				R.array.heilongjiang_province_item, R.array.shanghai_province_item,
				R.array.jiangsu_province_item, R.array.zhejiang_province_item,
				R.array.anhui_province_item, R.array.fujian_province_item,
				R.array.jiangxi_province_item, R.array.shandong_province_item,
				R.array.henan_province_item, R.array.hubei_province_item,
				R.array.hunan_province_item, R.array.guangdong_province_item,
				R.array.guangxi_province_item, R.array.hainan_province_item,
				R.array.chongqing_province_item, R.array.sichuan_province_item,
				R.array.guizhou_province_item, R.array.yunnan_province_item,
				R.array.xizang_province_item, R.array.shanxi2_province_item,
				R.array.gansu_province_item, R.array.qinghai_province_item,
				R.array.linxia_province_item, R.array.xinjiang_province_item,
				R.array.hongkong_province_item, R.array.aomen_province_item,
				R.array.taiwan_province_item };
		protected int[] countyOfQuanGuo = {};
		protected int[] countyOfBeiJing = { R.array.beijin_city_item };
		protected int[] countyOfTianJing = { R.array.tianjin_city_item };
		protected int[] countyOfHeBei = { R.array.shijiazhuang_city_item,
				R.array.tangshan_city_item, R.array.qinghuangdao_city_item,
				R.array.handan_city_item, R.array.xingtai_city_item,
				R.array.baoding_city_item, R.array.zhangjiakou_city_item,
				R.array.chengde_city_item, R.array.cangzhou_city_item,
				R.array.langfang_city_item, R.array.hengshui_city_item };
		protected int[] countyOfShanXi1 = { R.array.taiyuan_city_item,
				R.array.datong_city_item, R.array.yangquan_city_item,
				R.array.changzhi_city_item, R.array.jincheng_city_item,
				R.array.shuozhou_city_item, R.array.jinzhong_city_item,
				R.array.yuncheng_city_item, R.array.xinzhou_city_item,
				R.array.linfen_city_item, R.array.lvliang_city_item };
		protected int[] countyOfNeiMengGu = { R.array.huhehaote_city_item,
				R.array.baotou_city_item, R.array.wuhai_city_item,
				R.array.chifeng_city_item, R.array.tongliao_city_item,
				R.array.eerduosi_city_item, R.array.hulunbeier_city_item,
				R.array.bayannaoer_city_item, R.array.wulanchabu_city_item,
				R.array.xinganmeng_city_item, R.array.xilinguolemeng_city_item,
				R.array.alashanmeng_city_item };
		protected int[] countyOfLiaoNing = { R.array.shenyang_city_item,
				R.array.dalian_city_item, R.array.anshan_city_item,
				R.array.wushun_city_item, R.array.benxi_city_item,
				R.array.dandong_city_item, R.array.liaoning_jinzhou_city_item,
				R.array.yingkou_city_item, R.array.fuxin_city_item,
				R.array.liaoyang_city_item, R.array.panjin_city_item,
				R.array.tieling_city_item, R.array.zhaoyang_city_item,
				R.array.huludao_city_item };
		protected int[] countyOfJiLin = { R.array.changchun_city_item,
				R.array.jilin_city_item, R.array.siping_city_item,
				R.array.liaoyuan_city_item, R.array.tonghua_city_item,
				R.array.baishan_city_item, R.array.songyuan_city_item,
				R.array.baicheng_city_item, R.array.yanbian_city_item };
		protected int[] countyOfHeiLongJiang = { R.array.haerbing_city_item,
				R.array.qiqihaer_city_item, R.array.jixi_city_item,
				R.array.hegang_city_item, R.array.shuangyashan_city_item,
				R.array.daqing_city_item, R.array.heilongjiang_yichun_city_item,
				R.array.jiamusi_city_item, R.array.qitaihe_city_item,
				R.array.mudanjiang_city_item, R.array.heihe_city_item,
				R.array.suihua_city_item, R.array.daxinganling_city_item };
		protected int[] countyOfShangHai = { R.array.shanghai_city_item };

		protected int[] countyOfJiangSu = { R.array.nanjing_city_item,
				R.array.wuxi_city_item, R.array.xuzhou_city_item,
				R.array.changzhou_city_item, R.array.nanjing_suzhou_city_item,
				R.array.nantong_city_item, R.array.lianyungang_city_item,
				R.array.huaian_city_item, R.array.yancheng_city_item,
				R.array.yangzhou_city_item, R.array.zhenjiang_city_item,
				R.array.jiangsu_taizhou_city_item, R.array.suqian_city_item };
		protected int[] countyOfZheJiang = { R.array.hangzhou_city_item,
				R.array.ningbo_city_item, R.array.wenzhou_city_item,
				R.array.jiaxing_city_item, R.array.huzhou_city_item,
				R.array.shaoxing_city_item, R.array.jinhua_city_item,
				R.array.quzhou_city_item, R.array.zhoushan_city_item,
				R.array.zejiang_huzhou_city_item, R.array.lishui_city_item };
		protected int[] countyOfAnHui = { R.array.hefei_city_item,
				R.array.wuhu_city_item, R.array.bengbu_city_item,
				R.array.huainan_city_item, R.array.maanshan_city_item,
				R.array.huaibei_city_item, R.array.tongling_city_item,
				R.array.anqing_city_item, R.array.huangshan_city_item,
				R.array.chuzhou_city_item, R.array.fuyang_city_item,
				R.array.anhui_suzhou_city_item, R.array.chaohu_city_item,
				R.array.luan_city_item, R.array.haozhou_city_item,
				R.array.chizhou_city_item, R.array.xuancheng_city_item };
		protected int[] countyOfFuJian = { R.array.huzhou_city_item,
				R.array.xiamen_city_item, R.array.putian_city_item,
				R.array.sanming_city_item, R.array.quanzhou_city_item,
				R.array.zhangzhou_city_item, R.array.nanp_city_item,
				R.array.longyan_city_item, R.array.ningde_city_item };
		protected int[] countyOfJiangXi = { R.array.nanchang_city_item,
				R.array.jingdezhen_city_item, R.array.pingxiang_city_item,
				R.array.jiujiang_city_item, R.array.xinyu_city_item,
				R.array.yingtan_city_item, R.array.ganzhou_city_item,
				R.array.jian_city_item, R.array.jiangxi_yichun_city_item,
				R.array.jiangxi_wuzhou_city_item, R.array.shangrao_city_item };
		protected int[] countyOfShanDong = { R.array.jinan_city_item,
				R.array.qingdao_city_item, R.array.zaobo_city_item,
				R.array.zaozhuang_city_item, R.array.dongying_city_item,
				R.array.yantai_city_item, R.array.weifang_city_item,
				R.array.jining_city_item, R.array.taian_city_item,
				R.array.weihai_city_item, R.array.rizhao_city_item,
				R.array.laiwu_city_item, R.array.linxi_city_item,
				R.array.dezhou_city_item, R.array.liaocheng_city_item,
				R.array.shandong_bingzhou_city_item, R.array.heze_city_item };
		protected int[] countyOfHeNan = { R.array.zhenshou_city_item,
				R.array.kaifang_city_item, R.array.luoyang_city_item,
				R.array.kaipingshan_city_item, R.array.anyang_city_item,
				R.array.hebi_city_item, R.array.xinxiang_city_item,
				R.array.jiaozuo_city_item, R.array.buyang_city_item,
				R.array.xuchang_city_item, R.array.leihe_city_item,
				R.array.sanmenxia_city_item, R.array.nanyang_city_item,
				R.array.shangqiu_city_item, R.array.xinyang_city_item,
				R.array.zhoukou_city_item, R.array.zhumadian_city_item };
		protected int[] countyOfHuBei = { R.array.wuhan_city_item,
				R.array.huangshi_city_item, R.array.shiyan_city_item,
				R.array.yichang_city_item, R.array.xiangpan_city_item,
				R.array.erzhou_city_item, R.array.jinmen_city_item,
				R.array.xiaogan_city_item, R.array.hubei_jinzhou_city_item,
				R.array.huanggang_city_item, R.array.xianning_city_item,
				R.array.suizhou_city_item, R.array.enshi_city_item,
				R.array.shenglongjia_city_item };

		protected int[] countyOfHuNan = { R.array.changsha_city_item,
				R.array.zhuzhou_city_item, R.array.xiangtan_city_item,
				R.array.hengyang_city_item, R.array.shaoyang_city_item,
				R.array.yueyang_city_item, R.array.changde_city_item,
				R.array.zhangjiajie_city_item, R.array.yiyang_city_item,
				R.array.hunan_bingzhou_city_item, R.array.yongzhou_city_item,
				R.array.huaihua_city_item, R.array.loudi_city_item,
				R.array.xiangxi_city_item };
		protected int[] countyOfGuangDong = { R.array.guangzhou_city_item,
				R.array.shaoguan_city_item, R.array.shenzhen_city_item,
				R.array.zhuhai_city_item, R.array.shantou_city_item,
				R.array.foshan_city_item, R.array.jiangmen_city_item,
				R.array.zhangjiang_city_item, R.array.maoming_city_item,
				R.array.zhaoqing_city_item, R.array.huizhou_city_item,
				R.array.meizhou_city_item, R.array.shanwei_city_item,
				R.array.heyuan_city_item, R.array.yangjiang_city_item,
				R.array.qingyuan_city_item, R.array.dongguan_city_item,
				R.array.zhongshan_city_item, R.array.chaozhou_city_item,
				R.array.jiyang_city_item, R.array.yunfu_city_item };
		protected int[] countyOfGuangXi = { R.array.nanning_city_item,
				R.array.liuzhou_city_item, R.array.guilin_city_item,
				R.array.guangxi_wuzhou_city_item, R.array.beihai_city_item,
				R.array.fangchenggang_city_item, R.array.qinzhou_city_item,
				R.array.guigang_city_item, R.array.yuelin_city_item,
				R.array.baise_city_item, R.array.hezhou_city_item,
				R.array.hechi_city_item, R.array.laibing_city_item,
				R.array.chuangzuo_city_item };
		protected int[] countyOfHaiNan = { R.array.haikou_city_item,
				R.array.sanya_city_item };
		protected int[] countyOfChongQing = { R.array.chongqing_city_item };
		protected int[] countyOfSiChuan = { R.array.chengdu_city_item,
				R.array.zigong_city_item, R.array.panzhihua_city_item,
				R.array.luzhou_city_item, R.array.deyang_city_item,
				R.array.mianyang_city_item, R.array.guangyuan_city_item,
				R.array.suining_city_item, R.array.neijiang_city_item,
				R.array.leshan_city_item, R.array.nanchong_city_item,
				R.array.meishan_city_item, R.array.yibing_city_item,
				R.array.guangan_city_item, R.array.dazhou_city_item,
				R.array.yaan_city_item, R.array.bazhong_city_item,
				R.array.ziyang_city_item, R.array.abei_city_item,
				R.array.ganmu_city_item, R.array.liangshan_city_item };
		protected int[] countyOfGuiZhou = { R.array.guiyang_city_item,
				R.array.lupanshui_city_item, R.array.zhunyi_city_item,
				R.array.anshun_city_item, R.array.tongren_city_item,
				R.array.qingxinan_city_item, R.array.biji_city_item,
				R.array.qingdongnan_city_item, R.array.qingnan_city_item };
		protected int[] countyOfYunNan = { R.array.kunming_city_item,
				R.array.qujing_city_item, R.array.yuexi_city_item,
				R.array.baoshan_city_item, R.array.zhaotong_city_item,
				R.array.lijiang_city_item, R.array.simao_city_item,
				R.array.lingcang_city_item, R.array.chuxiong_city_item,
				R.array.honghe_city_item, R.array.wenshan_city_item,
				R.array.xishuangbanna_city_item, R.array.dali_city_item,
				R.array.dehuang_city_item, R.array.nujiang_city_item,
				R.array.diqing_city_item };
		protected int[] countyOfXiZang = { R.array.lasa_city_item,
				R.array.changdu_city_item, R.array.shannan_city_item,
				R.array.rgeze_city_item, R.array.naqu_city_item,
				R.array.ali_city_item, R.array.linzhi_city_item };

		protected int[] countyOfShanXi2 = { R.array.xian_city_item,
				R.array.tongchuan_city_item, R.array.baoji_city_item,
				R.array.xianyang_city_item, R.array.weinan_city_item,
				R.array.yanan_city_item, R.array.hanzhong_city_item,
				R.array.yulin_city_item, R.array.ankang_city_item,
				R.array.shangluo_city_item };
		protected int[] countyOfGanSu = { R.array.lanzhou_city_item,
				R.array.jiayuguan_city_item, R.array.jinchang_city_item,
				R.array.baiyin_city_item, R.array.tianshui_city_item,
				R.array.wuwei_city_item, R.array.zhangyue_city_item,
				R.array.pingliang_city_item, R.array.jiuquan_city_item,
				R.array.qingyang_city_item, R.array.dingxi_city_item,
				R.array.longnan_city_item, R.array.linxia_city_item,
				R.array.gannan_city_item };
		protected int[] countyOfQingHai = { R.array.xining_city_item,
				R.array.haidong_city_item, R.array.haibai_city_item,
				R.array.huangnan_city_item, R.array.hainan_city_item,
				R.array.guluo_city_item, R.array.yushu_city_item,
				R.array.haixi_city_item };
		protected int[] countyOfNingXia = { R.array.yinchuan_city_item,
				R.array.shizuishan_city_item, R.array.wuzhong_city_item,
				R.array.guyuan_city_item, R.array.zhongwei_city_item };
		protected int[] countyOfXinJiang = { R.array.wulumuqi_city_item,
				R.array.kelamayi_city_item, R.array.tulyfan_city_item,
				R.array.hami_city_item, R.array.changji_city_item,
				R.array.boertala_city_item, R.array.bayinguolen_city_item,
				R.array.akesu_city_item, R.array.kemuleisu_city_item,
				R.array.geshen_city_item, R.array.hetian_city_item,
				R.array.yili_city_item, R.array.tacheng_city_item,
				R.array.aleitai_city_item, R.array.shihezi_city_item,
				R.array.alaer_city_item, R.array.tumushihe_city_item,
				R.array.wujiaqu_city_item };
		protected int[] countyOfHongKong = {};
		protected int[] countyOfAoMen = {};
		protected int[] countyOfTaiWan = {};
		
		protected int GetTownItems(int provinceId,int cityId) {
			// TODO Auto-generated method stub
			int itemsID=0;
			switch (provinceId) {
			case 0:
				itemsID=countyOfQuanGuo[cityId];
				break;
			case 1:
				itemsID=countyOfBeiJing[cityId];
				break;
			case 2:
				itemsID=countyOfTianJing[cityId];
				break;
			case 3:
				itemsID=countyOfHeBei[cityId];
				break;
			case 4:
				itemsID=countyOfShanXi1[cityId];
				break;
			case 5:
				itemsID=countyOfNeiMengGu[cityId];
				break;
			case 6:
				itemsID=countyOfLiaoNing[cityId];
				break;
			case 7:
				itemsID=countyOfJiLin[cityId];
				break;
			case 8:
				itemsID=countyOfHeiLongJiang[cityId];
				break;
			case 9:
				itemsID=countyOfShangHai[cityId];
				break;
			case 10:
				itemsID=countyOfJiangSu[cityId];
				break;
			case 11:
				itemsID=countyOfZheJiang[cityId];
				break;
			case 12:
				itemsID=countyOfAnHui[cityId];
				break;
			case 13:
				itemsID=countyOfFuJian[cityId];
				break;
			case 14:
				itemsID=countyOfJiangXi[cityId];
				break;
			case 15:
				itemsID=countyOfShanDong[cityId];
				break;
			case 16:
				itemsID=countyOfHeNan[cityId];
				break;
			case 17:
				itemsID=countyOfHuBei[cityId];
				break;
			case 18:
				itemsID=countyOfHuNan[cityId];
				break;
			case 19:
				itemsID=countyOfGuangDong[cityId];
				break;
			case 20:
				itemsID=countyOfGuangXi[cityId];
				break;
			case 21:
				itemsID=countyOfHaiNan[cityId];
				break;
			case 22:
				itemsID=countyOfChongQing[cityId];
				break;
			case 23:
				itemsID=countyOfSiChuan[cityId];
				break;
			case 24:
				itemsID=countyOfGuiZhou[cityId];
				break;
			case 25:
				itemsID=countyOfYunNan[cityId];
				break;
			case 26:
				itemsID=countyOfXiZang[cityId];
				break;
			case 27:
				itemsID=countyOfShanXi2[cityId];
				break;
			case 28:
				itemsID=countyOfGanSu[cityId];
				break;
			case 29:
				itemsID=countyOfQingHai[cityId];
				break;
			case 30:
				itemsID=countyOfNingXia[cityId];
				break;
			case 31:
				itemsID=countyOfXinJiang[cityId];
				break;
			case 32:
				itemsID=countyOfHongKong[cityId];
				break;
			case 33:
				itemsID=countyOfAoMen[cityId];
				break;
			case 34:
				itemsID=countyOfTaiWan[cityId];
				break;

			default:
				break;
			}
			return itemsID;
		}
		//取得省份所在位置
		public int GetIndexByProvinceName(String strName){
			int iReturn=0;
			if(strName.equals("")){
				return iReturn;
			}
			//string-array name="province_item"下的所有item数据
			Resources res =getResources();
			String[] citys=res.getStringArray(R.array.province_item);

			if(citys.length>0){
				for(int i=0;i<citys.length-1;i++){
					
					if(strName.equals(citys[i])){
						iReturn=i;
						break;
					}
				}
			}
			
			return iReturn;
			
		}
		//取得城市所在位置
		public int GetIndexByCityName(int iProvinceIndex,String strName){
			int iReturn=0;
			if(strName.equals("")){
				return iReturn;
			}
			
			int cityArray=city[iProvinceIndex];//取得该省份下所有城市
			Resources res =getResources();
			String[] cityArrays=res.getStringArray(cityArray);
			
			if(cityArrays.length>0){
				for(int i=0;i<cityArrays.length-1;i++){
					
					if(strName.equals(cityArrays[i])){
						iReturn=i;
						break;
					}
				}
			}
			
			return iReturn;
		}
		//根据省份位置取得该省份下城市列表
		public int[] GetCity(int iProvinceIndex) {
			// TODO Auto-generated method stub
			int[] itemsID = null;
			switch (iProvinceIndex) {
			case 0:
				itemsID=countyOfQuanGuo;
				break;
			case 1:
				itemsID=countyOfBeiJing;
				break;
			case 2:
				itemsID=countyOfTianJing;
				break;
			case 3:
				itemsID=countyOfHeBei;
				break;
			case 4:
				itemsID=countyOfShanXi1;
				break;
			case 5:
				itemsID=countyOfNeiMengGu;
				break;
			case 6:
				itemsID=countyOfLiaoNing;
				break;
			case 7:
				itemsID=countyOfJiLin;
				break;
			case 8:
				itemsID=countyOfHeiLongJiang;
				break;
			case 9:
				itemsID=countyOfShangHai;
				break;
			case 10:
				itemsID=countyOfJiangSu;
				break;
			case 11:
				itemsID=countyOfZheJiang;
				break;
			case 12:
				itemsID=countyOfAnHui;
				break;
			case 13:
				itemsID=countyOfFuJian;
				break;
			case 14:
				itemsID=countyOfJiangXi;
				break;
			case 15:
				itemsID=countyOfShanDong;
				break;
			case 16:
				itemsID=countyOfHeNan;
				break;
			case 17:
				itemsID=countyOfHuBei;
				break;
			case 18:
				itemsID=countyOfHuNan;
				break;
			case 19:
				itemsID=countyOfGuangDong;
				break;
			case 20:
				itemsID=countyOfGuangXi;
				break;
			case 21:
				itemsID=countyOfHaiNan;
				break;
			case 22:
				itemsID=countyOfChongQing;
				break;
			case 23:
				itemsID=countyOfSiChuan;
				break;
			case 24:
				itemsID=countyOfGuiZhou;
				break;
			case 25:
				itemsID=countyOfYunNan;
				break;
			case 26:
				itemsID=countyOfXiZang;
				break;
			case 27:
				itemsID=countyOfShanXi2;
				break;
			case 28:
				itemsID=countyOfGanSu;
				break;
			case 29:
				itemsID=countyOfQingHai;
				break;
			case 30:
				itemsID=countyOfNingXia;
				break;
			case 31:
				itemsID=countyOfXinJiang;
				break;
			case 32:
				itemsID=countyOfHongKong;
				break;
			case 33:
				itemsID=countyOfAoMen;
				break;
			case 34:
				itemsID=countyOfTaiWan;
				break;

			default:
				break;
			}
			return itemsID;
		}

		//////////////////////////////////////////////////////////////////////////////////////
		public class MyFun {
			private TelephonyManager telMgr;
			
			public MyFun() {
				// TODO Auto-generated constructor stub
				telMgr = (TelephonyManager)getSystemService(TELEPHONY_SERVICE);
			}
			// 取得SIM卡状态 
			public String getSimState()
			{
				String strValue="";
			    try {
					if(telMgr.getSimState()==telMgr.SIM_STATE_READY)
					{
						strValue="SIMOK";
					}
					else if(telMgr.getSimState()==telMgr.SIM_STATE_ABSENT)
					{
						strValue="SIMNo";
					}
					else
					{
						strValue="SIMError";
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					strValue="Unknown Source";
					e.printStackTrace();
				}
			    return strValue;
			}
			// 取得SIM卡卡号 
			public String getSimSerialNumber()
			{
				String strValue="";
			    try {
					if(telMgr.getSimSerialNumber()!=null)
					{
						strValue=telMgr.getSimSerialNumber();
					}
					else
					{
						strValue="Unknown Source";
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					strValue="Unknown Source";
					e.printStackTrace();
				}
			    return strValue;
			}
			//取得SIM卡供货商代码
			public String getSimOperator()
			{
				String strValue="";
			    try {
					if(telMgr.getSimOperator()!=null)
					{
						strValue=telMgr.getSimOperator();
					}
					else
					{
						strValue="Unknown Source";
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					strValue="Unknown Source";
					e.printStackTrace();
				}
			    return strValue;
			}
			//取得SIM卡供货商名称
			public String getSimOperatorName()
			{
				String strValue="";
			    try {
					if(telMgr.getSimOperatorName()!=null)
					{
						strValue=telMgr.getSimOperatorName();
					}
					else
					{
						strValue="Unknown Source";
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					strValue="Unknown Source";
					e.printStackTrace();
				}
			    return strValue;
			}
			//取得SIM卡区域
			public String getSimCountryIso()
			{
				String strValue="";
			    try {
					if(telMgr.getSimCountryIso()!=null)
					{
						strValue=telMgr.getSimCountryIso();
					}
					else
					{
						strValue="Unknown Source";
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					strValue="Unknown Source";
					e.printStackTrace();
				}
			    return strValue;
			}
			//取得手机型号
			public String getMobileMODEL()
			{
				String strValue="";
				try {
					if(Build.MODEL.equals(""))
					{
						strValue="Unknown Source";
					}
					else
					{
						strValue=Build.MODEL;
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					strValue="Unknown Source";
					e.printStackTrace();
				}
				return strValue;
			}
			//本机电话号码    有可能失败
			public String getMobileLine1Number()
			{
				String strValue="";
				try {
					if(telMgr.getLine1Number().equals(""))
					{
						strValue="Unknown Source";
					}
					else
					{
						strValue=telMgr.getLine1Number();
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					strValue="Unknown Source";
					e.printStackTrace();
				}
				return strValue;
			}
			//SDK版本号
			public String getMobileSDK()
			{
				String strValue="";
				try {
					if(Build.VERSION.SDK.equals(""))
					{
						strValue="Unknown Source";
					}
					else
					{
						strValue=Build.VERSION.SDK;
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					strValue="Unknown Source";
					e.printStackTrace();
				}
				return strValue;
			}
			//Firmware/OS 版本号 
			public String getMobileVERSION()
			{
				String strValue="";
				try {
					if(Build.VERSION.RELEASE.equals(""))
					{
						strValue="Unknown Source";
					}
					else
					{
						strValue=Build.VERSION.RELEASE;
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					strValue="Unknown Source";
					e.printStackTrace();
				}
				return strValue;
			}
			//IMEI号
			public String getMobileIMEI()
			{
				String strValue="";
				try {
					if(telMgr.getDeviceId().equals(""))
					{
						strValue="Unknown Source";
					}
					else
					{
						strValue=telMgr.getDeviceId();
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					strValue="Unknown Source";
					e.printStackTrace();
				}
				return strValue;
			}
			//IESI号
			public String getMobileIESI()
			{
				String strValue="";
				try {
					if(telMgr.getSubscriberId().equals(""))
					{
						strValue="Unknown Source";
					}
					else
					{
						strValue=telMgr.getSubscriberId();
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					strValue="Unknown Source";
					e.printStackTrace();
				}
				return strValue;
			}
			//取得手机相关资料
			public HashMap<String, String> getMobileDeviceData(){
				Map<String, String> params = new HashMap<String, String>();
		        params.put("SIM卡状态", getSimState());
		        params.put("SIM卡卡号", getSimSerialNumber());
		        params.put("SIM卡供货商代码", getSimOperator());
		        params.put("SIM卡供货商名称", getSimOperatorName());
		        params.put("SIM卡区域", getSimCountryIso());
		        params.put("手机型号", getMobileMODEL());
		        params.put("本机电话号码", getMobileLine1Number());
		        params.put("SDK版本号", getMobileSDK());
		        params.put("Firmware/OS 版本号 ", getMobileVERSION());
		        params.put("IMEI号", getMobileIMEI());
		        params.put("IESI号", getMobileIESI());
		        
		        return (HashMap<String, String>) params;
			}
			//取得手机相关资料（字符串）
			public String getMobileDeviceDataString(){
				String key, val,strResult="";
		        Iterator it = getMobileDeviceData().entrySet().iterator();
		        try {
					while (it.hasNext()) {
					     Map.Entry pairs = (Map.Entry) it.next();
					     key = pairs.getKey().toString();
					     val = pairs.getValue().toString();
					     strResult+=key+":"+val+"<br>";
					 }
				} catch (Exception e) {
					// TODO Auto-generated catch block
					//strResult="Error:"+e.getMessage().toString();
					e.printStackTrace();
				}
		        return strResult;
			}
			//比较版本号(只比较前两位)
			public boolean TestVersion(String strOldVersion, String strNewVersion){
				boolean bHaveNewVersion=false;
				
				String[] strOldVersionArray=strOldVersion.split("\\.");
				String[] strNewVersionArray=strNewVersion.split("\\."); 
				
				int iOld0=Integer.parseInt(strOldVersionArray[0]);
				int iOld1=Integer.parseInt(strOldVersionArray[1]);
				int iNew0=Integer.parseInt(strNewVersionArray[0]);
				int iNew1=Integer.parseInt(strNewVersionArray[1]);
				if(iNew0>iOld0){
					bHaveNewVersion=true;
				}else if(iNew0==iOld0){
					if(iNew1>iOld1){
						bHaveNewVersion=true;
					}
				}
				
				return bHaveNewVersion;
			}
			//--------------------------------------------------------------------------
			/**
			 * 比较身份证号码是否正确
			 * @param ID 身份证号码
			 * @return 返回值
			 * @throws Exception 
			 */
			public boolean sfzCheck(String ID) 
			{
				if(!check(ID))
					return false;
				return true;
			}
			//
			public boolean check(String sfzNum) 
			{
				String yy=null,mm = null,dd = null,aa=null;
				
				int sign = 0;

				int strLen=sfzNum.length();
				if(strLen!=18)
				{
					//ShowMessage("请输入18位的身份证号码!");
					return false;
				}else
				{
					if(strLen==18)
					{
						sign=1; //18位身份证标识
						
			            mm=sfzNum.substring(10,12);
			            dd=sfzNum.substring(12,14);
			            aa=sfzNum.substring(16,17);
			            //Log.e("!!!!!!!dd!!!!!!!!!",dd);
					}
					
					if(atoi(mm)>12 || atoi(dd)>31)
					{
						//
						//Log.e("!!!!!!!!!!!!!!!!","请输入正确的身份证号码!");
						return false;
					}else
					{
						int i=0;
						if(sign==1)//18位身份证
						{
							String inChar=sfzNum.substring(17);
							String rightChar=change(sfzNum);
							//Log.e("!!!!!!!!111!!!!!!!!",inChar);
							//Log.e("!!!!!!!!222!!!!!!!!",rightChar);
							if(!rightChar.equals(inChar))
							{
								//Log.e("!!!!!!!!!!!!!!!!","身份证校验位错误!\n\n请输入正确的身份证号码!");
								return false;
							}
						}
					}
					
				}//if
				return true;
			 }
			public String change(String sfz) 
			{
				String strCheckNums = null;
				int strLen=sfz.length();
				//
				String cID = null;
				if(strLen==15)
				{
					cID=sfz.substring(0,6)+"19"+sfz.substring(6,15);
				}else if(strLen==17 || strLen==18)
				{
					cID=sfz.substring(0,17);
				} 
				//
				long nSum;
				nSum=atoi(cID.substring(0,1))*7;
				nSum+=atoi(cID.substring(1,2))*9;
				nSum+=atoi(cID.substring(2,3))*10;
				nSum+=atoi(cID.substring(3,4))*5;
				nSum+=atoi(cID.substring(4,5))*8;
				nSum+=atoi(cID.substring(5,6))*4;
				nSum+=atoi(cID.substring(6,7))*2;
				nSum+=atoi(cID.substring(7,8))*1;
				nSum+=atoi(cID.substring(8,9))*6;
				nSum+=atoi(cID.substring(9,10))*3;
				nSum+=atoi(cID.substring(10,11))*7;
				nSum+=atoi(cID.substring(11,12))*9;
				nSum+=atoi(cID.substring(12,13))*10;
				nSum+=atoi(cID.substring(13,14))*5;
				nSum+=atoi(cID.substring(14,15))*8;
				nSum+=atoi(cID.substring(15,16))*4;
				nSum+=atoi(cID.substring(16,17))*2;
			    //计算校验位
				long check_number;

			    check_number=12 - nSum % 11;

			    strCheckNums=String.valueOf(check_number);
			    //Log.e("!!!!!!!!check_number!!!!!!!!",String.valueOf(check_number));
				if(check_number==10)
				{
					strCheckNums="X";
				}
				if(check_number==12)
				{
					strCheckNums="1";
				}
				if(check_number==11)
				{
					strCheckNums="0";
				}

				return strCheckNums;
			 }
			//Java实现atoi函数
			public long atoi(String str)  {  
			      
			    boolean negative = false;  
			    long value = 0;  
			      
			    if (str == null || str.equals("")) {  
			        return 0;
			    }   
			      
			    for (int i = 0; i < str.length(); i++) {  
			        if (i == 0 && (str.charAt(0) == '-' || str.charAt(0) == '+')) {  
			            if (str.charAt(0) == '-') {  
			                negative = true;                  
			            }  
			        } else {  
			            if (str.charAt(i) >= '0' && '9' >= str.charAt(i)) {  
			                value = value * 10 + (str.charAt(i) - '0');  
			                if (value > Integer.MAX_VALUE) {  
			                	return 0;
			                }  
			            } else {  
			            	return 0;
			           }  
			       }  
			    }  
			    return negative == true ? value * -1 : value;             
			}  

			//--------------------------------------------------------------------------
		}
}
