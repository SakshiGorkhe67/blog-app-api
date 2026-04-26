package com.codewithdurgesh.blog.contollers;

import com.codewithdurgesh.blog.payloads.JwtAuthRequest;
import com.codewithdurgesh.blog.payloads.JwtAuthResponse;
import com.codewithdurgesh.blog.payloads.UserDto;
import com.codewithdurgesh.blog.security.JwtTokenHelper;
import com.codewithdurgesh.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;

    // 🔐 LOGIN API
    @PostMapping("/login")
    public ResponseEntity<?> createToken(@RequestBody JwtAuthRequest request) {

        try {
            // authenticate user
            this.authenticate(request.getUsername(), request.getPassword());

            // load user details
            UserDetails userDetails = this.userDetailsService
                    .loadUserByUsername(request.getUsername());

            // generate JWT token
            String token = this.jwtTokenHelper.generateToken(userDetails);

            // prepare response
            JwtAuthResponse response = new JwtAuthResponse();
            response.setToken(token);

            return ResponseEntity.ok(response);

        } catch (BadCredentialsException e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid username or password");

        } catch (DisabledException e) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("User account is disabled");

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Something went wrong");
        }
    }

    // 🔹 Authentication method
    private void authenticate(String username, String password) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);

        // This will automatically throw:
        // BadCredentialsException / DisabledException
        authenticationManager.authenticate(authenticationToken);
    }

   //register new user api
    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto){

     UserDto registeredUser=this.userService.registerNewUser(userDto);
     return new ResponseEntity<UserDto>(registeredUser,HttpStatus.CREATED);
    }

}