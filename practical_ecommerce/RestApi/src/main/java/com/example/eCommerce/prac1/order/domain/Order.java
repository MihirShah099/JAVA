package com.example.eCommerce.prac1.order.domain;

import com.example.eCommerce.prac1.user.domain.User;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "tbl_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime date;
    private String orderNumber;
    private String shippingAddress;
    @ManyToOne
    private User customer;
    @Column(columnDefinition = "double default 0.0")
    private Double totalPrice;
    @Column(columnDefinition = "double default 0.0")
    private Double totalQty;

    @OneToMany(mappedBy = "order",orphanRemoval = true,cascade = CascadeType.ALL)
    private List<OrderDetails> orderDetails = new ArrayList<>();
}
