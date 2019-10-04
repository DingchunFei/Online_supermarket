package com.fei.store.service;

import com.fei.store.domain.Category;

import java.util.List;
import java.util.Map;

public interface CategoryService {

    List<Category> getAllCats() throws Exception;

    Map<Category,Integer> getAllCatsDependencies()  throws Exception ;

	void addCategory(Category category);

	void delCategory(String cid);

	void editCategory(Category category);
}
