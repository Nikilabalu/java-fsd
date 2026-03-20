package com.hibernate.controller;

import com.hibernate.config.projConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

public class FlightController {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(projConfig.class);
        LocalContainerEntityManagerFactoryBean emf =
                context.getBean(LocalContainerEntityManagerFactoryBean.class);
        System.out.println(emf); //a hex mem loc
        System.out.println("Works!!!");
        context.close();
    }

}
