package com.codewithdurgesh.blog.services;

import com.codewithdurgesh.blog.payloads.CommentDto;

public interface CommentService {
    //crete comment
    CommentDto createComment(CommentDto commentDto,Integer postId,Integer userId);

    //delete comment
    void deleteComment(int id);
}