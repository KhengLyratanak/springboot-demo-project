package com.ratanak.demo2.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String firstName;
    private String password;

    private int age ;
    private String address;
    private String role ;
    private  String email;
}
