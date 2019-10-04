package com.fei.store.dao;

import com.fei.store.domain.Order;
import com.fei.store.domain.OrderItem;
import com.fei.store.domain.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface OrderMapper {

    public int saveOrder(Connection conn, Order order) throws SQLException;

    public int saveOrderItem(Connection conn, OrderItem item) throws Exception;

    List<Order> findMyOrders(User user)throws Exception;

    void deleteOrder(Connection conn , String oid) throws Exception;

    void deleteOrderItem(Connection conn , String oid) throws Exception;

	public List<Order> findMyOrders() throws Exception;
}


