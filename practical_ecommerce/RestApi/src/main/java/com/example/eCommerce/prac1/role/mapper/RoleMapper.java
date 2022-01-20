package com.example.eCommerce.prac1.role.mapper;

import com.example.eCommerce.prac1.utility.mapper.BaseMapper;
import com.example.eCommerce.prac1.role.domain.Role;
import com.example.eCommerce.prac1.role.model.RoleDTO;
import org.mapstruct.Mapper;

@Mapper
public interface RoleMapper extends BaseMapper<RoleDTO, Role> {
}
