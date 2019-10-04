package com.fei.store.service.serviceImpl;

import com.fei.store.dao.CategoryMapper;
import com.fei.store.dao.daoImpl.CategoryMapperImpl;
import com.fei.store.domain.Category;
import com.fei.store.service.CategoryService;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CategoryServiceImpl implements CategoryService {
    @Override
    public List<Category> getAllCats() throws Exception {
        CategoryMapper categoryDao = new CategoryMapperImpl();
        return categoryDao.getAllCats();
    }

	@Override
	public Map<Category,Integer> getAllCatsDependencies() throws Exception {
		
		CategoryMapper categoryDao = new CategoryMapperImpl();
		List<Category> cateList = categoryDao.getAllCats();
		Map<Category,Integer> map = new HashMap<Category,Integer>();
		
		for(Category cate : cateList){
			map.put(cate, categoryDao.getCatsDependencies(cate));
		}
		return map;
	}

	@Override
	public void addCategory(Category category) {
		CategoryMapper categoryDao = new CategoryMapperImpl();
        categoryDao.addCategory(category);
	}

	@Override
	public void delCategory(String cid) {
		CategoryMapper categoryDao = new CategoryMapperImpl();
        categoryDao.delCategory(cid);
	}

	@Override
	public void editCategory(Category category) {
		CategoryMapper categoryDao = new CategoryMapperImpl();
        categoryDao.editCategory(category);
	}
}
