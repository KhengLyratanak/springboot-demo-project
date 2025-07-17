package com.ratanak.demo2;

import com.ratanak.demo2.model.BaseResponseModel;
import com.ratanak.demo2.model.UserModel;
import com.ratanak.demo2.model.UserResponseModel;
import com.ratanak.demo2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private List<UserModel> users = new ArrayList<>(Arrays.asList(
            new UserModel(1L, "John", 23, "Phnom Penh","admin","dgjf")
    ));
    public ResponseEntity<UserResponseModel> ListUsers() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new UserResponseModel("success","successfully retrieve users"));
    }
    public ResponseEntity<BaseResponseModel> createUser(UserModel payload) {
       User user = new User();
       user.setName(payload.getFirstName());
       user.setAddress(payload.getAddress());
       user.setAge(payload.getAge());
       user.setEmail(payload.getEmail());
       user.setCreatedAt(LocalDateTime.now());
       user.setRole(payload.getRole());

       userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new BaseResponseModel("succes","succesfully created user"));
    }
    public ResponseEntity<BaseResponseModel> updateUser(UserModel payload,Long userid) {
        Long userId;
        Optional<User> existing = userRepository.findById(userid);

        if(existing.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new BaseResponseModel("fail","user not found with id "+userid));
        }
        //user found
        User updatedUser = existing.get();
       // modify value
        updatedUser.setName(payload.getFirstName());
        updatedUser.setAge(payload.getAge());
        updatedUser.setRole(payload.getRole());
        updatedUser.setEmail(payload.getEmail());
        updatedUser.setAddress(payload.getAddress());

        userRepository.save(updatedUser);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new BaseResponseModel("succes","succesfully updated user"));
    }
public ResponseEntity<BaseResponseModel> deleteUser( Long userid){
    Optional<User> existing = userRepository.findById(userid);
    if(existing.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new BaseResponseModel("fail","user not found with id "+userid));
    }
    userRepository.deleteById(userid);
    return ResponseEntity.status(HttpStatus.NO_CONTENT)
            .body(new BaseResponseModel("succes","succesfully deleted user"));
}

}