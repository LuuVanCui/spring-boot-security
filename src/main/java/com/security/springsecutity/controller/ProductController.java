package com.security.springsecutity.controller;

import com.security.springsecutity.model.Product;
import com.security.springsecutity.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getAllProducts(ModelMap modelMap) {
        Iterable<Product> products = productService.getAllProducts();
        modelMap.addAttribute("products", products);
        return "index";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getProductById(@PathVariable Long id, ModelMap modelMap) {
        Product product = productService.getProductById(id);
        modelMap.addAttribute("product", product);
        return "detail-product";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateProduct(@ModelAttribute Product product, ModelMap modelMap) {
        productService.updateProduct(product);
        return getAllProducts(modelMap);
    }
}
