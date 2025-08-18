package com.ratanak.demo2.service;

import com.ratanak.demo2.dto.user.UpdateUserDto;
import com.ratanak.demo2.dto.user.UserResponseDto;
import com.ratanak.demo2.exception.model.DuplicateResourceException;
import com.ratanak.demo2.exception.model.ResourceNotFoundException;
import com.ratanak.demo2.mapper.UserMapper;
import com.ratanak.demo2.model.BaseResponseModel;
import com.ratanak.demo2.model.BaseResponseWithDataModel;
import com.ratanak.demo2.dto.user.UserDto;
import com.ratanak.demo2.repository.UserRepository;
import com.ratanak.demo2.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
            new UserDto(1L, "John", "", 23, "Phnom Penh", "admin", "dgjf")
    ));

    public ResponseEntity<BaseResponseWithDataModel> ListUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponseDto> dtos = mapper.toDtoList(users);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new BaseResponseWithDataModel("success", "successfully retrieve users", dtos));
    }

    public ResponseEntity<BaseResponseWithDataModel> getUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user not found with id:" + userId));


        // Map the User entity to a UserResponseDto
        UserResponseDto dto = mapper.toDto(user);
        // Return the DTO in the response body
        return ResponseEntity.status(HttpStatus.OK)
                .body(new BaseResponseWithDataModel("success", "user found ", dto));
    }

    public ResponseEntity<BaseResponseModel> createUser(UserDto payload) {
        // validate if username is existed
        if (userRepository.existsByName(payload.getFirstName())) {
            throw new DuplicateResourceException("user is already exist");
        }
        // validate if email is existed
        if (userRepository.existsByEmail(payload.getEmail())) {
            throw new DuplicateResourceException("email is already existed");
        }
        User user = mapper.toEntity(payload);


        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new BaseResponseModel("succes", "succesfully created user"));
    }

    public ResponseEntity<BaseResponseModel> updateUser(UpdateUserDto payload, Long userid) {

        User existing = userRepository.findById(userid)
                .orElseThrow(() -> new ResourceNotFoundException("user not found with id :" + userid));

        //user found
        mapper.updateEntityFromDto(existing, payload);
        userRepository.save(existing);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new BaseResponseModel("succes", "succesfully updated user"));
    }

    public ResponseEntity<BaseResponseModel> deleteUser(Long userid) {
        //if user not found , then response 404
        if (!userRepository.existsById(userid)) {
            throw new ResourceNotFoundException("user not found with id: " + userid);
        }
        userRepository.deleteById(userid);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new BaseResponseModel("success ", "successfully deleted user"));
    }
}
