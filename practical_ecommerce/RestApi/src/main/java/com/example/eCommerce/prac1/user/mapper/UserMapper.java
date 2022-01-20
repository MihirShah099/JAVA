package com.example.eCommerce.prac1.user.mapper;

import com.example.eCommerce.prac1.utility.mapper.BaseMapper;
import com.example.eCommerce.prac1.user.domain.User;
import com.example.eCommerce.prac1.user.model.UserDTO;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<UserDTO, User> {
}
