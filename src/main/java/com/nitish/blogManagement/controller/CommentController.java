package com.nitish.blogManagement.controller;

import com.nitish.blogManagement.model.Comment;
import com.nitish.blogManagement.service.CommentService;
import jakarta.persistence.GeneratedValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    CommentService commentService;

    @GetMapping
    public ResponseEntity<List<Comment>> getAllComments(){
        return new ResponseEntity<>(commentService.getAllComments() , HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable Integer id){
        return new ResponseEntity<>(commentService.getCommentById(id) , HttpStatus.OK);
    }

    @PostMapping("/post/{postId}")
    public ResponseEntity<Comment> createComment(
            @PathVariable Integer postId,
            @RequestBody Comment comment) {

        return new ResponseEntity<>(
                commentService.createComment(postId, comment),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable Integer id ,@RequestBody Comment comment){
        return new ResponseEntity<>(commentService.updateComment(id , comment) , HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable Integer id){
        return new ResponseEntity<>(commentService.deleteComment(id) , HttpStatus.OK);
    }
}
