package com.fei.store.dao;

import com.fei.store.domain.Category;
import com.fei.store.domain.Product;

import java.util.List;

public interface ProductMapper {
	
	/**
	 * Feature A Mapper
	 */
    public List<Product> getAllProducts() throws Exception;

    public Product findProductByPid(String pid) throws Exception;

    public  List<Product>  findProductsByCid(String cid) throws Exception;

    public List<Product> getProductsByName(String pname) throws Exception;

	/**
	 * Feature B Mapper
	 */
    public int insertProduct(Product product) throws Exception;

	public int editProduct(Product product);

	public int delProduct(String pid);
}
