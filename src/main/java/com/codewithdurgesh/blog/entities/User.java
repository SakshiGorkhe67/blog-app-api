package com.codewithdurgesh.blog.entities;

import jakarta.persistence.*;
import lombok.*;


@Entity
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name="user_name",nullable=false,length=100)
    private String name;

    private String email;

    private String password;

    private String about;

}
