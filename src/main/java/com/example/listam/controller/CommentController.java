package com.example.listam.controller;

import com.example.listam.entity.Comment;
import com.example.listam.entity.Item;
import com.example.listam.repository.CommentRepository;
import com.example.listam.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
public class CommentController {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ItemRepository itemRepository;

    @PostMapping("/comments/add")
    public String commentsAdd(@RequestParam("comment") String comment,
                              @RequestParam("id") int id) {
        Comment comments = new Comment();
        comments.setComment(comment);
        Optional<Item> byId = itemRepository.findById(id);
        if (byId.isPresent()) {
            Item item = byId.get();
            comments.setItem(item);
            commentRepository.save(comments);
        } else {
            return "/items";
        }
        return "redirect:/items/" + id;
    }

    @GetMapping("/comments/remove")
    public String removeComments(@RequestParam("id") int id) {
        commentRepository.deleteById(id);
        return "redirect:/items";
    }

}
