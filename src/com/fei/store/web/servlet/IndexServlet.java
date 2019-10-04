package com.fei.store.web.servlet;

import com.fei.store.domain.Category;
import com.fei.store.domain.Product;
import com.fei.store.service.CategoryService;
import com.fei.store.service.ProductService;
import com.fei.store.service.serviceImpl.CategoryServiceImpl;
import com.fei.store.service.serviceImpl.ProductServiceImpl;
import com.fei.store.web.base.BaseServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;

public class IndexServlet extends BaseServlet {


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //2.查詢所有商品
        ProductService productService = new ProductServiceImpl();
        List<Product> productList;

        if(null!=request.getParameter("pname")){
            productList = productService.getProductsByName(request.getParameter("pname"));
        }else{
            productList = productService.getAllProducts();
        }


        String order =request.getParameter("order");        //排序规则

        if(order!=null){
            Collections.sort(productList);
            if(order.equals("desc")){
                Collections.reverse(productList);
            }
        }

        //将返回的集合放入request
        request.setAttribute("allProducts",productList);

        //转发到真实首页
        return "/jsp/index.jsp";
    }
}
