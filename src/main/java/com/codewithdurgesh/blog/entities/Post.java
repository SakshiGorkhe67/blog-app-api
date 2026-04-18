package com.codewithdurgesh.blog.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name="post")
@Setter
@Getter
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;
    @Column(name="post_title",length=100,nullable = false)
    private String title;
    private String content;
    private String imageName;
    private LocalDateTime addDate;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;


    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<Comment> comment=new HashSet<>();



}
