package com.codewithdurgesh.blog.services;

import com.codewithdurgesh.blog.entities.Post;
import com.codewithdurgesh.blog.payloads.PostDto;
import com.codewithdurgesh.blog.payloads.PostResponse;

import java.util.List;

public interface PostService {
    //create

    PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);

    //update

    PostDto updatePost(PostDto postDto,Integer postId);

    //Delete
    String deletePost(Integer postId);

    //Get all post
    PostResponse getAllPost(Integer pageNumber, Integer pageSize);

    //Get post by id

    PostDto getPostById(Integer postId);

    //Get all post by Category

    List<PostDto> getPostByCategory(Integer categoryId);

    // Get all post by User

    List<PostDto>getPostByUser(Integer userId);
}
