package com.codewithdurgesh.blog.contollers;

import com.codewithdurgesh.blog.payloads.PostDto;
import com.codewithdurgesh.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class PostController {
    @Autowired
     private PostService postService;

    @PostMapping("/user/{userId}/category/{categoryId}/post")
    public ResponseEntity<PostDto> createPost(
            @RequestBody PostDto postDto,
            @PathVariable Integer userId,
            @PathVariable Integer categoryId) {

        PostDto createdPost = this.postService.createPost(postDto, userId, categoryId);

        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }
}
