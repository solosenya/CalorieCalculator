package com.example.eduproject.eaten;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "eatenDB")
@Data
public class EatenFood {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "mass")
    private double mass;

    @Column(name = "food")
    private String food;
}