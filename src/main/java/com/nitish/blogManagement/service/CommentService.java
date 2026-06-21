package com.nitish.blogManagement.service;

import com.nitish.blogManagement.model.Comment;
import com.nitish.blogManagement.model.Post;
import com.nitish.blogManagement.model.User;
import com.nitish.blogManagement.repo.CommentRepo;
import com.nitish.blogManagement.repo.PostRepo;
import com.nitish.blogManagement.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    CommentRepo commentRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    PostRepo postRepo;

    public List<Comment> getAllComments() {
        return commentRepo.findAll();
    }

    public Comment getCommentById(Integer id) {
        return commentRepo.findById(id).orElseThrow(() -> new RuntimeException("Comment not found"));
    }

    public Comment createComment(Integer postId,
            Comment comment) {

        String email =
                SecurityContextHolder.getContext()
                        .getAuthentication()
                        .getName();

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        comment.setUser(user);
        comment.setPost(post);

        return commentRepo.save(comment);
    }

    public Comment updateComment(Integer id , Comment comment) {
        Comment existingComment = commentRepo.findById(id).orElseThrow(() -> new RuntimeException("Comment not found"));

        String email =
                SecurityContextHolder.getContext()
                        .getAuthentication()
                        .getName();

        if(!existingComment.getUser()
                .getEmail()
                .equals(email)){

            throw new RuntimeException(
                    "You can edit only your own comment"
            );
        }

        existingComment.setContent(comment.getContent());

        return commentRepo.save(existingComment);
    }

    public String deleteComment(Integer id) {
        Comment comment = commentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        String email =
                SecurityContextHolder.getContext()
                        .getAuthentication()
                        .getName();

        if(!comment.getUser()
                .getEmail()
                .equals(email)){

            throw new RuntimeException(
                    "You can delete only your own comment"
            );
        }

        commentRepo.delete(comment);

        return "Comment deleted successfully";
    }

}
