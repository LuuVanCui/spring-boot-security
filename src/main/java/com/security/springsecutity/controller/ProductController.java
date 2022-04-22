package com.security.springsecutity.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.security.springsecutity.model.Product;
import com.security.springsecutity.model.User;
import com.security.springsecutity.service.ProductService;
import com.security.springsecutity.service.UserService;
import com.security.springsecutity.utilities.Cookies;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final UserService userService;

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String getAllProducts(HttpServletRequest request, ModelMap modelMap) {
        AtomicBoolean isAuthorized = new AtomicBoolean(false);
        User user = new User();
        try {
            Optional<String> access_token = Cookies.getCookie(request, "access_token");
            String token = access_token.get();
            Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            String username = decodedJWT.getSubject();
            String[] roles = decodedJWT.getClaim("roles").asArray(String.class);

            if (username != null) {
                stream(roles).forEach(role->{
                    if (role.equals("ROLE_USER")) {
                        isAuthorized.set(true);
                    }
                });
            }

            if (isAuthorized.get()) {
                user = userService.getUser(username);
            }
        } catch (Exception exception) {

        } finally {
            List<Product> products = productService.getAllProducts();
            modelMap.addAttribute("products", products);
            modelMap.addAttribute("nameOfUser", user.getName());
            modelMap.addAttribute("imgUrl", user.getImgUrl());
            return "index";
        }
    }

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public String insertProduct(@ModelAttribute Product product) {
        productService.saveProduct(product);
        return "redirect:/products";
    }

    @RequestMapping(value = "/productbyid/{id}", method = RequestMethod.GET)
    public void getProductById(@PathVariable Long id, HttpServletResponse response) throws IOException {
        Product product = productService.getProductById(id);

        response.setStatus(OK.value());
        Map<String, Object> data = new HashMap<>();
        data.put("data", product);
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), data);
    }

    @RequestMapping(value = "/product/update", method = RequestMethod.POST)
    public String updateProduct(@ModelAttribute Product product, ModelMap modelMap) {
        boolean updateProductSuccess = productService.updateProduct(product);
        if (!updateProductSuccess) {
            modelMap.addAttribute("updateProductSuccess", updateProductSuccess);
        }
        return "redirect:/products";
    }

    @RequestMapping(value = "/product/delete/{id}", method = RequestMethod.POST)
    public String deleteProductById(ModelMap modelMap, @PathVariable Long id) {
        productService.deleteProductById(id);
        return "redirect:/products";
    }
}
