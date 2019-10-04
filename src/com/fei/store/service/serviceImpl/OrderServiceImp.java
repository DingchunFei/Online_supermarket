package com.fei.store.service.serviceImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


import com.fei.store.dao.OrderMapper;
import com.fei.store.dao.daoImpl.OrderMapperImp;
import com.fei.store.domain.Order;
import com.fei.store.domain.OrderItem;
import com.fei.store.domain.User;
import com.fei.store.service.OrderService;
import com.fei.store.utils.DBConnection;

public class OrderServiceImp implements OrderService {
	
	OrderMapper orderDao=new OrderMapperImp();

	@Override
	public void saveOrder(Order order) throws SQLException {
		
		Connection conn=null;
		try {
			//获取连接
			conn= DBConnection.getDBConnection();
			//保存订单
			
			orderDao.saveOrder(conn,order);
			//保存订单项
			for (OrderItem item : order.getList()) {
				orderDao.saveOrderItem(conn,item);	
			}
			//提交
			conn.commit();
		} catch (Exception e) {
			//回滚
			conn.rollback();
		}
	}

	@Override
	public List<Order> findMyOrders(User user) throws Exception {
		List<Order> list=orderDao.findMyOrders(user);
		return list;
	}

	@Override
	public void deleteOrderOrderItem(String oid) throws Exception {

		Connection conn=null;
		try {
			conn= DBConnection.getDBConnection();

			orderDao.deleteOrderItem(conn,oid);
			orderDao.deleteOrder(conn,oid);
			//提交
			conn.commit();
		} catch (Exception e) {
			//回滚
			conn.rollback();
		}
	}

	@Override
	public void deleteOrder(Order order) throws Exception {

		Connection conn=null;
		try {
			conn= DBConnection.getDBConnection();

			orderDao.deleteOrderItem(conn,order.getOid());
			orderDao.deleteOrder(conn,order.getOid());
			//提交
			conn.commit();
		} catch (Exception e) {
			//回滚
			conn.rollback();
		}
	}

	@Override
	public List<Order> findAllOrders() throws Exception {
		List<Order> list=orderDao.findMyOrders();
		return list;
	}

}