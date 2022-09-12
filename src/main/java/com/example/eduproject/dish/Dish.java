package com.example.eduproject.dish;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "dishesDB")
public class Dish {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "proteins")
    private double proteins;

    @Column(name = "carbs")
    private double carbs;

    @Column(name = "lipids")
    private double lipids;

    @Column(name = "kiloCalories")
    private double kiloCalories;
}
