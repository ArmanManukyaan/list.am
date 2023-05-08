package com.example.listam.controller;

import com.example.listam.entity.Category;
import com.example.listam.entity.Comment;
import com.example.listam.entity.Item;
import com.example.listam.repository.CategoryRepository;
import com.example.listam.repository.CommentRepository;
import com.example.listam.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ItemController {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @GetMapping("/items")
    public String itemsPage(ModelMap modelMap) {
        List<Item> all = itemRepository.findAll();
        modelMap.addAttribute("items", all);
        return "items";
    }

    @GetMapping("/items/{id}")
    public String itemsPage(@PathVariable("id") int id, ModelMap modelMap) {
        Optional<Item> byId = itemRepository.findById(id);
        List<Comment> comments = commentRepository.findAll();
        if (byId.isPresent()) {
            Item item = byId.get();
            modelMap.addAttribute("item", item);
            modelMap.addAttribute("comments",comments);
            return "singleItem";
        } else {
            return "redirect:/items";
        }
    }

    @GetMapping("/items/add")
    public String itemsAddPage(ModelMap modelMap) {
        List<Category> all = categoryRepository.findAll();
        modelMap.addAttribute("categories", all);
        return "addItem";
    }
    @PostMapping("/items/add")
    public String addItem(@ModelAttribute Item item) {
        itemRepository.save(item);
        return "redirect:/items";
    }
    @GetMapping("/items/remove")
    public String removeItem(@RequestParam("id") int id) {
        itemRepository.deleteById(id);
        return "redirect:/items";
    }
}
