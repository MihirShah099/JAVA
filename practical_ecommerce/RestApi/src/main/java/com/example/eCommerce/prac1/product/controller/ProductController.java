package com.example.eCommerce.prac1.product.controller;

import com.example.eCommerce.prac1.UrlConstant;
import com.example.eCommerce.prac1.utility.controller.BaseController;
import com.example.eCommerce.prac1.product.model.ProductDTO;
import com.example.eCommerce.prac1.product.service.ProductService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = UrlConstant.MainUrl + "product")
public class ProductController extends BaseController<ProductDTO> {
    public ProductController(ProductService service) {
        super(service);
    }

    @Override
    public String getModuleName() {
        return "[ Product Controller]";
    }
}
