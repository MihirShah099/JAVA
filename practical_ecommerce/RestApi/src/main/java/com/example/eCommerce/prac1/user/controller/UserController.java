package com.example.eCommerce.prac1.user.controller;

import com.example.eCommerce.prac1.UrlConstant;
import com.example.eCommerce.prac1.utility.controller.BaseController;
import com.example.eCommerce.prac1.user.model.UserDTO;
import com.example.eCommerce.prac1.user.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = UrlConstant.MainUrl + "user")
public class UserController extends BaseController<UserDTO> {
    public UserController(UserService service) {
        super(service);
    }

    @Override
    public String getModuleName() {
        return "[User Controller]";
    }
}
