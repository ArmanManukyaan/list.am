package com.example.listam.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@TableGenerator(name = "comment")
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "comment")
    private String comment;

    private Date commentDate;

    @ManyToOne
    private Item item;
}
