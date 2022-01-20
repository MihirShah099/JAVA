package com.example.RestApi.prac1.user.repository;

import com.example.RestApi.prac1.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
