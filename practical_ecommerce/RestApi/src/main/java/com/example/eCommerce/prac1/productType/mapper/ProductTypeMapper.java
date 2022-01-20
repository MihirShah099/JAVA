package com.example.eCommerce.prac1.productType.mapper;

import com.example.eCommerce.prac1.utility.mapper.BaseMapper;
import com.example.eCommerce.prac1.productType.domain.ProductType;
import com.example.eCommerce.prac1.productType.model.ProductTypeDTO;
import org.mapstruct.Mapper;

@Mapper
public interface ProductTypeMapper extends BaseMapper<ProductTypeDTO, ProductType> {
}
