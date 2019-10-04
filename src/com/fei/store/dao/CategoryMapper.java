package com.fei.store.dao;

import com.fei.store.domain.Category;

import java.util.List;

public interface CategoryMapper {
    List<Category> getAllCats() throws Exception;

	public Integer getCatsDependencies(Category cate);

	void addCategory(Category category);

	void delCategory(String cid);

	void editCategory(Category category);
}
