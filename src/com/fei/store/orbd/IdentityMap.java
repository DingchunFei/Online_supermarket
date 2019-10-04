package com.fei.store.orbd;

import java.util.HashMap;
import java.util.Map;

import com.fei.store.domain.Product;

public class IdentityMap{
    
    // personMap as IdentityMap
    private static Map productMap = new HashMap();
    
    // Add person object to IdentityMap
    public static void addProduct(Product product) {
    	productMap.put(product.getPid(), product);
    }
    
    // Retrieve person object from personMap
    public static Product getProduct(String pid) {
        return (Product) productMap.get(pid);
    }
    
}