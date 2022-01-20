package com.example.eCommerce.prac1.order.mapper;

import com.example.eCommerce.prac1.utility.mapper.BaseMapper;
import com.example.eCommerce.prac1.order.domain.Order;
import com.example.eCommerce.prac1.order.model.OrderDTO;
import org.mapstruct.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<OrderDTO, Order> {
}
