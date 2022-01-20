package com.example.eCommerce.prac1.productType.service;

import com.example.eCommerce.prac1.utility.service.BaseService;
import com.example.eCommerce.prac1.productType.domain.ProductType;
import com.example.eCommerce.prac1.productType.mapper.ProductTypeMapper;
import com.example.eCommerce.prac1.productType.model.ProductTypeDTO;
import com.example.eCommerce.prac1.productType.repository.ProductTypeRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductTypeService extends BaseService<ProductTypeDTO, ProductType, Long> {
    public ProductTypeService(ProductTypeRepository repository, ProductTypeMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public String getModuleName() {
        return "[ ProductType Service]";
    }
}
