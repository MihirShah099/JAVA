package com.example.eCommerce.prac1.productType.controller;

import com.example.eCommerce.prac1.UrlConstant;
import com.example.eCommerce.prac1.utility.controller.BaseController;
import com.example.eCommerce.prac1.productType.model.ProductTypeDTO;
import com.example.eCommerce.prac1.productType.service.ProductTypeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = UrlConstant.MainUrl + "productType")
public class ProductTypeController extends BaseController<ProductTypeDTO> {
    public ProductTypeController(ProductTypeService service) {
        super(service);
    }

    @Override
    public String getModuleName() {
        return "[ ProductType Controller]";
    }
}
