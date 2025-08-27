package com.ratanak.demo2.dto.user;

import com.ratanak.demo2.common.annotation.ValidEnum;
import com.ratanak.demo2.common.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    @NotNull(message = "username is required")
    @Size(min = 4, max = 50, message = "username must be between 4 and 50 characters")
    private String firstName;

    @NotNull(message = "password is required")
    @Size(min = 8, max = 20, message = "password must be between 8 and 20 characters")
    private String password;

    @NotNull(message = "age is required")
    @Min(value = 18, message = "age must be atleast 18")
    private Integer age;

    @NotNull(message = "address is required")
    @Size(min = 5, max = 50, message = "address must be between 5 and 50 characters")
    private String address;

    @NotNull(message = "email is required")
    @Email(message = "email must be valid")
    private String email;
    @ValidEnum(enumClass = Role.class, message = "Role must be in [USER,ADMIN]")
    private String role = "USER";
}
