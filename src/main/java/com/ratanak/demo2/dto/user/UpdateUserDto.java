package com.ratanak.demo2.dto.user;

import com.ratanak.demo2.common.annotation.ValidEnum;
import com.ratanak.demo2.common.enums.Role;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateUserDto {
    @NotNull(message = "username is required")
    @Size(min = 4, max = 50, message = "username must be between 4 and 50 characters")
    private String firstName;

    @NotNull(message = "age is required")
    @Min(value = 18, message = "age must be atleast 18")
    private Integer age;

    @NotNull(message = "address is required")
    @Size(min = 5, max = 50, message = "address must be between 5 and 50 characters")
    private String address;

    @NotNull(message = "role is required")
    @ValidEnum(enumClass = Role.class,message = "Role will be user or admin")
    private String role = "USER";
}
