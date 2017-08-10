package com.tb.order.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tb.order.bean.HTResult;
import com.tb.order.dao.IOrder;
import com.tb.order.pojo.Order;
import com.tb.order.pojo.PageResult;
import com.tb.order.pojo.ResultMsg;
import com.tb.order.sys.orderConstant;
import com.tb.order.util.ValidateUtil;
import com.tb.order.util.order.OrderUtil;

@Service
public class OrderService {

	private static final ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	private IOrder orderDao;

	// @Autowired
	// private RabbitTemplate rabbitTemplate;

	public HTResult createOrder(String json) throws Exception {
		Order order = null;
		try {
			order = objectMapper.readValue(json, Order.class);
			// 校验Order对象
			ValidateUtil.validate(order);
		} catch (Exception e) {
			return HTResult.build(400, "请求参数有误!");
		}

		String orderId = OrderUtil.getOrderId(order.getUserId());
		order.setOrderId(orderId);

		// 设置订单的初始状态为未付款
		order.setStatus(orderConstant.orderStatus.ORDER_STATUS_UNPAID);

		// 设置订单的创建时间
		order.setCreateTime(new Date());
		order.setUpdateTime(order.getCreateTime());

		// 设置买家评价状态，初始为未评价
		order.setBuyerRate(orderConstant.orderBuyerRate.ORDER_BuyerRate_STATUS_NO);

		// 持久化order对象
		int i = orderDao.createOrder(order);

		// 发送消息到RabbitMQ
		// Map<String, Object> msg = new HashMap<String, Object>(3);
		// msg.put("userId", order.getUserId());
		// msg.put("orderId", order.getOrderId());
		// List<Long> itemIds = new
		// ArrayList<Long>(order.getOrderItems().size());
		// for (OrderItem orderItem : order.getOrderItems()) {
		// itemIds.add(orderItem.getItemId());
		// }
		// msg.put("itemIds", itemIds);
		// this.rabbitTemplate.convertAndSend(objectMapper.writeValueAsString(msg));

		if (i > 0) {
			return HTResult.ok(orderId);
		}
		return HTResult.build(400, "保存订单失败!");
	}

	public Order queryOrderById(String orderId) {
		Order order = orderDao.queryOrderById(orderId);
		return order;
	}

	public PageResult<Order> queryOrderByUserNameAndPage(String buyerNick, int page, int count) {
		return orderDao.queryOrderByUserNameAndPage(buyerNick, page, count);
	}

	public ResultMsg changeOrderStatus(String json) {
		Order order = null;
		try {
			order = objectMapper.readValue(json, Order.class);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResultMsg("400", "请求参数有误!");
		}
		return this.orderDao.changeOrderStatus(order);
	}

}
