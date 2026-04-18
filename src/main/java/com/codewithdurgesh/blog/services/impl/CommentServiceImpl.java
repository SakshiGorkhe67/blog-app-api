package com.codewithdurgesh.blog.services.impl;

import com.codewithdurgesh.blog.entities.Comment;
import com.codewithdurgesh.blog.entities.Post;
import com.codewithdurgesh.blog.entities.User;
import com.codewithdurgesh.blog.exceptions.ResourceNotFoundException;
import com.codewithdurgesh.blog.payloads.CommentDto;
import com.codewithdurgesh.blog.repositories.CommentRepository;
import com.codewithdurgesh.blog.repositories.PostRepository;
import com.codewithdurgesh.blog.repositories.UserRepository;
import com.codewithdurgesh.blog.services.CommentService;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public CommentDto createComment(CommentDto commentDto,Integer postId,Integer userId)
    {
        Post post=this.postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","postId",postId));
        User user= this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","userId",userId));

        Comment comment=this.modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        comment.setUser(user);
        Comment savedcomment=this.commentRepository.save(comment);

        return this.modelMapper.map(savedcomment,CommentDto.class);

    }

    @Override
    public void deleteComment(int id) {
        Comment comment= this.commentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Comment","id",id));
        this.commentRepository.delete(comment);
    }
}