package com.hexaware.careercrafter.mapper;

import com.hexaware.careercrafter.dto.LoginRespDto;
import com.hexaware.careercrafter.dto.RegisterReqDto;
import com.hexaware.careercrafter.dto.UserRespDto;
import com.hexaware.careercrafter.model.User;

public class UserMapper {

    public static User mapToEntity(RegisterReqDto registerReqDto) {
        User user = new User();
        user.setName(registerReqDto.name());       // accessor
        user.setEmail(registerReqDto.email());
        user.setPassword(registerReqDto.password());
        user.setUserRole(registerReqDto.role());
        user.setPhone(registerReqDto.phone());
        user.setLocation(registerReqDto.location());
        return user;
    }

    public static UserRespDto mapToRespDto(User user) {
        return new UserRespDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getUserRole(),
                user.getPhone(),
                user.getLocation()
        );
    }

    public static LoginRespDto mapToLoginRespDto(User user, String token) {
        return new LoginRespDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getUserRole(),
                token
        );
    }
}