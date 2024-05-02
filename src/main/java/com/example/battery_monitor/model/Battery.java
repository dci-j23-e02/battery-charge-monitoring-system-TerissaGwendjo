package com.example.battery_monitor.model;


import jakarta.persistence.*;

@Entity
@Table (name = "batteries")
public class Battery {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column (nullable = false)
    private String name;

    @Column (nullable = false)
    private Integer chargePercentage;



}
