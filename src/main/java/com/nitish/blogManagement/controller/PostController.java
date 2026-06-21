package com.nitish.blogManagement.controller;

import com.nitish.blogManagement.model.Post;
import com.nitish.blogManagement.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    PostService service;

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts(){
        return new ResponseEntity<>(service.getAllPosts() , HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable int id){
        return new ResponseEntity<>(service.getPostById(id) , HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Post> createPost(
            @RequestBody Post post) {

        return new ResponseEntity<>(
                service.createPost(post),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Integer id, @RequestBody Post post){
        return new ResponseEntity<>(service.updatePost(id , post) , HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Integer id) {
        service.deletePost(id);
        return new ResponseEntity<>("Post Deleted Successfully" , HttpStatus.OK);
    }
}
