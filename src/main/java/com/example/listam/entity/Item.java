package com.example.listam.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@TableGenerator(name = "item")
@Data
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "title")
    private String title;
    private String description;
    @ManyToOne
    private Category category;

    private String imgName;

}
