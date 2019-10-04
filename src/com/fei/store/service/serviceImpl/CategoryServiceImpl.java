package com.fei.store.service.serviceImpl;

import com.fei.store.dao.CategoryMapper;
import com.fei.store.dao.daoImpl.CategoryMapperImpl;
import com.fei.store.domain.Category;
import com.fei.store.service.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    @Override
    public List<Category> getAllCats() throws Exception {
        CategoryMapper categoryDao = new CategoryMapperImpl();
        return categoryDao.getAllCats();
    }
}
