package com.nitish.blogManagement.service;

import com.nitish.blogManagement.model.Post;
import com.nitish.blogManagement.model.User;
import com.nitish.blogManagement.repo.PostRepo;
import com.nitish.blogManagement.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    PostRepo repo;

    @Autowired
    UserRepo userRepo;

    public List<Post> getAllPosts() {
        return repo.findAll();
    }

    public Post getPostById(int id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
    }

    public Post createPost(Integer userId, Post post) {

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        post.setAuthor(user);

        return repo.save(post);
    }

    public Post updatePost(Integer id, Post post) {

        Post existingPost = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        existingPost.setTitle(post.getTitle());
        existingPost.setContent(post.getContent());

        return repo.save(existingPost);
    }


    public void deletePost(Integer id) {

        Post post = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        String loggedInEmail =
                SecurityContextHolder.getContext()
                        .getAuthentication()
                        .getName();

        if (!post.getAuthor().getEmail().equals(loggedInEmail)) {
            throw new RuntimeException(
                    "You can delete only your own post"
            );
        }

        repo.delete(post);
    }
}
