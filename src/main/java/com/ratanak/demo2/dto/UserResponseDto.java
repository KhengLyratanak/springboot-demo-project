package com.ratanak.demo2.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
public class UserResponseDto {
        @JsonProperty("user_id")
        private Long id;
        @JsonProperty("username")
        private String firstName;

        private int age ;

        @JsonProperty("location")
        private String address;
        private String role ;
        private  String email;
    }

