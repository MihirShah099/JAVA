package com.example.eCommerce.prac1.product.mapper;

import com.example.eCommerce.prac1.utility.mapper.BaseMapper;
import com.example.eCommerce.prac1.product.domain.Product;
import com.example.eCommerce.prac1.product.model.ProductDTO;
import org.mapstruct.Mapper;

@Mapper
public interface ProductMapper extends BaseMapper<ProductDTO, Product> {
}
