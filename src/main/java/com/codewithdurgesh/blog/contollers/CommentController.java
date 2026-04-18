package com.codewithdurgesh.blog.contollers;

import com.codewithdurgesh.blog.payloads.ApiResponse;
import com.codewithdurgesh.blog.payloads.CommentDto;
import com.codewithdurgesh.blog.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {

   @Autowired
    private CommentService commentService;

   //*************************************** create Comment ***********************************
    @PostMapping("/posts/{postId}/comments/{userId}")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,@PathVariable Integer postId,@PathVariable Integer userId)
    {
        CommentDto commentDtos=this.commentService.createComment(commentDto,postId,userId);
        return new  ResponseEntity<CommentDto>(commentDtos, HttpStatus.CREATED);
    }


    //*****************************************Delete Comment ***************************

    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer id)
    {
       this.commentService.deleteComment(id);
       return new ResponseEntity<ApiResponse>(new ApiResponse("Comment Deleted Successfully",true),HttpStatus.OK);
    }
}
