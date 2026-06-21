package com.nitish.blogManagement.service;

import com.nitish.blogManagement.model.Post;
import com.nitish.blogManagement.model.User;
import com.nitish.blogManagement.repo.PostRepo;
import com.nitish.blogManagement.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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

    public Post createPost(Post post) {

        //Post create karte waqt logged-in user ko author banao.
        String email =
                SecurityContextHolder.getContext()
                        .getAuthentication()
                        .getName();

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        post.setAuthor(user);

        return repo.save(post);
    }

    public Post updatePost(Integer id, Post post) {

        Post existingPost = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        //user can only update his post
        String email =
                SecurityContextHolder.getContext()
                        .getAuthentication()
                        .getName();

        if(!existingPost.getAuthor()
                .getEmail()
                .equals(email)){

            throw new RuntimeException(
                    "You cannot edit someone else's post"
            );
        }


        existingPost.setTitle(post.getTitle());
        existingPost.setContent(post.getContent());

        return repo.save(existingPost);
    }


    public String deletePost(Integer id) {

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

        return "Post deleted";
    }
}
