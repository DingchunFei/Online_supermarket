package com.fei.store.web.servlet;

import com.fei.store.domain.Category;
import com.fei.store.domain.Description;
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
    
    
    public String insertProductUI(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	//when creating product, we require the admin to choose one category for the product
    	CategoryService CategoryService=new CategoryServiceImpl();
        List<Category> categories = CategoryService.getAllCats();
        
        request.setAttribute("categories",categories);
        return "/jsp/product_create.jsp";
    }
    
    public String insertProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String pname = request.getParameter("pname");
        Double price = Double.parseDouble(request.getParameter("price"));
        String cid = request.getParameter("cid");
        String pimage = request.getParameter("pimage");
        String pdesc = request.getParameter("pdesc");
        Description description = new Description(pdesc);
        
    	Product product = new Product(pname, price, pimage, cid, description);

        ProductService productService = new ProductServiceImpl();
        int result = productService.insertProduct(product);
        
		response.sendRedirect(request.getContextPath()+"/IndexServlet");
		return null;
    }
    
    public String editProductByPid(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	//when editing product, we require the admin to choose one category for the product
    	CategoryService CategoryService=new CategoryServiceImpl();
        List<Category> categories = CategoryService.getAllCats();
        request.setAttribute("categories",categories);

    	String pid = request.getParameter("pid");
        ProductService productService = new ProductServiceImpl();
        Product product = productService.findProductByPid(pid);
        request.setAttribute("product_detail",product);
        return "/jsp/product_edit.jsp";
    }
    
    
    public String editProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	String pid = request.getParameter("pid");
    	String pname = request.getParameter("pname");
        Double price = Double.parseDouble(request.getParameter("price"));
        String cid = request.getParameter("cid");
        String pimage = request.getParameter("pimage");
        String pdesc = request.getParameter("pdesc");
        Description description = new Description(pdesc);
        
    	Product product = new Product(pid, pname, price, pimage, cid, description);

        ProductService productService = new ProductServiceImpl();
        int result = productService.editProduct(product);
        
		response.sendRedirect(request.getContextPath()+"/IndexServlet");
		return null;
    }
    
    public String delProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	String pid = request.getParameter("pid");
    
        ProductService productService = new ProductServiceImpl();
        int result = productService.delProduct(pid);
        
		response.sendRedirect(request.getContextPath()+"/IndexServlet");
		return null;
    }
    
    
}
