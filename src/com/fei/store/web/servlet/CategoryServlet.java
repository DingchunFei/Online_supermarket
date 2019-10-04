package com.fei.store.web.servlet;

import com.fei.store.domain.Category;
import com.fei.store.domain.Product;
import com.fei.store.service.CategoryService;
import com.fei.store.service.ProductService;
import com.fei.store.service.serviceImpl.CategoryServiceImpl;
import com.fei.store.service.serviceImpl.ProductServiceImpl;
import com.fei.store.web.base.BaseServlet;
import net.sf.json.JSONArray;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class CategoryServlet extends BaseServlet {



    public String findAllCats(HttpServletRequest request, HttpServletResponse response) throws Exception {

        CategoryService CategoryService=new CategoryServiceImpl();
        List<Category> list = CategoryService.getAllCats();
        //将全部分类转换为JSON格式的数据
        String jsonStr= JSONArray.fromObject(list).toString();
        System.out.println(jsonStr);

        //将全部分类信息响应到客户端
        //告诉浏览器本次响应的数据是JSON格式的字符串
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().print(jsonStr);
        return null;
    }
}