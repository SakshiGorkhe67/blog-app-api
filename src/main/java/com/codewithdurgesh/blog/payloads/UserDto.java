package com.codewithdurgesh.blog.payloads;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private int id;
    @NotEmpty
    @Size(min=4,message="UserName must be minimum of 4 characters !!")
    private String name;    @Email(message = "Email address is not Valid !!")
    private String email;
    @NotEmpty
    @Size(min=3,max=20,message ="Password must be min of 3 and max of 10 chars!!")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$",
            message = "Must contain at least one uppercase letter, one number, and one special character"
    )
    private String password;
    @NotEmpty(message = "About Should not be Empty")
    private String about;
}
