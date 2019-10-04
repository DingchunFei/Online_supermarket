package com.fei.store.service;

import com.fei.store.domain.Category;
import com.fei.store.domain.Product;

import java.util.List;

public interface ProductService {
    public List<Product> getAllProducts() throws Exception;

    public Product findProductByPid(String pid) throws Exception;

    public  List<Product>  findProductsByCid(String cid) throws Exception;

    public List<Product> getProductsByName(String pname) throws Exception;
}
