package com.ratanak.demo2.service;

import com.ratanak.demo2.dto.UserResponseDto;
import com.ratanak.demo2.mapper.UserMapper;
import com.ratanak.demo2.model.BaseResponseModel;
import com.ratanak.demo2.model.BaseResponseWithDataModel;
import com.ratanak.demo2.dto.UserDto;
import com.ratanak.demo2.repository.UserRepository;
import com.ratanak.demo2.entity.User;
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

    @Autowired
    private UserMapper mapper;
    private List<UserDto> users = new ArrayList<>(Arrays.asList(
            new UserDto(1L, "John", "", 23,"Phnom Penh","admin","dgjf")
    ));
    public ResponseEntity<BaseResponseWithDataModel> ListUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponseDto> dtos = mapper.toDtoList(users);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new BaseResponseWithDataModel("success","successfully retrieve users",dtos));
    }
    public ResponseEntity<BaseResponseWithDataModel> getUser(Long userId){
        Optional<User> user = userRepository.findById(userId);

        if(user.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new BaseResponseWithDataModel("fail","user not found with id:"+userId,null));
        }
        // Map the User entity to a UserResponseDto
        UserResponseDto dto = mapper.toDto(user.get());
        // Return the DTO in the response body
        return ResponseEntity.status(HttpStatus.OK)
                .body(new BaseResponseWithDataModel("success","user found ",dto));
    }
    public ResponseEntity<BaseResponseModel> createUser(UserDto payload) {
        User user = mapper.toEntity(payload);


        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new BaseResponseModel("succes","succesfully created user"));
    }
    public ResponseEntity<BaseResponseModel> updateUser(UserDto payload, Long userid) {
        Long userId;
        Optional<User> existing = userRepository.findById(userid);

        if(existing.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new BaseResponseModel("fail","user nott found with id "+userid));
        }
        //user found
        User updatedUser = existing.get();
        mapper.updateEntityFromDto(updatedUser,payload);

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
