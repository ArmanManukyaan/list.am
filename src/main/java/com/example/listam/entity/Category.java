package com.example.listam.entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@TableGenerator(name = "category")
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
   @Column(name = "name")
    private String name;

}
