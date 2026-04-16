package com.codewithdurgesh.blog.payloads;

import com.codewithdurgesh.blog.entities.Category;
import com.codewithdurgesh.blog.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
@NoArgsConstructor
@Setter
@Getter
public class PostDto {

    private Integer postId;
    private String title;
    private String content;
    private String imageName;
    private LocalDateTime addDate;
    private CategoryDto category;
    private UserDto user;

}
