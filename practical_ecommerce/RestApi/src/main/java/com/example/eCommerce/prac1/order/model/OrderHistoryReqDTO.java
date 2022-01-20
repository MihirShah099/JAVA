package com.example.eCommerce.prac1.order.model;

import com.example.eCommerce.prac1.user.model.UserDTO;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderHistoryReqDTO {
    private UserDTO loggedInUser;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
