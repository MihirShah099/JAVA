package com.example.eCommerce.prac1.role.service;

import com.example.eCommerce.prac1.utility.service.BaseService;
import com.example.eCommerce.prac1.role.domain.Role;
import com.example.eCommerce.prac1.role.mapper.RoleMapper;
import com.example.eCommerce.prac1.role.model.RoleDTO;
import com.example.eCommerce.prac1.role.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends BaseService<RoleDTO, Role, Long> {
    public RoleService(RoleRepository repository, RoleMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public String getModuleName() {
        return "[Role Service]";
    }
}
