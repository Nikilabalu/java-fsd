package com.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.*")
public class ProjConfig {
    static{
        System.out.println("Project Config detected");
    }
}
