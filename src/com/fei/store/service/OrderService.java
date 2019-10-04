package com.fei.store.service;


import com.fei.store.domain.Order;
import com.fei.store.domain.User;

import java.util.List;

public interface OrderService {

	void saveOrder(Order order)throws Exception;

	List<Order> findMyOrders(User user)throws Exception;

	void deleteOrderOrderItem(String oid) throws Exception;

	void deleteOrder(Order order) throws Exception;


//	PageModel findMyOrdersWithPage(User user, int curNum)throws Exception;
//
//	Order findOrderByOid(String oid)throws Exception;
//
//	void updateOrder(Order order)throws Exception;

}
