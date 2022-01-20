package com.example.eCommerce.prac1.product.service;

import com.example.eCommerce.prac1.utility.log.AppLogger;
import com.example.eCommerce.prac1.utility.service.BaseService;
import com.example.eCommerce.prac1.product.domain.Product;
import com.example.eCommerce.prac1.product.mapper.ProductMapper;
import com.example.eCommerce.prac1.product.model.ProductDTO;
import com.example.eCommerce.prac1.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends BaseService<ProductDTO, Product, Long> {
    private ProductRepository repository;

    public ProductService(ProductRepository repository, ProductMapper mapper) {
        super(repository, mapper);
        this.repository = repository;
    }

    /**
     * updates product with adding stock
     * @param product ProductDTO
     * @param quantity Double
     * @throws Exception
     */
    public void addProductStock(ProductDTO product, Double quantity) throws Exception {
        try {
            /*made service call for reason if Product model is too large
            and if request made with necessary information only*/
            ProductDTO productDTO = getEntityById(product.getId());

            if (null != quantity && !quantity.equals(0.0)) {
                productDTO.setStock(productDTO.getStock() + quantity);
                saveEntity(productDTO);
            }
        } catch (Exception ex) {
            AppLogger.logger.error(getModuleName() + " [addProductStock()] " + ex.getMessage(), ex);
            throw ex;
        }
    }

    /**
     * updates product with deducting stock
     * @param product ProductDTO
     * @param quantity Double
     * @throws Exception
     */
    public void deductProductStock(ProductDTO product, Double quantity) throws Exception {
        try {
            /*made service call for reason if Product model is too large
            and if request made with necessary information only*/
            ProductDTO productDTO = getEntityById(product.getId());

            if (null != quantity && !quantity.equals(0.0)) {
                productDTO.setStock(productDTO.getStock() - quantity);
                saveEntity(productDTO);
            }
        } catch (Exception ex) {
            AppLogger.logger.error(getModuleName() + " [deductProductStock()] " + ex.getMessage(), ex);
            throw ex;
        }
    }

    @Override
    public String getModuleName() {
        return "[Product Service]";
    }
}
