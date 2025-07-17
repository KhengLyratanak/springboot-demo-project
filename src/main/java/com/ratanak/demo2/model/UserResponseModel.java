package com.ratanak.demo2.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class UserResponseModel extends BaseResponseModel{
    private List<UserModel> users;
    public UserResponseModel(String status, String message) {
        super(status, message);
        this.users = users;
    }
}
