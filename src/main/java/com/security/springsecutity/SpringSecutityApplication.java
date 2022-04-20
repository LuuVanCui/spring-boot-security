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

			userService.saveUser(new User(null, "Luu Van Cui", "vancui1", "123456", new ArrayList<>()));
			userService.saveUser(new User(null, "Luu Van Cui2", "vancui2", "123456", new ArrayList<>()));
			userService.saveUser(new User(null, "Luu Van Cui3", "vancui3", "123456", new ArrayList<>()));

			userService.addRoleToUser("vancui1", "ROLE_USER");
			userService.addRoleToUser("vancui1", "ROLE_MANAGER");
			userService.addRoleToUser("vancui2", "ROLE_ADMIN");
			userService.addRoleToUser("vancui2", "ROLE_USER");
			userService.addRoleToUser("vancui3", "ROLE_SUPPER_ADMIN");
			userService.addRoleToUser("vancui3", "ROLE_MANAGER");

			// Add some products
			productService.saveProduct(new Product(null, "Iphone 13 pro max", "Smartphone", 13, ""));
			productService.saveProduct(new Product(null, "Macbook Air M1", "Macbook Air", 13, ""));
		};
	}
}
