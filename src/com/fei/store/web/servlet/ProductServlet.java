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
import java.util.List;

public class ProductServlet extends BaseServlet {

    public String findProductByPid(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String pid = request.getParameter("pid");
        ProductService productService = new ProductServiceImpl();
        Product product = productService.findProductByPid(pid);
        request.setAttribute("product_detail",product);
        return "/jsp/product_info.jsp";
    }

    public String findProductsByCid(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String cid = request.getParameter("cid");
        ProductService productService = new ProductServiceImpl();
        List<Product> products = productService.findProductsByCid(cid);
        request.setAttribute("products",products);
        return "/jsp/product_list.jsp";
    }
}
