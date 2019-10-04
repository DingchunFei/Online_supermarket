package com.fei.store.web.servlet;

import com.fei.store.domain.Category;
import com.fei.store.domain.Product;
import com.fei.store.domain.User;
import com.fei.store.service.CategoryService;
import com.fei.store.service.ProductService;
import com.fei.store.service.serviceImpl.CategoryServiceImpl;
import com.fei.store.service.serviceImpl.ProductServiceImpl;
import com.fei.store.utils.UUIDUtils;
import com.fei.store.web.base.BaseServlet;
import net.sf.json.JSONArray;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryServlet extends BaseServlet {


	/**
	 * This method is called by AJAX
	 */
    public String findAllCats(HttpServletRequest request, HttpServletResponse response) throws Exception {

        CategoryService CategoryService=new CategoryServiceImpl();
        List<Category> list = CategoryService.getAllCats();
        //Translate into JSON format
        String jsonStr= JSONArray.fromObject(list).toString();
        System.out.println(jsonStr);

        response.setContentType("application/json;charset=utf-8");
        response.getWriter().print(jsonStr);
        return null;
    }
    
    public String editCategoryUI(HttpServletRequest request, HttpServletResponse response) throws Exception {

    	Map<Category,Integer> map;
    	CategoryService CategoryService=new CategoryServiceImpl();
        
        /**
         * find the dependencies between Products & Categories. 
         * If a Category is referenced by a Product, 
         * then the category can not be deleted
         */
    	map = CategoryService.getAllCatsDependencies();
    	request.setAttribute("catsMap", map);

		return "/jsp/category_edit.jsp";
    }
    
    public String addCategory(HttpServletRequest request, HttpServletResponse response) throws Exception {

    	String cname= request.getParameter("cname");
    	Category category = new Category(UUIDUtils.getId(),cname);
    	
    	CategoryService CategoryService=new CategoryServiceImpl();
    	CategoryService.addCategory(category);

		response.sendRedirect(request.getContextPath()+"/IndexServlet");
		return null;
    }
    
    public String delCategory(HttpServletRequest request, HttpServletResponse response) throws Exception {

    	String cid= request.getParameter("cid");
    	
    	CategoryService CategoryService=new CategoryServiceImpl();
    	CategoryService.delCategory(cid);

		response.sendRedirect(request.getContextPath()+"/IndexServlet");
		return null;
    }
    
    public String editCategory(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	
    	String cid= request.getParameter("cid");
    	String cname= request.getParameter("cname");
    	Category category = new Category(cid,cname);

    	CategoryService CategoryService=new CategoryServiceImpl();
    	CategoryService.editCategory(category);

		response.sendRedirect(request.getContextPath()+"/IndexServlet");
		return null;
    }
    
    
}



