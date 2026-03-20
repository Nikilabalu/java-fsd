package com.service;

import com.repository.ProductRepository;
import com.utility.CartUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductService {
    @Autowired
    ProductRepository productRepository;  // <--- Dependency Injection -- DI
    @Autowired
    CartUtility cartUtility;

    public String someMethod() {
        System.out.println(cartUtility.someUtilMethod());
        return  productRepository.someDbMethod();
    }
}
