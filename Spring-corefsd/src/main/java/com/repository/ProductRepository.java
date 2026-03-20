package com.repository;

import org.springframework.stereotype.Component;

@Component
public class ProductRepository {
    public String someDbMethod() {
        return "I vl write DB ops here for u ";
    }
}
