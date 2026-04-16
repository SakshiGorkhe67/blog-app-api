package com.codewithdurgesh.blog.services.impl;

import com.codewithdurgesh.blog.entities.Post;
import com.codewithdurgesh.blog.payloads.PostDto;

import java.util.List;

public interface PostService {
    //create

    Post createPost(PostDto postDto);

    //update

    Post updatePost(PostDto postDto,Integer postId);

    //Delete
    void deletePost(Integer postId);

    //Get all post
    List<Post> getAllPost();
}
