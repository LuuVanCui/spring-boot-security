package com.security.springsecutity.service;

import com.security.springsecutity.model.Product;
import com.security.springsecutity.repo.ProductRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service @RequiredArgsConstructor
@Transactional
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepo productRepo;

    @Override
    public List<Product> getAllProducts() {
        log.info("Fetching all products");
        return productRepo.findAll();
    }

    @Override
    public Product saveProduct(Product product) {
        log.info("Saving product {}", product);
        return productRepo.save(product);
    }

    @Override
    public boolean updateProduct(Product newProduct) {
        Optional<Product> existProduct = productRepo.findById(newProduct.getId());
        if (existProduct.isEmpty()) {
            log.error("Product {} is exist in the database", existProduct.get().getProductName());
            return false;
        } else {
            log.error("Updating product {} to {}", existProduct.get(), newProduct);
            productRepo.save(newProduct);
            return true;
        }
    }

    @Override
    public boolean deleteProductById(Long productId) {
        Optional<Product> existProduct = productRepo.findById(productId);
        if (existProduct.isEmpty()) {
            log.error("Product id {} is invalid", productId);
            return false;
        } else {
            log.info("Deleting product {}", existProduct.get());
            productRepo.deleteById(productId);
            return true;
        }
    }

    @Override
    public Product getProductById(Long productId) {
        Optional<Product> product = productRepo.findById(productId);
        log.info("Fetching product {}", product);
        return product.get();
    }
}
