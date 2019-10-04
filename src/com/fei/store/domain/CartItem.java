package com.fei.store.domain;

public class CartItem {
	private Product product;
	private int num;
	private double subTotal;
	
	
	public double getSubTotal() {
		return product.getPrice()*num;
	}
	
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}
	
	
	
}
