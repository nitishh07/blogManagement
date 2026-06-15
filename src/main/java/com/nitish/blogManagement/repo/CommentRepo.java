package com.nitish.blogManagement.repo;

import com.nitish.blogManagement.model.Comment;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepo extends JpaRepository<Comment , Integer> {
}
