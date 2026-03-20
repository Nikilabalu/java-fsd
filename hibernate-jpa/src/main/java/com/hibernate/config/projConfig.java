package com.hibernate.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan("com.*")
@EnableTransactionManagement
public class projConfig {
    //we need datasource for db usage
    @Bean
    public DataSource dataSource(){ //this takes care of DB Connection
        var dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/ticket_sys");
        dataSource.setUsername("root");
        dataSource.setPassword("root123");
        return dataSource;
    }
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        // Spring ORM connecting to JPA
        LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        //  give it DS
        localContainerEntityManagerFactoryBean.setDataSource(dataSource());
        //model class package
        localContainerEntityManagerFactoryBean.setPackagesToScan("com.hibernate.model");

        // To connect JPA -> Hibernate
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(hibernateJpaVendorAdapter);

        // Hibernate -> to create tables
        Properties properties = new Properties();
        //the property can be set like update,create,none
        properties.setProperty("hibernate.hbm2ddl.auto","update");
        //hibernate helps to write queries
        localContainerEntityManagerFactoryBean.setJpaProperties(properties);

        return localContainerEntityManagerFactoryBean;
    }
        //hibernate can be used to create tables automatically without the requirement of manual table creation



}
