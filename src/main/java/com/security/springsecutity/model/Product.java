package com.security.springsecutity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data @NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @NotBlank(message = "Product name cannot null")
    @Size(min = 3, max = 100)
    private String productName;

    @NotNull
    @NotBlank(message = "Product description cannot null")
    @Size(min = 10, max = 1000)
    private String description;

    @NotNull
    @Min(0)
    private double prize;

    private String url;
}
