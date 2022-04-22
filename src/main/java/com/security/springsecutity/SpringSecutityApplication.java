package com.security.springsecutity;

import com.security.springsecutity.model.Product;
import com.security.springsecutity.model.Role;
import com.security.springsecutity.model.User;
import com.security.springsecutity.service.ProductService;
import com.security.springsecutity.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class SpringSecutityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecutityApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(UserService userService, ProductService productService) {
		return args -> {
			userService.saveRole(new Role(null, "ROLE_USER"));
			userService.saveRole(new Role(null, "ROLE_MANAGER"));
			userService.saveRole(new Role(null, "ROLE_ADMIN"));
			userService.saveRole(new Role(null, "ROLE_SUPPER_ADMIN"));

			userService.saveUser(new User(null, "Luu Van Cui", "vancui1", "123456", "https://images.unsplash.com/photo-1574417836112-fda6a03dae97?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1470&q=80", new ArrayList<>()));
			userService.saveUser(new User(null, "Nguyen Van A", "vancui2", "123456", "https://images.unsplash.com/photo-1491441555545-d6b8e24ffa33?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1470&q=80", new ArrayList<>()));

			userService.addRoleToUser("vancui1", "ROLE_USER");
			userService.addRoleToUser("vancui2", "ROLE_USER");

			// Add some products
			productService.saveProduct(new Product(null, "Iphone 13 Pro Max", "Smartphone", 13, "https://vcdn-sohoa.vnecdn.net/2021/09/28/DSCF0011-1632545766-jpeg-9210-1632818972.jpg"));
			productService.saveProduct(new Product(null, "Macbook Air M1 256GB", "Macbook Air", 13, "https://cdn.tgdd.vn/Products/Images/44/231244/macbook-air-m1-2020-gray-600x600.jpg"));
		};
	}
}
