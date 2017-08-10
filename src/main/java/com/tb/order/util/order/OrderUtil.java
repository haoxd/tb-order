package com.tb.order.util.order;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author acer11
 *  作者：订单工具类
* 创建时间：2017年6月17日 上午10:08:32  
* 项目名称：tb-order  
* 文件名称：OrderUtil.java  
* 类说明：
 */
public class OrderUtil {
	
	
	/**
	 * 更具当前年份月份+当前系统时间搓+用户id生成
	 * @param userId：用户id
	 * @return
	 */
	public static synchronized  String getOrderId(Long userId){
			Date date=new Date();
			String time=new SimpleDateFormat("yyyyM").format(date);
			return  time+System.currentTimeMillis()+userId ;
	}
	
	public static void main(String[] args) {
		System.out.println(System.currentTimeMillis());
	}
	

}
