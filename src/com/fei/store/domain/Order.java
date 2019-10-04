package com.fei.store.domain;

import com.fei.store.utils.UnitOfWork;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {

	private String oid; //订单编号
	private double total; //总计
	private int state; //状态
	private String address; //状态

	// private String uid;
	// 1_程序对象和对象发生关系,而不是对象和对象的属性发生关系
	// 2_设计Order目的:让order携带订单上的数据向service,dao传递,user对象是可以携带更多的数据
	private User user;

	public Order() {
		//UnitOfWork.getCurrent().registerNew(this);
	}

	@Override
	public String toString() {
		return "Order{" +
				"oid='" + oid + '\'' +
				", total=" + total +
				", state=" + state +
				", address='" + address + '\'' +
				", user=" + user +
				", list=" + list +
				'}';
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
		//UnitOfWork.getCurrent().registerDirty(this);
	}

	// 程序中体现订单对象和订单项之间关系,我们再项目中的部分功能中有类似的需求:查询订单的同时还需要获取订单下所有的订单项
	private List<OrderItem> list = new ArrayList<OrderItem>();

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
		//UnitOfWork.getCurrent().registerDirty(this);

	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
		//UnitOfWork.getCurrent().registerDirty(this);

	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
		//UnitOfWork.getCurrent().registerDirty(this);

	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
		//UnitOfWork.getCurrent().registerDirty(this);

	}

	public List<OrderItem> getList() {
		return list;
	}

	public void setList(List<OrderItem> list) {
		this.list = list;
		//UnitOfWork.getCurrent().registerDirty(this);

	}

}
