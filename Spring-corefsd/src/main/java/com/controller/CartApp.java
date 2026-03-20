package com.controller;

import com.config.ProjConfig;
import com.service.ProductService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CartApp {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjConfig.class);
        ProductService productService = context.getBean(ProductService.class);
        System.out.println(productService.someMethod());
    }
}
