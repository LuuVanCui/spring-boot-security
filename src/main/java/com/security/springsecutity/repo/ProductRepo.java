package com.security.springsecutity.repo;

import com.security.springsecutity.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {

}
