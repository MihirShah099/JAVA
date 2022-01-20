package com.example.eCommerce.prac1.order.repository;

import com.example.eCommerce.prac1.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByCustomer_IdOrderByDateDesc(Long customer);

    List<Order> findAllByCustomer_IdAndDateBetweenOrderByDateDesc(Long customer, LocalDateTime startDate, LocalDateTime endDate);
}
