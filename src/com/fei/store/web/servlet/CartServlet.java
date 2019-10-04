package com.fei.store.web.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fei.store.domain.Cart;
import com.fei.store.domain.CartItem;
import com.fei.store.domain.Product;
import com.fei.store.service.ProductService;
import com.fei.store.service.serviceImpl.ProductServiceImpl;
import com.fei.store.web.base.BaseServlet;

public class CartServlet extends BaseServlet {
	
	//添加购物项到购物车
	public String addCartItemToCart(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//从session获取购物车
		Cart cart=(Cart)request.getSession().getAttribute("cart");
		if(null==cart){
		  //如果获取不到,创建购物车对象,放在session中
			cart=new Cart();
			request.getSession().setAttribute("cart", cart);
		}
   		//如果获取到,使用即可
		//获取到商品id,数量
		String pid=request.getParameter("pid");
		int num=Integer.parseInt(request.getParameter("quantity"));
		//通过商品id查询都商品对象
		ProductService ProductService=new ProductServiceImpl();
		Product product=ProductService.findProductByPid(pid);
		//获取到待购买的购物项
		CartItem cartItem=new CartItem();
		cartItem.setNum(num);
		cartItem.setProduct(product);

		//调用购物车上的方法
		cart.addCartItemToCar(cartItem);
		
		//重定向到/jsp/cart.jsp
		response.sendRedirect(request.getContextPath()+"/jsp/cart.jsp");
		//return "/jsp/cart.jsp";
		return  null;
	}
	
	//removeCartItem
	public String removeCartItem(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获取待删除商品pid
		String pid=request.getParameter("pid");
		//获取到购物车
		Cart cart=(Cart)request.getSession().getAttribute("cart");
		//调用购物车删除购物项方法
		cart.removeCartItem(pid);
		//重定向到/jsp/cart.jsp
		response.sendRedirect(request.getContextPath()+"/jsp/cart.jsp");
		return null;
	}
	//clearCart
	public String clearCart(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获取购物车
		Cart cart=(Cart)request.getSession().getAttribute("cart");
		//调用购物车上的清空购物车方法
		cart.clearCart();
		//重新定向到/jsp/cart.jsp
		response.sendRedirect(request.getContextPath()+"/jsp/cart.jsp");
		return null;
	}
}




