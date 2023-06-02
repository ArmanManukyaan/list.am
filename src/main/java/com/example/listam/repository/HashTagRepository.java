package com.example.listam.repository;

import com.example.listam.entity.Category;
import com.example.listam.entity.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HashTagRepository extends JpaRepository<Hashtag,Integer> {
    
}
