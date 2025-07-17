package com.ratanak.demo2.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
    private Long id;
    private String firstName;
    private int age ;
    private String address;
    private String role ;
    private  String email;
}
