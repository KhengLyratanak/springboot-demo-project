package com.ratanak.demo2.repository;


import com.ratanak.demo2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
    Boolean existsByName(String username);
    Boolean existsByEmail(String email);




}
