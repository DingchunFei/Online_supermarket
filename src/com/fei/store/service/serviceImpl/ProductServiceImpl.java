package com.fei.store.service.serviceImpl;

import com.fei.store.dao.CategoryMapper;
import com.fei.store.dao.ProductMapper;
import com.fei.store.dao.daoImpl.CategoryMapperImpl;
import com.fei.store.dao.daoImpl.ProductMapperImpl;
import com.fei.store.domain.Category;
import com.fei.store.domain.Product;
import com.fei.store.orbd.IdentityMap;
import com.fei.store.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    @Override
    public List<Product> getAllProducts() throws Exception {
        ProductMapper productDao = new ProductMapperImpl();
        return productDao.getAllProducts();
    }

    @Override
    public Product findProductByPid(String pid) throws Exception {
    	
    	// Try to find product from Identity Map By Using product ID
    	Product product = IdentityMap.getProduct(pid);
    	
    	// The product has not been found from Identity Map
    	if(product == null){
    		ProductMapper productDao = new ProductMapperImpl();
            product = productDao.findProductByPid(pid);
            IdentityMap.addProduct(product);
    	}
      
        return product;
    }

    @Override
    public  List<Product>  findProductsByCid(String cid) throws Exception {
        ProductMapper productDao = new ProductMapperImpl();
        return productDao.findProductsByCid(cid);
    }

    @Override
    public List<Product> getProductsByName(String pname) throws Exception {
        ProductMapper productDao = new ProductMapperImpl();
        return productDao.getProductsByName(pname);
    }
}
