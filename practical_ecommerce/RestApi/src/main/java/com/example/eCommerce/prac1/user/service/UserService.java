package com.example.eCommerce.prac1.user.service;

import com.example.eCommerce.prac1.utility.service.BaseService;
import com.example.eCommerce.prac1.user.domain.User;
import com.example.eCommerce.prac1.user.mapper.UserMapper;
import com.example.eCommerce.prac1.user.model.UserDTO;
import com.example.eCommerce.prac1.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<UserDTO, User, Long> {
    public UserService(UserRepository repository, UserMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public String getModuleName() {
        return "[User Service]";
    }
}
