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


    // Constructors, getters, and setters
    public Battery() {
    }

    public Battery(String name, Integer chargePercentage) {
        this.name = name;
        this.chargePercentage = chargePercentage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getChargePercentage() {
        return chargePercentage;
    }

    public void setChargePercentage(Integer chargePercentage) {
        this.chargePercentage = chargePercentage;
    }

}
