package com.ratanak.demo2.controller;

import com.ratanak.demo2.model.BaseResponseModel;
import com.ratanak.demo2.model.BaseResponseWithDataModel;
import com.ratanak.demo2.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ratanak.demo2.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class Testcontroller {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<BaseResponseWithDataModel>ListUsers() {
        return userService.ListUsers();
    }
    @GetMapping("{id}")
    public ResponseEntity<BaseResponseWithDataModel>getUser(@PathVariable("id") Long userId){
        return userService.getUser(userId);
    }

    @PostMapping()
    public ResponseEntity<BaseResponseModel> createUser(@RequestBody UserDto payload) {
        return userService.createUser(payload);
    }

    //endpoint  /api/v1/users/id
    @PutMapping("/{user_id}")
    public ResponseEntity<BaseResponseModel> updateUser(@PathVariable("user_id") Long userId, @RequestBody UserDto payload) {
        return userService.updateUser(payload, userId);
    }
    //that call path veriable
    @DeleteMapping("/{user_id}")
    public ResponseEntity<BaseResponseModel> deleteUser(@PathVariable("user_id") Long userId) {
        return userService.deleteUser(userId);
    }
}
