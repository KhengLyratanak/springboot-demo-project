package com.ratanak.demo2;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
@Entity
@Table(name= "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age ;
    private String email;
    private String role;
    @Column(name = "created_at")
   private LocalDateTime createdAt;
    private String address;
}
