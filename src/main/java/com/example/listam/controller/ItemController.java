package com.example.listam.controller;

import com.example.listam.entity.Category;
import com.example.listam.entity.Comment;
import com.example.listam.entity.Item;
import com.example.listam.repository.CategoryRepository;
import com.example.listam.repository.CommentRepository;
import com.example.listam.repository.HashTagRepository;
import com.example.listam.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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

    @Autowired
    private HashTagRepository hashTagRepository;

    @Value("${listam.upload.image.path}")
    private String imageUploadPate;

    @GetMapping("/items")
    public String itemsPage(ModelMap modelMap) {
        List<Item> all = itemRepository.findAll();
        modelMap.addAttribute("items", all);
        return "items";
    }
    @GetMapping("/items/{id}")
    public String itemsPage(@PathVariable("id") int
                                    id, ModelMap modelMap) {
        Optional<Item> byId = itemRepository.findById(id);
        List<Comment> comments = commentRepository.findAllByItem_Id(id);
        if (byId.isPresent()) {
            Item item = byId.get();
            modelMap.addAttribute("item", item);
            modelMap.addAttribute("comments", comments);
            return "singleItem";
        } else {
            return "redirect:/items";
        }
    }

    @GetMapping("/items/add")
    public String itemsAddPage(ModelMap modelMap) {
        List<Category> all = categoryRepository.findAll();
        modelMap.addAttribute("categories", all);
        modelMap.addAttribute("hashtags",hashTagRepository.findAll());
        return "addItem";
    }

    @PostMapping("/items/add")
    public String addItem(@ModelAttribute Item item,
                          @RequestParam("image") MultipartFile multipartFile,@RequestParam("tags")
                              List<Integer> tags) throws IOException {
        if (multipartFile != null && !multipartFile.isEmpty()) {
            String fileName = System.nanoTime() + "_" + multipartFile.getOriginalFilename();
            File file = new File(imageUploadPate + fileName);
            multipartFile.transferTo(file);
            item.setImgName(fileName);
        }
        itemRepository.save(item);
        return "redirect:/items";
    }

    @GetMapping("/items/remove")
    public String removeItem(@RequestParam("id") int id) {
        itemRepository.deleteById(id);
        return "redirect:/items";
    }
}