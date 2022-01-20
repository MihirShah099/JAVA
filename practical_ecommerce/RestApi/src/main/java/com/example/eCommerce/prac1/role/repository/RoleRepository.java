package com.example.eCommerce.prac1.role.repository;

import com.example.eCommerce.prac1.role.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
