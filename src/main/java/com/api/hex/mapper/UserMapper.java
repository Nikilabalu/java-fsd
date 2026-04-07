package com.api.hex.mapper;

import com.api.hex.dto.RegisterReqDto;
import com.api.hex.model.User;

public class UserMapper {
    public static User mapToEntity(RegisterReqDto registerReqDto) {
        User user = new User();
        user.setUsername(registerReqDto.username());
        user.setPassword(registerReqDto.password());
        user.setRole(registerReqDto.role());
        return user;
    }
}
