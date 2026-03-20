package com.hibernate.model;

import jakarta.persistence.*;

@Entity
public class Flight {
    @Id // a Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "flight_number", nullable = false)//column will be flight_number , NOT NULL
    private String flightNumber;
}
