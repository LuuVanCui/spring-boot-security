package com.security.springsecutity.controller;

import com.security.springsecutity.model.Product;
import com.security.springsecutity.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getAllProducts(ModelMap modelMap) {
        List<Product> products = productService.getAllProducts();
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
    public String updateProduct(@ModelAttribute Product product, ModelMap modelMap, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

        }
        productService.updateProduct(product);
        return "redirect:/products";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String deleteProductById(ModelMap modelMap, @PathVariable Long id) {
        productService.deleteProductById(id);
        return "redirect:/products";
    }
}
