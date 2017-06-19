package com.tb.order.sys;

public class orderConstant {
	
	
	/**
	 * @author acer11
	* 类说明：订单状态
	 */
	public interface orderStatus{
		
		/**
		 * 未付款
		 */
		Integer ORDER_STATUS_UNPAID=1;
		/**
		 * 已付款
		 */
		Integer ORDER_STATUS_PAID=2;
		/**
		 * 未发货
		 */
		Integer ORDER_STATUS_NOTSHIPPED=3;
		/**
		 * 已发货
		 */
		Integer ORDER_STATUS_SHIPPED=4;
		/**
		 * 交易成功
		 */
		Integer ORDER_STATUS_TRANSACTION_SUCC=5;
		/**
		 * 交易关闭
		 */
		Integer ORDER_STATUS_TRANSACTION_CLOSE=6;
		/**
		 * 交易失败
		 */
		Integer ORDER_STATUS_TRANSACTION_FAIL=7;
	}
	
	/**
	 * @author acer11
	* 类说明：订单是否评价
	 */
	public interface orderBuyerRate{
		/**
		 * 已评价
		 */
		Integer ORDER_BuyerRate_STATUS_NO=0;
		/**
		 * 未评价
		 */
		Integer ORDER_BuyerRate_STATUS_OK=1;
	}
}
