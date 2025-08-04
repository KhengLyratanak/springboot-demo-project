package com.ratanak.demo2.mapper;

import com.ratanak.demo2.dto.UserDto;
import com.ratanak.demo2.dto.UserResponseDto;
import com.ratanak.demo2.entity.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    public User toEntity (UserDto dto){
        User entity = new User();
        entity.setName(dto.getFirstName());
        entity.setAge(dto.getAge());
        entity.setPassword(dto.getPassword());
        entity.setRole(dto.getRole());
        entity.setAddress(dto.getAddress());
        entity.setCreatedAt(LocalDateTime.now());

        return entity;
    }

    public UserResponseDto toDto(User entity){
        UserResponseDto dto = new UserResponseDto();

        dto.setId(entity.getId());
        dto.setFirstName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setAge(entity.getAge());
        dto.setAddress(entity.getAddress());
        dto.setRole(entity.getRole());
        return dto;
    }
    public List<UserResponseDto> toDtoList (List<User> entities){
        if(entities == null || entities.isEmpty()){
            return new ArrayList<>();
        }
        return entities.stream()
                .map(user -> this.toDto(user))
                .collect(Collectors.toList());

    }
}
