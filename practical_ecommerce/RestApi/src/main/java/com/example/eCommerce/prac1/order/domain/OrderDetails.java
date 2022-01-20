package com.example.eCommerce.prac1.order.domain;

import com.example.eCommerce.prac1.product.domain.Product;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tbl_order_details")
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Product product;
    @Column(columnDefinition = "double default 0.0")
    private Double price;
    @Column(columnDefinition = "double default 0.0")
    private Double qty;

    @ManyToOne
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Order order;
}
