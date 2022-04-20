package com.security.springsecutity.service;

import com.security.springsecutity.model.Product;

import java.util.List;

public interface ProductService {
    Iterable<Product> getAllProducts();
    Product getProductById(Long productId);
    Product saveProduct(Product product);
    boolean updateProduct(Product newProduct);
    boolean deleteProductById(Long productId);
}
