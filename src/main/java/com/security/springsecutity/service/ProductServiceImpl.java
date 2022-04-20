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
    public Iterable<Product> getAllProducts() {
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
            return false;
        } else {
            log.error(existProduct.get().toString());
            productRepo.save(newProduct);
            return true;
        }
    }

    @Override
    public boolean deleteProductById(Long productId) {
        Optional<Product> existProduct = productRepo.findById(productId);
        if (existProduct.isEmpty()) {
            return false;
        } else {
            productRepo.deleteById(productId);
            return true;
        }
    }

    @Override
    public Product getProductById(Long productId) {
        Optional<Product> product = productRepo.findById(productId);
        log.info("Getting product {}", product);
        return product.get();
    }
}
