package com.example.listam.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

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

    @ManyToMany
    @JoinTable(name = "item_hashtag"
            , joinColumns = @JoinColumn(name = "item_id"),
            inverseJoinColumns = @JoinColumn(name = "hashtag_id"))
    List<Hashtag> hashtagList;

}
