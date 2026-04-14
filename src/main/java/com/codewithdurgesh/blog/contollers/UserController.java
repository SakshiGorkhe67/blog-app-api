package com.codewithdurgesh.blog.contollers;

import com.codewithdurgesh.blog.payloads.UserDto;
import com.codewithdurgesh.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    //========================Create User===============================
    @PostMapping("/")
    public ResponseEntity<UserDto> crateUser(@RequestBody UserDto userDto){
        UserDto createduserDto=this.userService.createUser(userDto);
        return  new ResponseEntity<>(createduserDto, HttpStatus.CREATED);
    }
























}
