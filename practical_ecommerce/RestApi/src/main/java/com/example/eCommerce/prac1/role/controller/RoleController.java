package com.example.eCommerce.prac1.role.controller;

import com.example.eCommerce.prac1.UrlConstant;
import com.example.eCommerce.prac1.utility.controller.BaseController;
import com.example.eCommerce.prac1.role.model.RoleDTO;
import com.example.eCommerce.prac1.role.service.RoleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = UrlConstant.MainUrl + "role")
public class RoleController extends BaseController<RoleDTO> {
    public RoleController(RoleService service) {
        super(service);
    }

    @Override
    public String getModuleName() {
        return "[Role Controller]";
    }
}
