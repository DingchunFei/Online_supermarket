package com.fei.store.dao.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.fei.store.dao.OrderMapper;
import com.fei.store.dao.ProductMapper;
import com.fei.store.domain.Order;
import com.fei.store.domain.OrderItem;
import com.fei.store.domain.Product;
import com.fei.store.domain.User;
import com.fei.store.utils.DBConnection;


public class OrderMapperImp implements OrderMapper {

	@Override
	public int saveOrder(Connection conn, Order order) throws SQLException {

		try {
			PreparedStatement stmt = DBConnection.prepare(conn,"INSERT into t_orders (oid,total,uid,state,address) values(?,?,?,?,?)");
			stmt.setString(1, order.getOid());
			stmt.setDouble(2, order.getTotal());
			stmt.setString(3, order.getUser().getUid());
			stmt.setInt(4, order.getState());
			stmt.setString(5, order.getAddress());

			int resultSet=stmt.executeUpdate();
			return resultSet;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;	//表示插入失败
	}

	@Override
	public int saveOrderItem(Connection conn, OrderItem item) throws Exception {
		try {
			PreparedStatement stmt = DBConnection.prepare(conn,"INSERT into t_orderitem (itemid,quantity,total,pid,oid) values(?,?,?,?,?)");
			stmt.setString(1, item.getItemid());
			stmt.setInt(2, item.getQuantity());
			stmt.setDouble(3, item.getTotal());
			stmt.setString(4, item.getProduct().getPid());
			stmt.setString(5, item.getOrder().getOid());

			int resultSet=stmt.executeUpdate();
			return resultSet;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;	//表示插入失败
	}



	public List<OrderItem> findOrderByOid(String oid) throws Exception {
		List<OrderItem> orderItems = new LinkedList<>();
		try {
			PreparedStatement stmt = DBConnection.prepare("SELECT * from t_orderitem where oid = ?");

			stmt.setString(1, oid);

			ResultSet rs = stmt.executeQuery();
            ProductMapper productDao = new ProductMapperImpl();

			while(rs.next()){
				OrderItem orderItem = new OrderItem();
				orderItem.setItemid(rs.getString(1));
				orderItem.setQuantity(rs.getInt(2));
				orderItem.setTotal(rs.getDouble(3));
				Product product = productDao.findProductByPid(rs.getString(4));
				orderItem.setProduct(product);
                orderItems.add(orderItem);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderItems;
	}


	@Override
	public List<Order> findMyOrders(User user) throws Exception {
		List<Order> orders = new LinkedList<>();
		try {
			PreparedStatement stmt = DBConnection.prepare("SELECT * from t_orders where uid = ?");

			stmt.setString(1, user.getUid());

			ResultSet rs = stmt.executeQuery();

			while(rs.next()){
				Order order = new Order();
				order.setOid(rs.getString(1));
				order.setTotal(rs.getDouble(2));
				order.setState(rs.getInt(4));
				order.setAddress(rs.getString(5));
				List<OrderItem> orderItems = this.findOrderByOid(rs.getString(1));
				order.setList(orderItems);
				orders.add(order);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orders;
	}

	@Override
	public void deleteOrderItem(Connection conn , String oid)  throws Exception {
		try {
			PreparedStatement stmt = DBConnection.prepare(conn,"DELETE from t_orderitem where oid = ?");

			stmt.setString(1, oid);

			int rs = stmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void deleteOrder(Connection conn , String oid) throws Exception {

		try {
			PreparedStatement stmt = DBConnection.prepare(conn,"DELETE from t_orders where oid = ?");

			stmt.setString(1, oid);

			int rs = stmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public List<Order> findMyOrders() throws Exception {
		List<Order> orders = new LinkedList<>();
		try {
			PreparedStatement stmt = DBConnection.prepare("SELECT * from t_orders");
			
			ResultSet rs = stmt.executeQuery();

			while(rs.next()){
				Order order = new Order();
				order.setOid(rs.getString(1));
				order.setTotal(rs.getDouble(2));
				order.setState(rs.getInt(4));
				order.setAddress(rs.getString(5));
				List<OrderItem> orderItems = this.findOrderByOid(rs.getString(1));
				order.setList(orderItems);
				orders.add(order);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orders;
	}




	/*@Override
	public void updateOrder(Order order) throws Exception {
		String sql="UPDATE orders SET ordertime=? ,total=? ,state= ?, address=?,NAME=?, telephone =? WHERE oid=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Object[] params={order.getOrdertime(),order.getTotal(),order.getState(),order.getAddress(),order.getName(),order.getTelephone(),order.getOid()};
		qr.update(sql,params);
		
	}

	@Override
	public List findMyOrdersWithPage(User user, int startIndex, int pageSize) throws Exception {
		String sql="select * from orders where uid=? limit ? , ?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		List<Order> list=qr.query(sql, new BeanListHandler<Order>(Order.class),user.getUid(),startIndex,pageSize);
		
		//遍历所有订单
		for (Order order : list) {
			//获取到每笔订单oid   查询每笔订单下的订单项以及订单项对应的商品信息
			String oid=order.getOid();
			sql="select * from orderItem o ,product p where o.pid=p.pid and oid=?";
			List<Map<String, Object>> list02 = qr.query(sql, new MapListHandler(),oid);
			//遍历list
			for (Map<String, Object> map : list02) {
				OrderItem orderItem=new OrderItem();
				Product product=new Product();
				// 由于BeanUtils将字符串"1992-3-3"向user对象的setBithday();方法传递参数有问题,手动向BeanUtils注册一个时间类型转换器
				// 1_创建时间类型的转换器
				DateConverter dt = new DateConverter();
				// 2_设置转换的格式
				dt.setPattern("yyyy-MM-dd");
				// 3_注册转换器
				ConvertUtils.register(dt, java.util.Date.class);
				
				//将map中属于orderItem的数据自动填充到orderItem对象上
				BeanUtils.populate(orderItem, map);
				//将map中属于product的数据自动填充到product对象上
				BeanUtils.populate(product, map);
				
				//让每个订单项和商品发生关联关系
				orderItem.setProduct(product);
				//将每个订单项存入订单下的集合中
				order.getList().add(orderItem);
				
			}
		}
		return list;
	}

	@Override
	public Order findOrderByOid(String oid) throws Exception {
		String sql="select * from orders where oid= ?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Order order=qr.query(sql, new BeanHandler<Order>(Order.class),oid);
		
		//根据订单id查询订单下所有的订单项以及订单项对应的商品信息
		sql="select * from orderitem o, product p where o.pid=p.pid and oid=?";
		List<Map<String, Object>> list02 = qr.query(sql, new MapListHandler(),oid);
		//遍历list
		for (Map<String, Object> map : list02) {
			OrderItem orderItem=new OrderItem();
			Product product=new Product();
			// 由于BeanUtils将字符串"1992-3-3"向user对象的setBithday();方法传递参数有问题,手动向BeanUtils注册一个时间类型转换器
			// 1_创建时间类型的转换器
			DateConverter dt = new DateConverter();
			// 2_设置转换的格式
			dt.setPattern("yyyy-MM-dd");
			// 3_注册转换器
			ConvertUtils.register(dt, java.util.Date.class);
			
			//将map中属于orderItem的数据自动填充到orderItem对象上
			BeanUtils.populate(orderItem, map);
			//将map中属于product的数据自动填充到product对象上
			BeanUtils.populate(product, map);
			
			//让每个订单项和商品发生关联关系
			orderItem.setProduct(product);
			//将每个订单项存入订单下的集合中
			order.getList().add(orderItem);
		}
		return order;
	}

	@Override
	public int getTotalRecords(User user) throws Exception {
		String sql="select count(*) from orders where uid=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Long num=(Long)qr.query(sql, new ScalarHandler(),user.getUid());
		return num.intValue();
	}
*/


}
