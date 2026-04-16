package com.codewithdurgesh.blog.repositories;

import com.codewithdurgesh.blog.entities.Post;
import com.codewithdurgesh.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Integer> {
//custom finder Methods
    List<Post> findByUser(User user);
}
