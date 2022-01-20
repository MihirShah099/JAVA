package com.example.RestApi.prac1.user.mapper;

import com.example.RestApi.prac1.core.BaseMapper;
import com.example.RestApi.prac1.user.domain.User;
import com.example.RestApi.prac1.user.model.UserDTO;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<UserDTO, User> {
}
