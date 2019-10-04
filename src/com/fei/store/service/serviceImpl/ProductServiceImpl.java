package com.fei.store.service.serviceImpl;

import com.fei.store.dao.CategoryMapper;
import com.fei.store.dao.ProductMapper;
import com.fei.store.dao.daoImpl.CategoryMapperImpl;
import com.fei.store.dao.daoImpl.ProductMapperImpl;
import com.fei.store.domain.Category;
import com.fei.store.domain.Product;
import com.fei.store.orbd.IdentityMap;
import com.fei.store.service.ProductService;
import com.fei.store.utils.UUIDUtils;

import java.util.List;
import java.util.UUID;

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

	@Override
	public int insertProduct(Product product) throws Exception {
		product.setPid(UUIDUtils.getId());
		System.out.print(product);
        ProductMapper productDao = new ProductMapperImpl();
        return productDao.insertProduct(product);
	}

	@Override
	public int editProduct(Product product) {

		IdentityMap.removeProduct(product.getPid());

		ProductMapper productDao = new ProductMapperImpl();
        return productDao.editProduct(product);
	}

	@Override
	public int delProduct(String pid) {
		IdentityMap.removeProduct(pid);

		ProductMapper productDao = new ProductMapperImpl();
        return productDao.delProduct(pid);
	}
}
