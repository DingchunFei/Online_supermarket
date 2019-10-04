package com.fei.store.service;

import com.fei.store.domain.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAllCats() throws Exception;
}
